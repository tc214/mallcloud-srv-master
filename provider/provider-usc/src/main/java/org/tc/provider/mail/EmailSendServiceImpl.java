package org.tc.provider.mail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.tc.base.constant.GlobalConstant;
import org.tc.base.enums.ErrorCodeEnum;
import com.xiaoleilu.hutool.date.DateUtil;
import org.tc.util.PublicUtil;
import org.tc.util.RedisKeyUtil;
import org.tc.core.generator.UniqueIdGenerator;
import org.tc.provider.mail.model.MailEntity;
import org.tc.provider.model.domain.CompanyInfo;
import org.tc.provider.model.domain.UserInfo;
import org.tc.provider.model.exceptions.UserBizException;
import org.tc.provider.service.FreeMarkerService;
import org.tc.provider.service.UserService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class EmailSendServiceImpl implements EmailSendService {

    @Resource
    private TaskExecutor taskExecutor;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private FreeMarkerService freeMarkerService;

    // config email sender
    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${mallcloud.auth.rest-pwd-url}")
    private String resetPwdUrl;


    @Override
    public int sendSimpleMail(String subject, String text, Set<String> to) {
        int result = 1;
        try {
            SimpleMailMessage message = MailEntity.createSimpleMailMessage(subject, text, to);
            message.setFrom(from);
            taskExecutor.execute(() -> mailSender.send(message));
        } catch (Exception e) {
            log.info("sendSimpleMail [FAIL] ex={}", e.getMessage(), e);
            result = 0;
        }
        return result;
    }

    @Override
    public int sendTemplateMail(String subject, String text, Set<String> to) {
        int result = 1;
        try {
            MimeMessage mimeMessage = getMimeMessage(subject, text, to);
            taskExecutor.execute(() -> mailSender.send(mimeMessage));
        } catch (Exception e) {
            log.info("sendTemplateMail [FAIL] ex={}", e.getMessage(), e);
            result = 0;
        }
        return result;
    }

    @Override
    public int sendTemplateMail(Map<String, Object> model, String templateLocation, String subject, Set<String> to) {
        String text;
        try {
            text = freeMarkerService.getTemplate(model, templateLocation);
        } catch (IOException | TemplateException e) {
            log.info("sendTemplateMail [FAIL] ex={}", e.getMessage(), e);
            return 0;
        }
        return this.sendTemplateMail(subject, text, to);
    }


    private MimeMessage getMimeMessage(String subject, String text, Set<String> to) {
        Preconditions.checkArgument(!PublicUtil.isNull(subject, text, to), "参数异常, 邮件信息不完整");

        String[] toArray = setToArray(to);
        Preconditions.checkArgument(PublicUtil.isNotEmpty(toArray), "请输入收件人邮箱");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(new InternetAddress(GlobalConstant.APP_NICKNAME + " <" + from + ">"));
            helper.setTo(toArray);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            log.error("生成邮件消息体出现异常={}", e.getMessage(), e);
            return null;
        }
        return mimeMessage;
    }

    private String[] setToArray(Set<String> to) {
        Preconditions.checkArgument(PublicUtil.isNotEmpty(to), "请输入收件人邮箱");
        Set<String> toSet = Sets.newHashSet();
        for (String toStr : to) {
            toStr = toStr.trim();
            if (PublicUtil.isEmailAddress(toStr)) {
                toSet.add(toStr);
            }
        }
        if (PublicUtil.isEmpty(toSet)) {
            return null;
        }
        return toSet.toArray(new String[toSet.size()]);
    }

    @Override
    public void submitResetPwdEmail(String emailCode) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(emailCode), ErrorCodeEnum.USER10011018.msg());
        JSONObject json = JSON.parseObject(emailCode);
        String email = json.getString("email");
        String imageCode = json.getString("imageCode");

        // 获取用户
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo = userService.selectOne(userInfo);
        if (userInfo == null) {
            throw new UserBizException(ErrorCodeEnum.USER10011004, email);
        }

        String resetPwdKey = PublicUtil.getUuid() + UniqueIdGenerator.generateId();
        redisTemplate.opsForValue().set(RedisKeyUtil.getResetPwdTokenKey(resetPwdKey),
                userInfo, 7 * 24, TimeUnit.HOURS);

        Map<String, Object> param = Maps.newHashMap();
        param.put("loginName", userInfo.getLoginName());
//        param.put("email", email);
        param.put("resetPwdUrl", resetPwdUrl + resetPwdKey);
        param.put("dateTime", DateUtil.formatDateTime(new Date()));

        Set<String> to = Sets.newHashSet();
        to.add(email);
        String subject = GlobalConstant.RESET_PWD_SUBJECT;
        String templateLocation = "sendResetPwdTemplate.ftl";
        int sendTemplateMailRes = sendTemplateMail(param, templateLocation, subject, to);
    }

    @Override
    public int sendActiveEmail(UserInfo userInfo) {
        if (userInfo == null) {
            throw new UserBizException(ErrorCodeEnum.USER10011004, userInfo);
        }
        String activeToken = PublicUtil.getUuid().substring(0, 20);
        userInfo.setActiveCode(activeToken);

        // save token to redis
        redisTemplate.opsForValue().set(RedisKeyUtil.getActiveUserKey(activeToken),
                userInfo, 1 * 24, TimeUnit.HOURS);
        // save token to db
        userService.updateUserInfo(userInfo);

        String url = GlobalConstant.URL_PASSPORT_ACTIVATE + activeToken;
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", userInfo.getUserName());
        map.put("url", url);
        map.put("dateTime", "2018-10-19");
        String templateLocation = "sendRegisterActivateTemplate.ftl";

        String subject = GlobalConstant.REGISTER_ACTIVATE_SUBJECT;
        Set<String> to = new HashSet<String>();
        to.add(userInfo.getEmail());
        int sendTemplateMailRes = sendTemplateMail(map, templateLocation, subject, to);
        log.info("sendTemplateMailRes={}", sendTemplateMailRes);
        return sendTemplateMailRes;
    }

    @Override
    public int sendCompRegCheckEmail(CompanyInfo companyInfo) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("companyname", companyInfo.getGroupName());
        map.put("dateTime", "2018-10-19");
        String templateLocation = "sendCompRegCheckTemplate.ftl";

        String subject = GlobalConstant.REGISTER_COM_SUBJECT;
        Set<String> to = new HashSet<String>();
        to.add(companyInfo.getGroupEmail());
        int sendTemplateMailRes = sendTemplateMail(map, templateLocation, subject, to);
        log.info("sendTemplateMailRes={}", sendTemplateMailRes);
        return sendTemplateMailRes;
    }

}

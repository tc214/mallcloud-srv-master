package org.tc.provider.mail.model;


import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.tc.util.PublicUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;
import java.util.Set;

@Slf4j
@Data
public class MailEntity {


    /**
     * 邮件回复地址
     */
    private String replyTo;
    /**
     * 邮件收件人的地址集合
     */
    private Set<String> to;
    /**
     * 邮件的抄送收件人的地址集合
     */
    private Set<String> cc;
    /**
     * 邮件的密件抄送收件人的地址集合
     */
    private Set<String> bcc;
    /**
     * 发送时间
     */
    private Date sentDate;
    /**
     * 邮件的主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String text;

    /**
     * Initialize a new Mail entity.
     *
     * @param subject the subject
     * @param text    the text
     * @param to      the to
     */
    public MailEntity(String subject, String text, Set<String> to) {
        this.subject = subject;
        this.text = text;
        this.to = to;
        this.sentDate = new Date();
    }

    /**
     * Initialize a new Mail entity.
     *
     * @param subject the subject
     * @param text    the text
     * @param to      the to
     * @param cc      the cc
     */
    public MailEntity(String subject, String text, Set<String> to, Set<String> cc) {
        this.subject = subject;
        this.text = text;
        this.to = to;
        this.cc = cc;
        this.sentDate = new Date();
    }

    /**
     * Create simple mail message
     *
     * @param subject the subject
     * @param text    the text
     * @param to      the to
     * @return the simple mail message
     */
    public static SimpleMailMessage createSimpleMailMessage(String subject, String text, Set<String> to) {
        log.info("邮件信息 subject={}, text={}, to={}", subject, text, to);
        Preconditions.checkArgument(!PublicUtil.isNull(subject, text, to), "参数异常, 邮件信息不完整");

        String[] toArray = setToArray(to);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(toArray);
        return simpleMailMessage;
    }

    private static String[] setToArray(Set<String> to) {
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

}

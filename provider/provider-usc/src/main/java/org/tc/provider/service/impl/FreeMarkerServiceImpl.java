package org.tc.provider.service.impl;

import com.google.common.base.Preconditions;
import org.tc.util.PublicUtil;
import org.tc.provider.service.FreeMarkerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;


@Service
public class FreeMarkerServiceImpl implements FreeMarkerService {
    @Resource
    private Configuration configuration;

//    @Autowired
//    FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public String getTemplate(Map<String, Object> map, String templateLocation) throws IOException, TemplateException {
        Preconditions.checkArgument(PublicUtil.isNotEmpty(templateLocation), "模板不能为空");
//        Template t = freeMarkerConfigurer.getConfiguration().getTemplate(templateLocation, "UTF-8");
        Template t = configuration.getTemplate(templateLocation, "UTF-8");
        return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
    }
}

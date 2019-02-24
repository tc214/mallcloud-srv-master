package org.tc.provider.validate.code.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tc.provider.validate.SecurityResult;
import org.tc.provider.validate.code.ValidateCodeGenerator;
import org.tc.provider.validate.code.ValidateCodeRepository;
import org.tc.provider.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * 图片验证码处理器
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Resource
    private ObjectMapper objectMapper;

    public ImageCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators,
                              ValidateCodeRepository validateCodeRepository) {
        super(validateCodeGenerators, validateCodeRepository);
    }

    @Override
    protected void sendValidateCode(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(imageCode.getImage(), "JPEG", bos);
        SecurityResult result = SecurityResult.ok(bos.toByteArray());
        String json = objectMapper.writeValueAsString(result);
        HttpServletResponse response = request.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

package org.tc.provider.validate.code.impl;


import org.tc.provider.validate.code.ValidateCode;
import org.tc.provider.validate.code.ValidateCodeException;
import org.tc.provider.validate.code.ValidateCodeRepository;
import org.tc.provider.validate.code.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis的验证码存取器
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    private final RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public RedisValidateCodeRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveToRedis(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        String key = buildKey(request, type);
        redisTemplate.opsForValue().set(key, code, 3, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, type));
        if (value == null) {
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(buildKey(request, type));
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }

}

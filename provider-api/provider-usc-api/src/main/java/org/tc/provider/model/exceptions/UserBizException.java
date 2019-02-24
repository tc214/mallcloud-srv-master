package org.tc.provider.model.exceptions;

import org.tc.base.enums.ErrorCodeEnum;
import org.tc.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * The class User biz exception.
 */
@Slf4j
public class UserBizException extends BusinessException {


    private static final long serialVersionUID = 8689840259672405895L;

    public UserBizException() {
    }

    public UserBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== UserException, code:" + this.code + ", message:" + super.getMessage());

    }

    public UserBizException(int code, String msg) {
        super(code, msg);
        log.info("<== UserException, code:" + this.code + ", message:" + super.getMessage());
    }

    public UserBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== UserException, code:" + this.code + ", message:" + super.getMessage());
    }

    public UserBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== UserException, code:" + this.code + ", message:" + super.getMessage());
    }
}

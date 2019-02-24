package org.tc.provider.controller;

import org.tc.util.PublicUtil;
import org.tc.util.ThreadLocalMap;
import org.tc.base.constant.GlobalConstant;
import org.tc.base.dto.LoginAuthDto;
import org.tc.base.enums.ErrorCodeEnum;
import org.tc.base.exception.BusinessException;

public class BaseController {

    protected LoginAuthDto getLoginAuthDto() {
        LoginAuthDto loginAuthDto = (LoginAuthDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (PublicUtil.isEmpty(loginAuthDto)) {
            throw new BusinessException(ErrorCodeEnum.USER10011041);
        }
        return loginAuthDto;
    }


}

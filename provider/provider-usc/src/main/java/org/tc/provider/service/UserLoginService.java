package org.tc.provider.service;


import org.tc.provider.model.dto.user.LoginRespDto;

public interface UserLoginService {

    LoginRespDto loginAfter(Long applicationId);

}

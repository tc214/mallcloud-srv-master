package org.tc.provider.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tc.wrapper.WrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(WrapMapper.ok("退出成功")));
    }

}

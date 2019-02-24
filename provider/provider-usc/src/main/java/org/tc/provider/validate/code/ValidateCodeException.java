package org.tc.provider.validate.code;

import org.springframework.security.core.AuthenticationException;


public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = -5597313802721087175L;

    /**
     * Instantiates a new Validate code exception.
     *
     * @param msg the msg
     */
    public ValidateCodeException(String msg) {
        super(msg);
    }

}

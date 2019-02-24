package org.tc.provider.mail;


import org.tc.provider.model.domain.CompanyInfo;
import org.tc.provider.model.domain.UserInfo;

import java.util.Map;
import java.util.Set;

public interface EmailSendService {


    int sendSimpleMail(String subject, String text, Set<String> to);

    int sendTemplateMail(String subject, String text, Set<String> to);

    int sendTemplateMail(Map<String, Object> model, String templateLocation, String subject, Set<String> to);

    void submitResetPwdEmail(String email);

    int sendActiveEmail(UserInfo object);

    int sendCompRegCheckEmail(CompanyInfo companyInfo);
}

package org.tc.provider.enums;

import java.util.Arrays;
import java.util.List;

public enum UserStatusEnum {

    ENABLE("ENABLE", "启用"),
    DISABLE("DISABLE", "禁用");
    String key;
    String value;

    UserStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static List<UserStatusEnum> getList() {
        return Arrays.asList(UserStatusEnum.values());
    }


}

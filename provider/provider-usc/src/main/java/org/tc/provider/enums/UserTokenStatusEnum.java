package org.tc.provider.enums;


/**
 * The enums user token status enums.
 */
public enum UserTokenStatusEnum {

    ON_LINE(0, "在线"),
    ON_REFRESH(10, "已刷新"),
    OFF_LINE(20, "离线");
    int status;
    String value;

    public static String getValue(int status) {
        for (UserTokenStatusEnum ele : UserTokenStatusEnum.values()) {
            if (status == ele.getStatus()) {
                return ele.getValue();
            }
        }
        return null;
    }

    UserTokenStatusEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }


}
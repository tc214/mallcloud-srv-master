package org.tc.base.constant;

/**
 * The class Global constant.
 */
public class GlobalConstant {

    public static final long ACTIVATE_VALID_TIME = 24 * 60 * 60 * 1000; // 24h
    public static final String UNKNOWN = "unknown";
    public static final String ROOT_PREFIX = "mallcloud";


    public static final String LOCALHOST_IP = "127.0.0.1";
    public static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
    public static final int MAX_IP_LENGTH = 15;
    public static final String URL_PASSPORT = "http://passport.tc.com/";
    public static final String URL_PASSPORT_VERIFICATION = "http://passport.tc.com/verification/";
    public static final String URL_PASSPORT_ACTIVATE = "http://passport.tc.com/user/active?activeToken=";
    public static final String APP_NICKNAME = "开发云";
    public static final String REGISTER_ACTIVATE_SUBJECT = "欢迎使用开发云!立即激活您的云账号";
    public static final String RESET_PWD_SUBJECT = "重置密码";
    public static final String REGISTER_COM_SUBJECT = "单位注册审核通知";

    public static final class Sys {
        private Sys() {
        }

        public static final String TOKEN_AUTH_DTO = "CURRENT_USER_DTO";
        public static final Long SUPER_MANAGER_USER_ID = 1L;
        public static final String SUPER_MANAGER_LOGIN_NAME = "admin";
        public static final Long SUPER_MANAGER_ROLE_ID = 1L;
        public static final Long SUPER_MANAGER_GROUP_ID = 1L;
        public static final Long OPER_APPLICATION_ID = 1L;


    }

    /**
     * The class Symbol.
     */
    public static final class Symbol {
        private Symbol() {
        }

        /**
         * The constant COMMA.
         */
        public static final String COMMA = ",";

        /**
         * The constant PERCENT.
         */
        public final static String PERCENT = "%";

        /**
         * The constant PIPE.
         */
        public final static String PIPE = "||";
        public final static String SHORT_LINE = "-";
        public final static String SPACE = " ";
        public static final String SLASH = "/";
        public static final String MH = ":";

    }
}

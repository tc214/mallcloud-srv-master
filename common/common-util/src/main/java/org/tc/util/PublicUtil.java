package org.tc.util;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class PublicUtil {

    private final static String STRING_NULL = "-";
    private static final String REGX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";


    /**
     * Create Uuid string.
     *
     * @return the UUID string
     */
    public synchronized static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 判断一个或多个对象是否为空
     *
     * @param values 可变参数, 要判断的一个或多个对象
     * @return 只要有一个对象为空则返回true, 否则返回false
     */
    public static boolean isNull(Object... values) {
        if (!PublicUtil.isNotNullAndNotEmpty(values)) {
            return true;
        }
        for (Object value : values) {
            boolean flag;
            if (value instanceof Object[]) {
                flag = !isNotNullAndNotEmpty((Object[]) value);
            } else if (value instanceof Collection<?>) {
                flag = !isNotNullAndNotEmpty((Collection<?>) value);
            } else if (value instanceof String) {
                flag = isOEmptyOrNull(value);
            } else {
                flag = (null == value);
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象数组不为Null，且不为空
     *
     * @param objects 对象集合
     * @return boolean
     */
    private static Boolean isNotNullAndNotEmpty(Object[] objects) {
        boolean bl = false;
        if (null != objects && 0 < objects.length) {
            bl = true;
        }
        return bl;
    }

    /**
     * 判断对象集合不为Null，且不为空
     *
     * @param collection 对象集合
     * @return boolean
     */
    private static Boolean isNotNullAndNotEmpty(Collection<?> collection) {
        boolean bl = false;
        if (null != collection && !collection.isEmpty()) {
            bl = true;
        }
        return bl;
    }

    /**
     * 判断对象是否为空或者NULL
     *
     * @param o 对象
     * @return boolean
     */
    private static boolean isOEmptyOrNull(Object o) {
        return o == null || isSEmptyOrNull(o.toString());
    }

    /**
     * 判断字符串为NULL或者长度为0
     *
     * @param s 字符串
     * @return boolean
     */
    private static boolean isSEmptyOrNull(String s) {
        return trimAndNullAsEmpty(s).length() <= 0;
    }

    private static String trimAndNullAsEmpty(String s) {
        if (s != null && !s.trim().equals(STRING_NULL)) {
            return s.trim();
        } else {
            return "";
        }
    }

    public static boolean isStrEmptyOrNull(String s) {
        return s == null || s.length() <= 0;
    }

    public static boolean isEmailAddress(String str) {
        boolean bl = true;
        if (isSEmptyOrNull(str) || !str.matches(REGX_EMAIL)) {
            bl = false;
        }
        return bl;
    }

    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj == "") {
            return true;
        }
        if (pObj instanceof String) {
            return ((String) pObj).length() == 0;
        } else if (pObj instanceof Collection) {
            return ((Collection) pObj).isEmpty();
        } else if (pObj instanceof Map) {
            return ((Map) pObj).size() == 0;
        }
        return false;
    }

    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        }
        if (pObj == "") {
            return false;
        }
        if (pObj instanceof String) {
            return ((String) pObj).length() != 0;
        } else if (pObj instanceof Collection) {
            return !((Collection) pObj).isEmpty();
        } else if (pObj instanceof Map) {
            return ((Map) pObj).size() != 0;
        }
        return true;
    }

}

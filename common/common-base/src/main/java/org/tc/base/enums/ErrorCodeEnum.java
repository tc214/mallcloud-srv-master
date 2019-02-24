package org.tc.base.enums;


/**
 * The enum Error code enums.
 */
public enum ErrorCodeEnum {

    GL99990100(9999100, "参数异常"),

    GL99990401(99990401, "无访问权限"),

    GL99990500(500, "未知异常"),

    GL99990403(9999403, "无权访问"),

    GL9999404(9999404, "找不到指定资源"),

    GL99990001(99990001, "注解使用错误"),

    GL99990002(99990002, "微服务不在线,或者网络超时"),

    USER10010001(10010001, "会话超时,请刷新页面重试"),

    USER10010002(10010002, "TOKEN解析失败"),

    USER10010003(10010003, "操作频率过快, 您的帐号已被冻结"),

    USER10011001(10011001, "用户Id不能为空"),

    USER10011002(10011002, "找不到用户,loginName=%s"),

    USER10011003(10011003, "找不到用户,userId=%s"),

    USER10011004(10011004, "找不到用户,email=%s"),

    USER10011006(10012006, "手机号不能为空"),

    USER10011007(10011007, "登录名不能为空"),

    USER10011008(10011008, "新密码不能为空"),

    USER10011009(10011009, "确认密码不能为空"),

    USER10011010(10011010, "两次密码不一致"),

    USER10011011(10011011, "用户不存在, userId=%s"),

    USER10011012(10011012, "登录名已存在"),

    USER10011013(10011013, "手机号已存在"),

    USER10011014(10011014, "密码不能为空"),

    USER10011016(10011016, "用户名或密码错误"),

    USER10011017(10011017, "验证类型错误"),

    USER10011018(10011018, "邮箱不能为空"),

    USER10011019(10011019, "邮箱已存在"),

    USER10011021(10011021, "发送邮箱验证码对象转换为json字符串失败"),

    USER10011025(10011025, "用户已存在, loginName=%"),

    USER10011026(10011026, "更新用户失败, userId=%"),

    USER10011027(10011027, "找不到用户,mobile=%s"),

    USER10011028(10011028, "链接已失效"),

    USER10011029(10011029, "重置密码失败"),

    USER10011030(10011030, "激活失败, 链接已过期"),

    USER10011031(10011031, "验证码超时, 请重新发送验证码"),

    USER10011032(10011032, "邮箱不存在, loginName=%s,email=%s"),

    USER10011035(10011035, "原始密码输入错误"),

    USER10011036(10011036, "新密码和原始密码不能相同"),

    USER10011037(10011037, "修改用户失败,userId=%s"),

    USER10011038(10011038, "激活用户失败,userId=%s"),

    USER10011039(10011039, "验证token失败"),

    USER10011040(10011040, "解析header失败"),

    USER10011041(10011041, "页面已过期,请重新登录"),

    USER10014001(10014001, "找不到权限信息, actionId=%s"),

    USER10014002(10014002, "删除失败, actionId=%s"),

    USER10014003(10014003, "保存权限信息失败"),

    USER10012001(10012001, "角色ID不能为空"),

    ;


    private int code;
    private String msg;

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCodeEnum getEnum(int code) {
        for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}

package org.tc.provider.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Md5Util {

    public static String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean checkoutPwd(String pass, String hashPass) {
        return new BCryptPasswordEncoder().matches(pass, hashPass);
    }

    public static void main(String args[]) {
        String pwd = encrypt("123456");
        String hashPass = encrypt(pwd);
        System.out.println(pwd);
        boolean f = checkoutPwd(pwd, hashPass);
        System.out.println(f);
    }


}

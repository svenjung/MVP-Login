package com.alashoo.signmvp.util;

public class Constant {
    public static final String SP_KEY_USER = "sp_key_user";
    public static final String SP_KEY_TOKEN = "sp_key_token";

    public static final String SP_KEY_SHOW_GUIDE = "sp_key_show_guide";

    public interface SmsType {
        String SMS_TYPE_V2_REGISTER = "REGISTER";

        String SMS_TYPE_V2_FORGET_PASSWORD = "FORGET_PASSWORD";

        String SMS_TYPE_V2_CHANGE_MOBILE = "CHANGE_MOBILE";

        String SMS_TYPE_V2_ADD_BANK_CARD = "ADD_BANK_CARD";

        String SMS_TYPE_V2_BIND = "BIND";
    }
}

package com.pparreno.ligchat.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class FieldValidator {

    public static boolean validateEmailString(String email) {
        if (email == null) {
            return false;
        } else {
            if (email.isEmpty()) {
                return false;
            } else {
                return EmailValidator.getInstance(true).isValid(email);
            }
        }
    }

    public static boolean validateUserName(String username) {
        if (username == null) {
            return false;
        } else {
            if (username.isEmpty()) {
                return false;
            } else {
                return username.length() >= 8;
            }
        }
    }

    public static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        } else {
            if (password.isEmpty()) {
                return false;
            } else {
                return password.length() >= 8;
            }
        }
    }

}

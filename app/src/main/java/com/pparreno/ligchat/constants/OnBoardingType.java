package com.pparreno.ligchat.constants;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.pparreno.ligchat.constants.OnBoardingType.SIGN_IN;
import static com.pparreno.ligchat.constants.OnBoardingType.SIGN_UP;

@IntDef({SIGN_UP, SIGN_IN})
@Retention(RetentionPolicy.SOURCE)
public @interface OnBoardingType{
    int SIGN_UP = 0;
    int SIGN_IN = 1;
}
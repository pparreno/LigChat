package com.pparreno.ligchat.constants;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.pparreno.ligchat.constants.MessageViewType.EXTERNAL_TYPE;
import static com.pparreno.ligchat.constants.MessageViewType.OWNER_TYPE;


@IntDef({OWNER_TYPE, EXTERNAL_TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface MessageViewType {
    int EXTERNAL_TYPE = 0;
    int OWNER_TYPE = 1;
}
package com.mozahidone.openai.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    public static boolean isOk(String str) {
        return !(str == null || str.trim().isEmpty());
    }

}

package com.ies.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class TempPzwd {

    public static String tempPzwd(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String tempPzwd = RandomStringUtils.random( 6, characters );
        return tempPzwd;
    }
}

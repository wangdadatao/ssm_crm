package com.datao.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by 海涛 on 2016/5/3.
 */
public class ToUTF8 {

    public static String toUTF8(String str) {
        try {
            return new String(str.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

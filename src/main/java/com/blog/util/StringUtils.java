package com.blog.util;

import java.util.UUID;

public class StringUtils {

    public static String generateString() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}

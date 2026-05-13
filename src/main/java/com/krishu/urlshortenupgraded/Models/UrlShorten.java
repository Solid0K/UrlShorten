package com.krishu.urlshortenupgraded.Models;

import java.util.Random;

public class UrlShorten {
    private static final String CHAR_SET =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int LENGTH = 6;

    private static final Random random = new Random();

    public static String generate() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < LENGTH; i++) {
            sb.append(
                    CHAR_SET.charAt(
                            random.nextInt(CHAR_SET.length())
                    )
            );
        }

        return sb.toString();
    }
}

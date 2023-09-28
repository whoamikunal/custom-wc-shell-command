package com.commands.app.service;

import com.commands.app.enums.InputType;
import com.commands.app.enums.Options;

import java.nio.charset.StandardCharsets;
import java.util.*;

public final class Operations {
    private Operations() {
    }

    public static String operate(byte[] bytes, List<Options> options, InputType inputType, String fileName) {
        options.sort(Comparator.comparingInt(Options::getOrder));
        String text = new String(bytes, StandardCharsets.UTF_8);
        StringBuilder message = new StringBuilder();
        for (Options options1 : options) {
            long temp = calculate(bytes, text, options1);
            message.append(temp);
            message.append(" ");
        }
        if (inputType == InputType.FILE) {
            message.append(fileName);
        }
        return message.toString().trim();
    }

    static long calculate(byte[] bytes, String str, Options options) {
        switch (options) {
            case C:
                return bytes.length;
            case M:
                return str.length();
            case L:
                long count = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '\n') count++;
                }
                return count;
            case W:
                return Arrays.stream(str.split("\\s+")).count();
            default:
                System.out.println(options.getValue() + " -- illegal option");
        }
        return -1;
    }
}

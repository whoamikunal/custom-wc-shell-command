package com.commands.app.enums;

import com.commands.app.exceptions.IllegalOption;

public enum Options {
    C("c", 2),
    L("l", 0),
    M("m", 2),
    W("w", 1);
    private final String value;
    private final int order;

    Options(String value, int order) {
        this.value = value;
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public int getOrder() {
        return order;
    }

    public static Options getOptionsFromValue(String v) {
        for (Options options : Options.values()) {
            if (v.equals(options.value)) return options;
        }
        throw new IllegalOption("wc illegal option -- " + v);
    }
}

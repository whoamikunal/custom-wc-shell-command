package com.commands.app.dto;

public class Pair {
    private byte[] bytes;
    private String fileName;

    public Pair(byte[] bytes, String fileName) {
        this.bytes = bytes;
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getFileName() {
        return fileName;
    }
}

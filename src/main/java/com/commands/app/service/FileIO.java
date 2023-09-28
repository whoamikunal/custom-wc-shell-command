package com.commands.app.service;

import com.commands.app.dto.Pair;
import com.commands.app.exceptions.FileException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileIO {
    private FileIO() {
    }

    public static Pair getFileContent(String filePath) {
        try {
//            StringBuilder stringBuilder = new StringBuilder();
            Path path = Paths.get(filePath);
            System.out.println("Count " + Files.size(path));
            byte[] fileBytes = Files.readAllBytes(path);
//            stringBuilder.append(new String(fileBytes, StandardCharsets.UTF_8));
//            return new String[] {stringBuilder.toString(), path.getFileName().toString() };
            return new Pair(fileBytes, path.getFileName().toString());
        } catch (IOException e) {
            throw new FileException("No file found with path : " + filePath);
        }
    }
}

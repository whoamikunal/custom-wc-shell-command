package com.commands.app;


import com.commands.app.dto.Pair;
import com.commands.app.enums.InputType;
import com.commands.app.enums.Options;
import com.commands.app.service.FileIO;
import com.commands.app.service.Operations;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class App
{
    private static final Set<Options> AVAILABLE_OPTIONS = new HashSet<>();
    private static final Boolean MULTIBYTE_CHARACTER_SUPPORTED_BY_LOCALE;

    static {
        AVAILABLE_OPTIONS.add(Options.C);
        AVAILABLE_OPTIONS.add(Options.L);
        AVAILABLE_OPTIONS.add(Options.W);
    }

    static {
        String encoding = Charset.defaultCharset().name();
        MULTIBYTE_CHARACTER_SUPPORTED_BY_LOCALE = encoding.contains("UTF");
    }
    public static void main( String[] args )
    {
        try {
            String message = core(args);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void processOptions(Set<Options> options, Options option) {
        if (!MULTIBYTE_CHARACTER_SUPPORTED_BY_LOCALE && option == Options.M) {
            option = Options.C;
        }
        switch (option) {
            case W:
                options.add(option);
                break;
            case L:
                options.add(option);
                break;
            case M:
                options.remove(Options.C);
                options.add(option);
                break;
            case C:
                options.remove(Options.M);
                options.add(option);
                break;
            default:
                return;
        }
    }

    static String core(String ...args) {
        Set<Options> options = new HashSet<>();
        Set<String> filePaths = new HashSet<>();
        for (String s : args) {
            if (s.startsWith("-") && s.length() > 1) {
                String tmp = s.substring(1);
                String[] characters = tmp.split("");
                for (String character : characters) {
                    Options options1 = Options.getOptionsFromValue(character);
                    processOptions(options, options1);
                }
            } else {
                filePaths.add(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        Set<Options> optionsSet = options.size() == 0 ? AVAILABLE_OPTIONS : options;
        if (filePaths.size() == 0) {
            try(InputStream inputStream = System.in;
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                byte[] allBytes = byteArrayOutputStream.toByteArray();
                sb.append(Operations.operate(allBytes, new ArrayList<>(optionsSet), InputType.STDIN, null));
            } catch (IOException e) {
                System.out.println("exception while reading input");
            }
        } else {
            for (String path : filePaths) {
                Pair temp = FileIO.getFileContent(path);
                sb.append(Operations.operate(temp.getBytes(), new ArrayList<>(optionsSet), InputType.FILE, temp.getFileName()));
                sb.append("\n");
            }
        }

        if (sb.charAt(sb.length() - 1) == '\n') sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

package com.sachin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class DocumentGenerator {

    public static void main(String[] args) throws Exception {
        int sizeOfFileInMB = 3;
        int bytes = sizeOfFileInMB * 1024 * 1024;
        File file = new File("bigfile.txt");
        try(PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            byte[] myByteArray = new byte[bytes];
            Random random = new Random();
            for (int i = 0; i < bytes; i++) {
                myByteArray[i] = (byte) ('a' + random.nextInt(26));
            }
            writer.write(new String(myByteArray, StandardCharsets.UTF_8));
        } catch (Exception e) {
            // do something
        }
        System.out.println("File generated at location " + file.getAbsolutePath());
    }
}

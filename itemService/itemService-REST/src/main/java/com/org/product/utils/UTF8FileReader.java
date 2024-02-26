package com.org.product.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface UTF8FileReader {

    /**
     * Reads a file as UTF-8 and returns it as a String. By default a file should be located under the 'resources'
     * directory.
     * @param fileName The name (and path) of the file to read.
     * @return The contents of the file.
     * @throws IOException
     */
    default String readFile(String fileName) throws IOException {
        return this.readFile(fileName, null);
    }

    /**
     * Reads a file and returns it as a String. The file should be located under the 'resources' directory.
     * @param fileName The name (and path) of the file to read.
     * @param charset  The character set to use for file reading. If not given (<code>null</code>), will default to
     *                 'UTF-8'. Could also be e.g. "ISO-8859-1";
     * @return The contents of the file.
     * @throws IOException
     */
    default String readFile(String fileName, Charset charset) throws IOException {
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        try (var is = JsonReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IOException("File not found: " + fileName);
            }
            var resultStringBuilder = new StringBuilder();
            try (var br = new BufferedReader(new InputStreamReader(is, charset))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            return resultStringBuilder.toString();
        }
    }
}

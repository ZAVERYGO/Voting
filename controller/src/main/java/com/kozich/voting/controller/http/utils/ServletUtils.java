package com.kozich.voting.controller.http.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class ServletUtils {

    public static void writeBrakeLine(Writer writer) throws IOException {
        writer.write("-----------------------------------</br>\n");
    }

    public static void writeTo(List<Map.Entry<String, Integer>> data, Writer writer) throws IOException {
        for (Map.Entry<String, Integer> line : data) {
            writer.write(line.getKey() + ": " + line.getValue() + "</br>\n");
        }
    }

    public static void writeTo(Map<String, String> data, Writer writer) throws IOException {
        for (Map.Entry<String, String> line : data.entrySet()) {
            writer.write(line.getKey() + ": " + line.getValue() + "</br>\n");
        }
    }
}

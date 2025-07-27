package com.avnishgamedev.texteditor;

import android.content.Context;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
    public static String readTextFromUri(Context c, Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getContentResolver().openInputStream(uri)));
            String line;
            while ((line=reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new IOException("Could not open file", e);
        }
    }

    public static void writeTextToUri(Context c, Uri uri, String text) throws IOException {
        try {
            c.getContentResolver().openOutputStream(uri).write(text.getBytes());
        } catch (Exception e) {
            throw new IOException("Could not write to file", e);
        }
    }
}

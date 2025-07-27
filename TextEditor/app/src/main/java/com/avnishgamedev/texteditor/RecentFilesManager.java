package com.avnishgamedev.texteditor;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class RecentFilesManager {
    private static final String PREFS = "RecentFilesPrefs";
    private static final String KEY_RECENT_FILES = "recentFiles";

    public static void addRecentFile(Context context, String filePath) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        Set<String> recentFiles = prefs.getStringSet(KEY_RECENT_FILES, new HashSet<>());
        recentFiles.add(filePath);
        prefs.edit().putStringSet(KEY_RECENT_FILES, recentFiles).apply();
    }

    public static Set<String> getRecentFiles(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return prefs.getStringSet(KEY_RECENT_FILES, new HashSet<>());
    }
}

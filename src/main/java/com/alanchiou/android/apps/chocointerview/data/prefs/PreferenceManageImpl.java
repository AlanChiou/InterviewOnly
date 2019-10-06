package com.alanchiou.android.apps.chocointerview.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.Nullable;

public class PreferenceManageImpl implements PreferenceManager {

    private static final String PREFS_APP = "app";
    private static final String KEY_LAST_SEARCH = "last_search";

    private final SharedPreferences sharedPreferences;

    public PreferenceManageImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE);
    }

    @Override
    public void setLastSearch(String query) {
        if (TextUtils.isEmpty(query)) {
            sharedPreferences.edit().remove(KEY_LAST_SEARCH).apply();
            return;
        }

        sharedPreferences.edit().putString(KEY_LAST_SEARCH, query).apply();
    }

    @Nullable
    @Override
    public String getLastSearch() {
        if (!sharedPreferences.contains(KEY_LAST_SEARCH)) {
            return null;
        }

        return sharedPreferences.getString(KEY_LAST_SEARCH, /* defValue= */null);
    }
}

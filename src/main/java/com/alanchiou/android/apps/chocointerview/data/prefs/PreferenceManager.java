package com.alanchiou.android.apps.chocointerview.data.prefs;

import androidx.annotation.Nullable;

public interface PreferenceManager {

    /**
     * Saves the query of the last search if user leaves within a search result.
     */
    void setLastSearch(String query);

    /**
     * Returns the query of the last search if user leaves within a search result.
     */
    @Nullable
    String getLastSearch();
}

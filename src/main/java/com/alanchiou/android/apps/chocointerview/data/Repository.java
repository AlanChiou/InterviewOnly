package com.alanchiou.android.apps.chocointerview.data;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.alanchiou.android.apps.chocointerview.data.prefs.PreferenceManageImpl;
import com.alanchiou.android.apps.chocointerview.data.prefs.PreferenceManager;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * A class represents the repository of this application to access the data and preferences.
 */
public final class Repository {

    private static Repository instance;
    private final Context appContext;
    private final ListeningExecutorService listeningExecutorService;
    private final AppDatabase database;
    private final PreferenceManager preferenceManager;

    public synchronized static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context);
        }

        return instance;
    }

    private Repository(Context context) {
        appContext = context.getApplicationContext();
        listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        database = Room.databaseBuilder(appContext, AppDatabase.class, AppDatabase.DB_NAME).build();
        preferenceManager = new PreferenceManageImpl(appContext);
    }

    /**
     * Returns the {@link LiveData} of all dramas in {@link AppDatabase}.
     */
    public LiveData<List<Drama>> queryDramas() {
        return database.dramaDao().loadAllDramas();
    }

    /**
     * Returns the {@link LiveData} of all dramas in {@link AppDatabase} by a specific keyword.
     */
    public LiveData<List<Drama>> queryDramasByKeyword(String keyword) {
        return database.dramaDao().loadDramasByKeyword(keyword);
    }

    /**
     * Inserts or updates a list of {@link Drama} into {@link AppDatabase}.
     */
    public void insertOrUpdateDramas(List<Drama> dramas) {
        listeningExecutorService
                .submit(() -> database.dramaDao().insertDramas(dramas.toArray(new Drama[0])));
    }

    /**
     * Returns the {@link LiveData} of a specific drama in {@link AppDatabase} by the specific id.
     */
    public LiveData<Drama> queryDrama(int id) {
        return database.dramaDao().loadDrama(id);
    }

    /**
     * Saves the query of the last search if user leaves within a search result.
     */
    public void setLastSearch(String query) {
        preferenceManager.setLastSearch(query);
    }

    /**
     * Returns the query of the last search if user leaves within a search result.
     */
    @Nullable
    public String getLastSearch() {
        return preferenceManager.getLastSearch();
    }
}

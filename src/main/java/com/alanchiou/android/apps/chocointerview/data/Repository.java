package com.alanchiou.android.apps.chocointerview.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

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
    }

    public LiveData<List<Drama>> queryDramas() {
        return database.dramaDao().loadAllDramas();
    }

    public void insertOrUpdateDramas(List<Drama> dramas) {
        listeningExecutorService
                .submit(() -> database.dramaDao().insertDramas(dramas.toArray(new Drama[0])));
    }
}

package com.alanchiou.android.apps.chocointerview.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Drama.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

  static final String DB_NAME = "app";

  public abstract DramaDao dramaDao();
}
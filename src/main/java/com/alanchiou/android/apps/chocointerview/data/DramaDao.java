package com.alanchiou.android.apps.chocointerview.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DramaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDramas(Drama... dramas);

    @Query("SELECT * FROM dramas")
    LiveData<List<Drama>> loadAllDramas();

    @Query("SELECT * FROM dramas WHERE id = :id LIMIT 1")
    LiveData<Drama> loadDrama(int id);

}

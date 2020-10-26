package com.andrew.bakingapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.andrew.bakingapp.database.entity.BakeEntity;

import java.util.List;

@Dao
public interface BakeDao {

    @Insert
    void insertToDB(BakeEntity bakeEntity);

    @Delete
    void deleteFromDB(BakeEntity bakeEntity);

    @Query("SELECT * FROM BakeEntity WHERE id = :id ")
    BakeEntity selectDB(int id);

    @Query("SELECT * FROM BakeEntity")
    LiveData<List<BakeEntity>> selectAllFavorite();
}

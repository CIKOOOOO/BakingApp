package com.andrew.bakingapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.andrew.bakingapp.utils.Constant;

@Entity(tableName = Constant.TABLE_NAME)
public class BakeEntity {

    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

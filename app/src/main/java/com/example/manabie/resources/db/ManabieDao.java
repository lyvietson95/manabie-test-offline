package com.example.manabie.resources.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.manabie.resources.model.ManabieItem;

import java.util.List;

@Dao
public interface ManabieDao extends BaseDao<ManabieItem> {
    @Query("SELECT * FROM manabieItem_table")
    LiveData<List<ManabieItem>> getAllManabie();
}

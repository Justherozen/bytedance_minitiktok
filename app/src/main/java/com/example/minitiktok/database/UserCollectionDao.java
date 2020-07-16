package com.example.minitiktok.database;

import android.net.Uri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface UserCollectionDao {
    @Query("SELECT * FROM collectvideo")
    List<UserCollectionEntity> loadAll();
    @Insert
    void addCollectVideo(UserCollectionEntity entity);
    @Delete
    void deleteCollectVideo(UserCollectionEntity entity);

}

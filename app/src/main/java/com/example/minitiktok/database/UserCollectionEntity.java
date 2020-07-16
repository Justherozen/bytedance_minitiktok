package com.example.minitiktok.database;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "collectvideo")//@Entity是实体的标识
public class UserCollectionEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String UserId;
    public String videoUri;
    public String imagecoverUri;

    public UserCollectionEntity(String UserId, String videoUri,String imagecoverUri) {
        this.UserId = UserId;
        this.videoUri = videoUri;
        this.imagecoverUri=imagecoverUri;
    }

}


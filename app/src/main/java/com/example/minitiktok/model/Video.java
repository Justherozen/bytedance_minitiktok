package com.example.minitiktok.model;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("student_id") public String studentId;
    @SerializedName("user_name") public String userName;
    @SerializedName("image_url") public String imageUrl;
    @SerializedName("video_url") public String videoUrl;
    @SerializedName("image_w") public int imageWidth;
    @SerializedName("image_h") public int imageHeight;

    public String getStudentId() {
        return studentId;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }
}

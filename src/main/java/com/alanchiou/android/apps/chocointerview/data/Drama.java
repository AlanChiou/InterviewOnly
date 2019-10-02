package com.alanchiou.android.apps.chocointerview.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.time.Instant;

/**
 * A class represents the drama data from JSON format.
 */
@Entity(tableName = "dramas")
public final class Drama {

  @PrimaryKey
  @SerializedName("drama_id")
  public int id;

  @ColumnInfo(name = "name")
  @SerializedName("name")
  public String name;

  @ColumnInfo(name = "total_views")
  @SerializedName("total_views")
  public long totalViews;

  @ColumnInfo(name = "created_at")
  @SerializedName("created_at")
  public Instant createdAt;

  @ColumnInfo(name = "thumb")
  @SerializedName("thumb")
  public String thumbUrl;

  @ColumnInfo(name = "rating")
  @SerializedName("rating")
  public float rating;

  /**
   * Returns the id of the drama.
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the name of the drama.
   */
  public String getName() {
    return name;
  }


  /**
   * Returns the total views of the drama.
   */
  public long getTotalViews() {
    return totalViews;
  }

  /**
   * Returns the {@link Instant} for the created at of the drama.
   */
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Returns the url string of the thumb of the drama.
   */
  public String getThumbUrl() {
    return thumbUrl;
  }

  /**
   * Returns the rating of the drama.
   */
  public float getRating() {
    return rating;
  }
}

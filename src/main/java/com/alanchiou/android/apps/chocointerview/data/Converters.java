package com.alanchiou.android.apps.chocointerview.data;

import androidx.room.TypeConverter;

import java.time.Instant;

public class Converters {

    @TypeConverter
    public static Instant fromTimestamp(Long value) {
        return value == null ? null : Instant.ofEpochMilli(value);
    }

    @TypeConverter
    public static Long instantToTimestamp(Instant instant) {
        return instant == null ? null : instant.toEpochMilli();
    }
}
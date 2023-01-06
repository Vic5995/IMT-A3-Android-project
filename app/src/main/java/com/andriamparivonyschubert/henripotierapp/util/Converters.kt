package com.andriamparivonyschubert.henripotierapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(str: String): List<String> {
        val gson = Gson()
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(str, listType)
    }

}
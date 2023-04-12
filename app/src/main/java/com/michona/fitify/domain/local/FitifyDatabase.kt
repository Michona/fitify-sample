package com.michona.fitify.domain.local

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.michona.fitify.domain.data.dtos.LocalExercise
import com.michona.fitify.domain.local.api.ExercisesDao
import java.lang.reflect.Type

@Database(entities = [LocalExercise::class], version = 1)
@TypeConverters(Converters::class)
abstract class FitifyDatabase : RoomDatabase() {

    abstract fun exercisesDao(): ExercisesDao

    companion object {
        @Volatile
        private var INSTANCE: FitifyDatabase? = null

        fun build(context: Context): FitifyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FitifyDatabase::class.java,
                    "fitify.main.database",
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}

object Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

package com.example.storygenerator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.storygenerator.data.modeles.ContentEntity

@Database(entities = [ContentEntity::class],version = 1)
abstract class dbAbstract: RoomDatabase(){
    abstract fun contentsDao(): ContentDao

    companion object {
        fun getDatabase(context: Context): dbAbstract {
            return  Room.databaseBuilder(
                context.applicationContext,
                dbAbstract::class.java,
                "myDB"
            ).build()
        }
    }
}
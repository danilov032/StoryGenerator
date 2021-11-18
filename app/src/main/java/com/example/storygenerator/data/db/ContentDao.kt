package com.example.storygenerator.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storygenerator.data.modeles.ContentEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ContentDao {

    @Query("SELECT * FROM ContentEntity")
    fun getContents(): Single<List<ContentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContents(contents: List<ContentEntity>): Completable

    @Query("DELETE FROM ContentEntity")
    fun deleteAll(): Completable
}
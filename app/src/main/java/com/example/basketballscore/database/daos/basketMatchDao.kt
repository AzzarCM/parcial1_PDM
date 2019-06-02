package com.example.basketballscore.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basketballscore.database.entities.basketMatch

@Dao
interface basketMatchDao {
    @Query("SELECT * FROM basketMatch_table")
    fun getAllMatches() : LiveData<List<basketMatch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: basketMatch)

    @Query("DELETE FROM basketMatch_table")
    fun nukeAllMatches()
}
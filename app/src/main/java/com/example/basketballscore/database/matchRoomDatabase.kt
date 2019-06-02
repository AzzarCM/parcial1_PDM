package com.example.basketballscore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basketballscore.database.daos.basketMatchDao
import com.example.basketballscore.database.entities.basketMatch


@Database(entities = [basketMatch::class], version = 1)
public abstract class matchRoomDatabase : RoomDatabase(){

    abstract fun basketMatchDao() :basketMatchDao
    companion object {
        @Volatile
        private var INSTANCE: matchRoomDatabase? = null

        fun getDatabase(context:Context
        ): matchRoomDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    matchRoomDatabase::class.java,
                    "word_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
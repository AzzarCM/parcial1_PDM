package com.example.basketballscore.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.basketballscore.database.daos.basketMatchDao
import com.example.basketballscore.database.entities.basketMatch

class matchRepository(private val basketMatchDao: basketMatchDao){

    val allMatches : LiveData<List<basketMatch>> = basketMatchDao.getAllMatches()

    @WorkerThread
    suspend fun insert(basketMatch: basketMatch){
        basketMatchDao.insert(basketMatch)
    }
}
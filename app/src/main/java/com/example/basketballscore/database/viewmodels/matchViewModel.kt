package com.example.basketballscore.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basketballscore.database.daos.basketMatchDao
import com.example.basketballscore.database.entities.basketMatch
import com.example.basketballscore.database.matchRoomDatabase
import com.example.basketballscore.database.repositories.matchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class matchViewModel(application: Application): AndroidViewModel(application){
    private val repository : matchRepository
    val allMatches: LiveData<List<basketMatch>>

    val scoreTeamA = MutableLiveData<Int>().apply { postValue(0) }
    val scoreTeamB = MutableLiveData<Int>().apply { postValue(0) }

    init {
        val basketMatchDao = matchRoomDatabase.getDatabase(application).basketMatchDao()
        repository = matchRepository(basketMatchDao)

        allMatches = repository.allMatches
    }

    fun insert(basketMatch: basketMatch) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(basketMatch)
    }
}
package com.example.basketballscore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.basketballscore.database.entities.basketMatch
import com.example.basketballscore.database.viewmodels.matchViewModel
import com.example.basketballscore.fragment.FragmentMatchesList
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : matchViewModel
    private lateinit var fragment : FragmentMatchesList
    val calendar: Calendar = java.util.Calendar.getInstance()
    val format = SimpleDateFormat("dd/MM/yyyy h:mm a")

    var countA : Int = 0
    var countB : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(matchViewModel::class.java)
        fragment = FragmentMatchesList.newInstance()

        val scoreAObserver = Observer<Int>{ newValueA ->
            tv_score_team_a.text = newValueA.toString()
        }

        val scoreBObserver = Observer<Int> { newValueB ->
            tv_score_team_b.text = newValueB.toString()
        }

        viewModel.scoreTeamA.observe(this, scoreAObserver)
        viewModel.scoreTeamB.observe(this,scoreBObserver)

        val buttonSaveMatch = findViewById<Button>(R.id.bt_save)
        buttonSaveMatch.setOnClickListener { click() }
        all_matches.setOnClickListener {
            changeFragment(R.id.lol, fragment)
        }
        // TEAM A
        bt_team_a_3_p.setOnClickListener {
            countA +=3
            viewModel.scoreTeamA.value = countA
        }

        bt_team_a_2_p.setOnClickListener {
            countA +=2
            viewModel.scoreTeamA.value = countA
        }
        bt_team_a_free_throw.setOnClickListener {
            countA++
            viewModel.scoreTeamA.value = countA
        }

        // TEAM B
        bt_team_b_3_p.setOnClickListener {
            countB +=3
            viewModel.scoreTeamB.value = countB
        }

        bt_team_b_2_p.setOnClickListener {
            countB +=2
            viewModel.scoreTeamB.value = countB
        }
        bt_team_b_free_throw.setOnClickListener {
            countB++
            viewModel.scoreTeamB.value = countB
        }
    }

    fun click(){
        if(TextUtils.isEmpty(et_teamA.text) && TextUtils.isEmpty(et_teamB.text)){
            Toast.makeText(applicationContext, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }else{
            var teamA = et_teamA.text.toString()
            var teamB = et_teamB.text.toString()
            var scoreA = tv_score_team_a.text.toString()
            var scoreB = tv_score_team_b.text.toString()
            var winner:String

            winner = if(scoreA.toInt() > scoreB.toInt()){
                teamA
            }else{
                teamB
            }
            var concat = "Fecha: " + format.format(calendar.time)
            var match = basketMatch(teamA, teamB, scoreA.toInt(), scoreB.toInt(), concat, "5:12", winner)

            viewModel.insert(match)
            Toast.makeText(applicationContext, "Inserted", Toast.LENGTH_LONG).show()
        }
    }
    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }



}

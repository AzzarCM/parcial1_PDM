package com.example.basketballscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basketballscore.R
import com.example.basketballscore.database.entities.basketMatch
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.match_list_model.view.*

class basketMatchAdapter() : RecyclerView.Adapter<basketMatchAdapter.ViewHolder>(){

    private var matches = emptyList<basketMatch>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_list_model, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = matches[position]
        holder.bind(current)
    }
    internal fun setMatches(matches : List<basketMatch>){
        this.matches = matches
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: basketMatch) = with(itemView){
            tv_team_a.text = item.teamNameA
            tv_score_a.text = item.scoreTeamA.toString()
            tv_score_b.text = item.scoreTeamB.toString()
            tv_team_b.text = item.teamNameB
            tv_winner.text = "The winner is " + item.winner
            tv_date.text = item.date

        }
    }
}
package com.example.basketballscore.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basketballscore.R
import com.example.basketballscore.adapter.basketMatchAdapter
import com.example.basketballscore.database.viewmodels.matchViewModel
import kotlinx.android.synthetic.main.activity_fragment_matches_list.view.*

class FragmentMatchesList : Fragment() {
    lateinit var MatchViewModel: matchViewModel
    lateinit var matchAdapter: basketMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment_matches_list, container, false)
        MatchViewModel = ViewModelProviders.of(this).get(matchViewModel::class.java)
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        matchAdapter = basketMatchAdapter()
        view.rv_list_matches.adapter = matchAdapter

        MatchViewModel.allMatches.observe(this, Observer { matches->
            matches?.let { matchAdapter.setMatches(it) }
        })

        view.rv_list_matches.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }
    companion object {
        fun newInstance(): FragmentMatchesList{
            val newFragment = FragmentMatchesList()
            return newFragment
        }
    }


}

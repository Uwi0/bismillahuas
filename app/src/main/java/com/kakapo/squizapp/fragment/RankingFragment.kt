package com.kakapo.squizapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.kakapo.squizapp.R
import com.kakapo.squizapp.adapter.RankingAdapter
import com.kakapo.squizapp.common.Common
import com.kakapo.squizapp.interfaceApp.RankingCallback
import com.kakapo.squizapp.model.QuestionScore
import com.kakapo.squizapp.model.Ranking

class RankingFragment : Fragment() {

    private lateinit var myFragment: View
    private lateinit var rankingList: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RankingAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var questionScore: DatabaseReference
    lateinit var rankingTable: DatabaseReference

    var sum: Long = 0

    companion object{
        fun newInstance() : RankingFragment {
            return RankingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        questionScore = database.reference.child("Question_Score")
        rankingTable = database.reference.child("Rangking")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        myFragment = inflater.inflate(R.layout.fragment_ranking, container, false)

        rankingList = myFragment.findViewById(R.id.rankingList)
        layoutManager = LinearLayoutManager(activity)
        rankingList.setHasFixedSize(true)
        rankingList.layoutManager = layoutManager

        updateScore(Common.currentUser.userName!!, object : RankingCallback<Ranking> {
            override fun callBack(ranking: Ranking) {
                rankingTable.child(ranking.userName).setValue(ranking)
            }

        })
        val options = FirebaseRecyclerOptions.Builder<Ranking>()
                .setQuery(rankingTable, Ranking::class.java) // lek gak rangking score / question socre
                .setLifecycleOwner(this)
                .build()

        adapter = RankingAdapter(options)
        rankingList.adapter = adapter
        return myFragment
    }


    private fun updateScore(userName: String, callback: RankingCallback<Ranking>){
        questionScore.orderByChild("user").equalTo(userName)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (data in snapshot.children) {
                            val ques = data.getValue(QuestionScore::class.java)!!
                            sum += ques.Score!!.toInt()
                        }

                        val ranking = Ranking(userName, sum)
                        callback.callBack(ranking)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
    }
}
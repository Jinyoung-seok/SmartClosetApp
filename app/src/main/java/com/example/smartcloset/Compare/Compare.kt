package com.example.smartcloset.Compare

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcloset.Main.MainActivity
import com.example.smartcloset.R

class Compare: Fragment() {
    var datalist =ArrayList<Int>()
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.compare, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var compareRecyclerView:RecyclerView? = getView()?.findViewById(R.id.compare_recycler)
        for(i in 0..7){
            datalist.add(R.drawable.p1)
        }

        val adapter =RecyclerAdapter(mainActivity, R.layout.compare_item,datalist)
        compareRecyclerView?.adapter = adapter

    }
}
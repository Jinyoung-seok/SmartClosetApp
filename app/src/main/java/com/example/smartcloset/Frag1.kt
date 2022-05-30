package com.example.smartvending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.smartcloset.R
import kotlinx.android.synthetic.main.frag1.*

class Frag1: Fragment {
    lateinit var content:String
    constructor(content:String){
        this.content = content
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { //View가 만들어질 때 frag1로 화면을 만들어 넘겨줌 
        return inflater.inflate(R.layout.frag1, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  //View가 만들어졌을 때, 테스트 삼아 화면의 text를 바꿔본 것
        super.onViewCreated(view, savedInstanceState)
        frag1_txt.text = content
    }
}
package com.example.smartcloset.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.smartcloset.R
import com.example.smartcloset.network.MyMqtt
import kotlinx.android.synthetic.main.register.*

class Register: AppCompatActivity(), View.OnClickListener {
    val sub_topic = "iot/#"
    val server_uri = "tcp://192.168.200.107:1883" //broker의 ip와 port
    var mymqtt: MyMqtt? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        //Mqtt통신을 수행항 Mqtt객체를 생성
        mymqtt = MyMqtt(this, server_uri)

        submit_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var data: String = ""
        if(v?.id== R.id.submit_register) {
            if (pw_register.text == pwcheck_register.text) {
                Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                data = "${id_register.text}, ${pw_register.text}, ${name_register.text}, ${birthday_register.text}"
                mymqtt?.publish("register",data)
            }
        }
    }
}

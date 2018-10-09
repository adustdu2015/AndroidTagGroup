package me.gujun.android.taggroup.demo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.test_kotlin.*
import me.gujun.android.taggroup.demo.R
import me.gujun.android.taggroup.demo.core.preference.Preference

class TestKotlinActivity : AppCompatActivity() {
     val TAG="TestKotlinActivity"
    protected var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_kotlin)
        btn_login.setOnClickListener { isLogin = true
            Log.e(TAG, isLogin.toString())
        }

    }
}

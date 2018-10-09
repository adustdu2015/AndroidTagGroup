package me.gujun.android.taggroup.demo.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fresco.*
import me.gujun.android.taggroup.demo.R

class FrescoActivity : AppCompatActivity() {

    private var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)
        mContext = this
        Picasso.with(mContext).load("https://avatars0.githubusercontent.com/u/17478136?s=460&v=4")
                .into(iv_picasso)
        toast("测试代码")
    }
    fun toast(test:String){
        Toast.makeText(applicationContext,test, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        toast("good")
    }
}

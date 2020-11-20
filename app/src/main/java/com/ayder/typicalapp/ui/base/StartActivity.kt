package com.ayder.typicalapp.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ayder.typicalapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        findViewById<View>(R.id.btn_daily_image_demo).setOnClickListener(this)
        findViewById<View>(R.id.btn_mock_user_demo).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_daily_image_demo -> {
                val startIntent = Intent(this, NasaDailyPicturesActivity::class.java)
                startActivity(startIntent)
            }
            R.id.btn_mock_user_demo -> {
                val startIntent = Intent(this, UsersActivity::class.java)
                startActivity(startIntent)
            }
        }
    }
}
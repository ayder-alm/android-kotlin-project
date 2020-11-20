package com.ayder.typicalapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ayder.typicalapp.data.model.NasaDailyPicture

const val EXTRA_PICTURE_KEY = "extra_picture_key"

class NasaDailyPictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val picture = intent.getParcelableExtra<NasaDailyPicture>(EXTRA_PICTURE_KEY)
        Toast.makeText(this, "${picture?.title}", Toast.LENGTH_SHORT).show()
    }
}
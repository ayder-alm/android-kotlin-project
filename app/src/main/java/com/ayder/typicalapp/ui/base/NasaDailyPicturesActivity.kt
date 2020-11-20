package com.ayder.typicalapp.ui.base

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayder.typicalapp.R
import com.ayder.typicalapp.ui.main.adapter.PictureItemClickListener
import com.ayder.typicalapp.ui.main.adapter.PictureRecyclerAdapter
import com.ayder.typicalapp.ui.main.viewmodel.DailyPicturesActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

private val DATE_PICKER_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

@AndroidEntryPoint
class NasaDailyPicturesActivity : AppCompatActivity(),
    View.OnClickListener,
    PictureItemClickListener {

    private lateinit var activityViewModel: DailyPicturesActivityViewModel
    private lateinit var mEtDateFrom: EditText
    private lateinit var mEtDateTo: EditText
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mRecyclerAdapter: PictureRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nasa_pictures_activity)
        mProgressBar = findViewById(R.id.progress_bar)
        findViewById<View>(R.id.btn_load_pictures).setOnClickListener(this)
        mEtDateFrom = findViewById(R.id.et_date_from)
        mEtDateTo = findViewById(R.id.et_date_to)
        mEtDateFrom.setOnClickListener(this)
        mEtDateTo.setOnClickListener(this)

        mRecyclerView = findViewById(R.id.rv_picture_list)
        initRecycleView()
        activityViewModel = ViewModelProvider(this).get(DailyPicturesActivityViewModel::class.java)
        setViewModelObservers(activityViewModel)
    }

    private fun initRecycleView() {
        mRecyclerAdapter = PictureRecyclerAdapter(this@NasaDailyPicturesActivity, this)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NasaDailyPicturesActivity)
        mRecyclerView.adapter = this.mRecyclerAdapter
        mRecyclerView.addItemDecoration(
            DividerItemDecoration(this@NasaDailyPicturesActivity, DividerItemDecoration.VERTICAL)
        )
    }

    private fun setViewModelObservers(activityViewModel: DailyPicturesActivityViewModel) {
        activityViewModel.pictures.observe(this@NasaDailyPicturesActivity) { pictures ->
            mRecyclerAdapter.updateData(pictures)
        }
        activityViewModel.dateFrom.observe(this@NasaDailyPicturesActivity) {
            mEtDateFrom.setText(DATE_PICKER_FORMAT.format(it))
        }
        activityViewModel.dateTo.observe(this@NasaDailyPicturesActivity) {
            mEtDateTo.setText(DATE_PICKER_FORMAT.format(it))
        }
        activityViewModel.isLoading.observe(this@NasaDailyPicturesActivity) { isLoading ->
            mProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        activityViewModel.error.observe(this@NasaDailyPicturesActivity) { message ->
            message?.let {
                Toast.makeText(this@NasaDailyPicturesActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_load_pictures -> activityViewModel.loadDailyPictures()
            R.id.et_date_from -> handleFromDateClicked()
            R.id.et_date_to -> handleToDateClicked()
        }
    }

    private fun handleToDateClicked() {
        activityViewModel.dateTo.value?.let {
            startTimeDialog(it) { newDate: Date ->
                activityViewModel.dateTo.value = newDate
//              activityViewModel.loadDailyPictures()
            }
        }
    }

    private fun handleFromDateClicked() {
        activityViewModel.dateFrom.value?.let {
            startTimeDialog(it) { newDate: Date ->
                activityViewModel.dateFrom.value = newDate
//              activityViewModel.loadDailyPictures()
            }
        }
    }

    private fun startTimeDialog(date: Date, onSuccessHandler: (newDate: Date) -> Unit) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dateFromDialogListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                if (year < 1996) {
                    showWrongDateAlert()
                } else {
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    onSuccessHandler(calendar.time)
                }
            }
        DatePickerDialog(
            this@NasaDailyPicturesActivity,
            dateFromDialogListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showWrongDateAlert() {
        Toast.makeText(
            this@NasaDailyPicturesActivity,
            "Date should not be before january 1 1996",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, NasaDailyPictureActivity::class.java)
        val picture = mRecyclerAdapter.items[position]
        intent.putExtra(EXTRA_PICTURE_KEY, picture)
        startActivity(intent)
    }
}
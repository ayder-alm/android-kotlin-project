package com.ayder.typicalapp.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayder.typicalapp.R
import com.ayder.typicalapp.data.model.NasaDailyPicture
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nasa_picture_recycler_item.view.*

interface PictureItemClickListener {
    fun onItemClick(position: Int)
}

class PictureRecyclerAdapter(
        val context: Context,
        val pictureClickListener: PictureItemClickListener,
        var items: List<NasaDailyPicture> = emptyList()) :
        RecyclerView.Adapter<NasaPictureHolder>() {

        fun updateData(newItems: List<NasaDailyPicture>) {
            val oldItems = items
            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
                    PictureItemDiffCallback(oldItems, newItems)
            )
            items = newItems
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaPictureHolder {
        val view = LayoutInflater
                .from(context)
                .inflate(R.layout.nasa_picture_recycler_item, parent, false)
        return NasaPictureHolder(view, pictureClickListener)
    }

    override fun onBindViewHolder(holder: NasaPictureHolder, position: Int) {
        val item: NasaDailyPicture = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}

class NasaPictureHolder(v: View, private val pictureClickListener: PictureItemClickListener)
    : RecyclerView.ViewHolder(v), View.OnClickListener {

    private var mIvImage: ImageView? = null
    private var mTvDate: TextView? = null
    private var mTvTitle: TextView? = null

    init { //find view by id only once, not each bind
        mIvImage = itemView.iv_image
        mTvDate = itemView.tv_date
        mTvTitle = itemView.tv_title
    }

    fun bind(item: NasaDailyPicture) {
        Picasso
                .get()
                .load(item.url)
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(mIvImage)
        mIvImage?.setOnClickListener(this@NasaPictureHolder)
        mTvDate?.text = item.date
        mTvTitle?.text = item.title
    }

    override fun onClick(v: View?) {
        pictureClickListener.onItemClick(adapterPosition)
    }
}
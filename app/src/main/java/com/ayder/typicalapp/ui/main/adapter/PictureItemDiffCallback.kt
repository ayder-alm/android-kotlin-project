package com.ayder.typicalapp.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ayder.typicalapp.data.model.NasaDailyPicture

class PictureItemDiffCallback (val oldList: List<NasaDailyPicture>, val newList: List<NasaDailyPicture>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].date == newList[newItemPosition].date
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
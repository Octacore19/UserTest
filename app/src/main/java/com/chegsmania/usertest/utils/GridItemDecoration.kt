package com.chegsmania.usertest.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(gridSpacingPx: Int, gridSize: Int) : RecyclerView.ItemDecoration(){
    private val mGridSpacingPx = gridSpacingPx
    private val mGridSize = gridSize
    private var mNeedLeftSpacing = false
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val frameWidth = ((parent.width - mGridSpacingPx.toFloat() * (mGridSize - 1)) / mGridSize).toInt()
        val padding = parent.width / mGridSize - frameWidth
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        if (itemPosition < mGridSize){
            outRect.top = 0
        } else{
            outRect.top = mGridSpacingPx
        }
        if (itemPosition % mGridSize == 0){
            outRect.left = 0
            outRect.right = padding
            mNeedLeftSpacing = true
        } else if ((itemPosition + 1) % mGridSize == 0){
            outRect.left = padding
            outRect.right = 0
            mNeedLeftSpacing = false
        } else if (mNeedLeftSpacing){
            outRect.left = mGridSpacingPx - padding
            mNeedLeftSpacing = false
            if ((itemPosition + 2) % mGridSize == 0){
                outRect.right = mGridSpacingPx - padding
            } else {
                outRect.right = mGridSpacingPx / 2
            }
        } else if ((itemPosition + 2) % mGridSize == 0) {
            mNeedLeftSpacing = false
            outRect.left = mGridSpacingPx / 2
            outRect.right = mGridSpacingPx - padding
        } else{
            mNeedLeftSpacing = false
            outRect.left = mGridSpacingPx / 2
            outRect.right = mGridSpacingPx / 2
        }
        outRect.bottom = 0
    }
}
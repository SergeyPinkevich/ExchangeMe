package com.mockingbird.spinkevich.exchangeme.feature.exchange

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.mockingbird.spinkevich.exchangeme.R

class SwipeToDeleteCallback(
    private val deleteCallback: (Int) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val iconHeight = 30
    private val iconWidth = 15

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState === ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView

            drawBackgroundDelete(itemView, dX, canvas)
            drawBackgroundSwap(itemView, dX, canvas)
            drawIconDelete(itemView, dX, canvas)
            drawIconSwap(itemView, dX, canvas)
        } else {
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    private fun drawBackgroundDelete(itemView: View, dX: Float, canvas: Canvas) {
        val swipeValue = (dX / 5) * 2
        itemView.translationX = swipeValue

        val paint = Paint()
        val backgroundColor = ColorDrawable(itemView.resources.getColor(R.color.red))
        paint.color = backgroundColor.color
        val background = RectF(
            itemView.right.toFloat() + swipeValue,
            itemView.top.toFloat(),
            itemView.right.toFloat(),
            itemView.bottom.toFloat()
        )
        canvas.drawRect(background, paint)
    }

    private fun drawBackgroundSwap(itemView: View, dX: Float, canvas: Canvas) {
        val swipeValue = (dX / 5) * 2
        itemView.translationX = swipeValue

        val paint = Paint()
        val backgroundColor = ColorDrawable(itemView.resources.getColor(R.color.blue))
        paint.color = backgroundColor.color
        val background = RectF(
            itemView.right.toFloat() + swipeValue,
            itemView.top.toFloat(),
            itemView.right.toFloat() + swipeValue / 2,
            itemView.bottom.toFloat()
        )
        canvas.drawRect(background, paint)
    }

    private fun drawIconDelete(itemView: View, dX: Float,canvas: Canvas) {
        val icon = itemView.resources.getDrawable(R.drawable.ic_delete)
        val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight - iconHeight) / 2
        val iconBottom = iconTop + icon.intrinsicHeight + iconHeight
        val iconLeft = itemView.right + (dX / 5).toInt() + icon.intrinsicWidth - iconWidth
        val iconRight = itemView.right - icon.intrinsicWidth + iconWidth
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        icon.draw(canvas)
    }

    private fun drawIconSwap(itemView: View, dX: Float, canvas: Canvas) {
        val icon = itemView.resources.getDrawable(R.drawable.ic_swap)
        val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight - iconHeight) / 2
        val iconBottom = iconTop + icon.intrinsicHeight + iconHeight
        val iconLeft = itemView.right + (dX / 5).toInt() + (dX / 5).toInt() + icon.intrinsicWidth - iconWidth
        val iconRight = itemView.right + (dX / 5).toInt() - icon.intrinsicWidth + iconWidth
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        icon.draw(canvas)
    }
}
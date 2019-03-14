package com.mockingbird.spinkevich.exchangeme.feature.exchange

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.mockingbird.spinkevich.exchangeme.R

class SwipeToDeleteCallback(private val adapter: ExchangeAdapter) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val resources = adapter.context.resources

    private val iconSwap = resources.getDrawable(R.drawable.ic_swap)
    private val iconDelete = resources.getDrawable(R.drawable.ic_delete)
    private val backgroundSwap = ColorDrawable(resources.getColor(R.color.blue))
    private val backgroundDelete = ColorDrawable(resources.getColor(R.color.red))

    private val iconHeight = 30
    private val iconWidth = 15

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
    }

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
            drawSwapIcon(itemView, dX, canvas)
        } else {
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    private fun drawBackgroundDelete(itemView: View, dX: Float, canvas: Canvas) {
        val swipeValue = (dX / 5) * 2
        itemView.translationX = swipeValue

        val paint = Paint()
        paint.color = backgroundDelete.color
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
        paint.color = backgroundSwap.color
        val background = RectF(
            itemView.right.toFloat() + swipeValue,
            itemView.top.toFloat(),
            itemView.right.toFloat() + swipeValue / 2,
            itemView.bottom.toFloat()
        )
        canvas.drawRect(background, paint)
    }

    private fun drawIconDelete(itemView: View, dX: Float,canvas: Canvas) {
        val iconTop = itemView.top + (itemView.height - iconDelete.intrinsicHeight - iconHeight) / 2
        val iconBottom = iconTop + iconDelete.intrinsicHeight + iconHeight
        val iconMargin = (itemView.height - iconDelete.intrinsicHeight - iconWidth) / 2
        val iconLeft = itemView.right - iconMargin + (dX / 10).toInt() - iconDelete.intrinsicWidth - iconWidth
        val iconRight = itemView.right - iconMargin + (dX / 10).toInt() + iconWidth
        iconDelete.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        iconDelete.draw(canvas)
    }

    private fun drawSwapIcon(itemView: View, dX: Float, canvas: Canvas) {
        val iconTop = itemView.top + (itemView.height - iconSwap.intrinsicHeight - iconHeight) / 2
        val iconBottom = iconTop + iconSwap.intrinsicHeight + iconHeight
        val iconMargin = (itemView.height - iconSwap.intrinsicHeight - iconWidth) / 2
        val iconLeft = itemView.right - iconMargin + (dX / 5).toInt() - iconSwap.intrinsicWidth - iconWidth
        val iconRight = itemView.right - iconMargin + (dX / 5).toInt() + iconWidth
        iconSwap.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        iconSwap.draw(canvas)
    }
}
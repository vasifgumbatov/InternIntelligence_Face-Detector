package com.vasifgumbatov.graphic

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.vasifgumbatov.utils.CameraUtils

class RectangleOverlay(
    private val overlay: GraphicOverlay<*>,
    private val face: com.google.mlkit.vision.face.Face,
    private val rect: Rect
) : GraphicOverlay.Graphic(overlay) {

    private val boxPaint : Paint = Paint()

    init {
        boxPaint.color = Color.GREEN
        boxPaint.style = Paint.Style.STROKE
        boxPaint.strokeWidth = 3.0f
    }

    override fun draw(canvas: Canvas) {
        val rect = CameraUtils.calculateRect(
            overlay,
            rect.height().toFloat(),
            rect.width().toFloat(),
            face.boundingBox
        )
        canvas.drawRect(rect, boxPaint)

        face.allLandmarks.forEach{landmark ->
            val cx = translateX(landmark.position.x)
            val cy = translateY(landmark.position.y)
            canvas.drawCircle(cx, cy, 10.0f, boxPaint)
        }
    }
}
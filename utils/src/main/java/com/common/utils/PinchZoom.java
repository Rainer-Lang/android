package com.common.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

@SuppressLint({"NewApi", "FloatMath"})
public class PinchZoom implements OnTouchListener {

    // We can be in one of these 3 states
    static final int NONE = 0;
    int mode = NONE;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    // These matrices will be used to move and zoom image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    int r = 0;
    @SuppressWarnings("unused")
    private float y = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        // Dump touch event to log
        dumpEvent(event);
        // int rotation=(int)Math.toDegrees(r);
        // Handle touch events here...
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                System.out.println("get x >>>" + event.getX());
                System.out.println("get y>>>" + event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    // ...
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY()
                            - start.y);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }

                }
                break;
        }
        //fixing();
        view.setImageMatrix(matrix);
        return true; // indicate event was handled
    }

    public void fixing() {

        float[] value = new float[9];
        matrix.getValues(value);

        float[] savedValue = new float[9];
        savedMatrix.getValues(savedValue);

        int width = getWidth();
        int height = getHeight();

        Drawable d = getDrawable();
        if (d == null) return;
        int imageWidth = d.getIntrinsicWidth();
        int imageHeight = d.getIntrinsicHeight();
        int scaleWidth = (int) (imageWidth * value[0]);
        int scaleHeight = (int) (imageHeight * value[4]);

        // don't let the image go outside
        if (value[2] > width - 1)
            value[2] = width - 10;
        else if (value[5] > height - 1)
            value[5] = height - 10;
        else if (value[2] < -(scaleWidth - 1))
            value[2] = -(scaleWidth - 10);
        else if (value[5] < -(scaleHeight - 1))
            value[5] = -(scaleHeight - 10);

        // maximum zoom ratio: MAx
        if (value[0] > ZOOM || value[4] > ZOOM) {
            value[0] = ZOOM;
            value[4] = ZOOM;
            //value[2] = savedValue[2];
            //value[5] = savedValue[5];
        }

        matrix.setValues(value);
        savedMatrix.set(matrix);
    }

    private Drawable getDrawable() {
        return null;
    }

    private int getHeight() {
        return 0;
    }

    private int getWidth() {
        return 0;
    }

    /**
     * Show an event in the LogCat view, for debugging
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @SuppressLint("NewApi")
    private void dumpEvent(MotionEvent event) {
        String names[] = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
    }

    /**
     * Determine the space between the first two fingers
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /**
     * Calculate the mid point of the first two fingers
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}
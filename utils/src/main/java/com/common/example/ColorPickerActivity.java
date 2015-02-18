package com.common.example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.utils.Common;
import com.common.utils.R;


public class ColorPickerActivity extends Activity {

    int color;
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int action = motionEvent.getAction();
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();


                    try {
                        color = Common.pickColor(view, x, y);
                    } catch (NullPointerException e) {
                        return false;
                    }

                    updateResultData();
            }
            return false;
        }
    };
    private ImageView ivColorImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        ivColorImage = (ImageView) findViewById(R.id.imageView);
        ivColorImage.setOnTouchListener(onTouchListener);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.pickImage(ColorPickerActivity.this, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
            if (requestCode == 123) {

                String path = Common.getPath(ColorPickerActivity.this, data.getData());
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    final Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                    ivColorImage.setImageBitmap(bitmap);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError outOfMemoryError) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    // downsizing image as it throws OutOfMemory Exception for larger images
                    options.inSampleSize = 2;
                    final Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                    ivColorImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
    }

    protected void updateResultData() {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);


        ImageView imageViewColor = (ImageView) findViewById(R.id.imageViewColor);
        imageViewColor.setBackgroundColor(color);


        TextView textViewRgb = (TextView) findViewById(R.id.textViewRgb);
        textViewRgb.setText(
                "RGB: ".concat(Integer.toString(red, 10)).
                        concat(", ").concat(Integer.toString(green, 10)).
                        concat(", ").concat(Integer.toString(blue, 10)));

        TextView textViewHex = (TextView) findViewById(R.id.textViewHex);
        textViewHex.setText(
                "HEX: #".concat(Common.beautyHexString(Integer.toHexString(red))).
                        concat(Common.beautyHexString(Integer.toHexString(green))).
                        concat(Common.beautyHexString(Integer.toHexString(blue))));
    }

}

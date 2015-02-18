package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.common.utils.Common;
import com.common.utils.R;

public class RoundedBitmapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_bitmap);

        ImageView ivImage = (ImageView) findViewById(R.id.rounded_bitmap_iv_image);

        ivImage.setImageBitmap(Common.getRoundedCornerBitmap(Common.drawableTobitmap(this, R.drawable.abc), 30));
    }


}

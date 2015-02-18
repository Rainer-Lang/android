package com.common.example;
/**
 * @author Y@$!n
 *
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.common.utils.Common;
import com.common.utils.R;

public class PinchZoomImageViewAct extends Activity {
    Context mContext;
    ImageView ivImagePich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinch_zoom_image_view);
        mContext = PinchZoomImageViewAct.this;

        init();

    }

    private void init() {
        ivImagePich = (ImageView) findViewById(R.id.ivImagePich);
        Common.applyPinchZoomImage(ivImagePich);
    }
}
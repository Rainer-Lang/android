package com.common.example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.common.utils.Common;
import com.common.utils.R;

import java.io.IOException;

public class PickCaptureActivity extends Activity implements View.OnClickListener {

    private ImageView ivPreview;
    private VideoView vvVideo;
    private TextView tvCaptureImage;
    private TextView tvCaptureVideo;
    private TextView tvPickVideo;
    private TextView tvPickImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_capture);

        ivPreview = (ImageView) findViewById(R.id.imageview_preview);
        vvVideo = (VideoView) findViewById(R.id.videoview_preview);

        tvCaptureImage = (TextView) findViewById(R.id.captureimage);
        tvCaptureVideo = (TextView) findViewById(R.id.capturevideo);
        tvPickImage = (TextView) findViewById(R.id.pickimage);
        tvPickVideo = (TextView) findViewById(R.id.pickvideo);

        tvCaptureImage.setOnClickListener(this);
        tvCaptureVideo.setOnClickListener(this);
        tvPickImage.setOnClickListener(this);
        tvPickVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.captureimage:
                Common.captureImage(this, 101, "Back");
                break;
            case R.id.capturevideo:
                Common.recordVideo(this, 102, "Back");
                break;
            case R.id.pickimage:
                Common.pickImage(this, 103);
                break;
            case R.id.pickvideo:
                Common.pickVideo(this, 104);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 101:
                    vvVideo.setVisibility(View.GONE);
                    ivPreview.setVisibility(View.VISIBLE);
                    Common.previewCapturedImage(ivPreview);
                    break;
                case 102:
                    vvVideo.setVisibility(View.VISIBLE);
                    ivPreview.setVisibility(View.GONE);
                    Common.previewVideo(vvVideo);
                    break;
                case 103:
                    vvVideo.setVisibility(View.GONE);
                    ivPreview.setVisibility(View.VISIBLE);
                    Uri uri = data.getData();
                    try {
                        Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        ivPreview.setImageBitmap(bm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 104:
                    vvVideo.setVisibility(View.VISIBLE);
                    ivPreview.setVisibility(View.GONE);
                    Uri fileUri = data.getData();
                    vvVideo.setVideoPath(Common.getPath(PickCaptureActivity.this, fileUri));
                    vvVideo.start();
            }
        }
    }
}

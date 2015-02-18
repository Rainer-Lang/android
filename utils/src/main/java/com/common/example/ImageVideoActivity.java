package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.common.utils.R;

public class ImageVideoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_video);

        boolean image = getIntent().getBooleanExtra("image", true);

        TextView tvCode = (TextView) findViewById(R.id.image_video_tv_code);

        if (image) {
            tvCode.setText("\n\n \t OPEN IMAGE CODE \n\n"
                    + "  /**\n" +
                    "     * use for open image from SDcard\n" +
                    "     *\n" +
                    "     * @param mContext\n" +
                    "     * @param imagePath location of the image from your sdcard to open\n" +
                    "     */\n" +
                    "    public static void openImage(Context mContext, String imagePath) {\n" +
                    "        Intent intent = new Intent(\"android.intent.action.VIEW\");\n" +
                    "        intent.addCategory(\"android.intent.category.DEFAULT\");\n" +
                    "        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);\n" +
                    "        Uri uri = Uri.fromFile(new File(imagePath));\n" +
                    "        intent.setDataAndType(uri, \"image/*\");\n" +
                    "        mContext.startActivity(intent);\n" +
                    "    }");
        } else {
            tvCode.setText("\n\n \t OPEN VIDEO CODE \n\n"
                    + " /**\n" +
                    "     * use for open video fromSDcard\n" +
                    "     *\n" +
                    "     * @param mContext\n" +
                    "     * @param videoPath location of video from SDcard to open\n" +
                    "     */\n" +
                    "    public static void openVideo(Context mContext, String videoPath) {\n" +
                    "        Intent intent = new Intent(\"android.intent.action.VIEW\");\n" +
                    "        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);\n" +
                    "        intent.putExtra(\"oneshot\", 0);\n" +
                    "        intent.putExtra(\"configchange\", 0);\n" +
                    "        Uri uri = Uri.fromFile(new File(videoPath));\n" +
                    "        intent.setDataAndType(uri, \"video/*\");\n" +
                    "        mContext.startActivity(intent);\n" +
                    "    }");
        }

    }


}

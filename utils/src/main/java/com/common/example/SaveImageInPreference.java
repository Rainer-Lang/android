package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.common.utils.R;

public class SaveImageInPreference extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_image_in_preference);


        TextView tvCode = (TextView) findViewById(R.id.save_image_in_pref_tv_code);
        tvCode.setText("\n \t SAVE BITMAP TO PREFERENCE \n" +
                "  /**\n" +
                "     * save your bitmap in your preference\n" +
                "     *\n" +
                "     * @param bitmap  which you have to store in preference\n" +
                "     * @param context\n" +
                "     * @param name    of preference for your image\n" +
                "     */\n" +
                "    public static void setBitmapToPreference(Bitmap bitmap, Context context, String name, String PREFS_FILE_NAME) {\n" +
                "        ByteArrayOutputStream baos = new ByteArrayOutputStream();\n" +
                "        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);\n" +
                "        byte[] b = baos.toByteArray();\n" +
                "        String temp = Base64.encodeToString(b, Base64.DEFAULT);\n" +
                "        setStringPrefrences(context, name, temp, PREFS_FILE_NAME);\n" +
                "    }\n" +
                "\n\n  GET BITMAP FROM PREFERENCE" +
                "    /**\n" +
                "     * get bitmap which you stored in preference\n" +
                "     *\n" +
                "     * @param mContext\n" +
                "     * @param name            of bitmap preference\n" +
                "     * @param PREFS_FILE_NAME\n" +
                "     * @return bitmap image\n" +
                "     */\n" +
                "    public static Bitmap getBitmapFromPreference(Context mContext, String name, String PREFS_FILE_NAME) {\n" +
                "        try {\n" +
                "            String imageString = getStringPrefrences(mContext, name, PREFS_FILE_NAME);\n" +
                "            byte[] encodeByte = Base64.decode(imageString, Base64.DEFAULT);\n" +
                "            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,\n" +
                "                    encodeByte.length);\n" +
                "            return bitmap;\n" +
                "        } catch (Exception e) {\n" +
                "            e.getMessage();\n" +
                "            return null;\n" +
                "        }\n" +
                "    }");
    }


}

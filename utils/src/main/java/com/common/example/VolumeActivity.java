package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.common.utils.R;

public class VolumeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        TextView tvCode = (TextView) findViewById(R.id.volume_code);
        tvCode.setText("   /**\n" +
                "     * Use current volume in application\n" +
                "     *\n" +
                "     * @param mContext\n" +
                "     */\n" +
                "    public static void setCurrentDeviceVolume(Context mContext) {\n" +
                "        AudioManager audioManager = (AudioManager) mContext\n" +
                "                .getSystemService(Context.AUDIO_SERVICE);\n" +
                "        int currentVolume = audioManager\n" +
                "                .getStreamVolume(AudioManager.STREAM_MUSIC);\n" +
                "        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume,\n" +
                "                0);\n" +
                "    }");
    }


}

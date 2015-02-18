package com.common.example;
/**
 * @author Y@$!n
 *
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.Common;
import com.common.utils.R;

public class GetCurrentLocationAct extends Activity {
    Context mContext;
    TextView tvLatitude, tvLongitude;
    Button btnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_current_location);
        mContext = GetCurrentLocationAct.this;
        init();

    }

    private void init() {
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);


        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Common.isNetworkAvailable(mContext)) {
                    if (Common.getGpsStatus(mContext)) {
                        tvLatitude.setText("" + Common.getCurrentLocation(mContext).getLatitude());
                        tvLongitude.setText("" + Common.getCurrentLocation(mContext).getLongitude());
                    } else {
                        Common.showGPSDisabledAlert("Please enable your loacation.", mContext);

                    }
                } else {
                    Toast.makeText(mContext, "Please Connect your device with internet.", Toast.LENGTH_LONG).show();
                    Common.showNETWORDDisabledAlert(mContext);
                }
            }
        });
    }

}
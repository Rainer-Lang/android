package com.common.example;
/**
 * @author Y@$!n
 *
 */

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.common.utils.Common;
import com.common.utils.R;

public class BluetoothOrWifiOnOffAct extends Activity {
    Context mContext;
    Button btnBluetoothOnOff, btnWifiOnOff;
    Boolean isBluetooth = true, isWifi = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_on_off);
        mContext = BluetoothOrWifiOnOffAct.this;
        init();

    }

    private void init() {
        // TODO Auto-generated method stub

        btnBluetoothOnOff = (Button) findViewById(R.id.btnBluetoothOnOff);
        btnWifiOnOff = (Button) findViewById(R.id.btnWifiOnOff);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            btnBluetoothOnOff.setText("Bluetooth on");
            isBluetooth = false;
        } else {
            isBluetooth = true;
            btnBluetoothOnOff.setText("Bluetooth off");
        }

        WifiManager wm = ((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE));
        if (wm.isWifiEnabled()) {
            btnWifiOnOff.setText("Wifi on");
            isWifi = false;
        } else {
            btnWifiOnOff.setText("Wifi off");
            isWifi = true;
        }

        btnBluetoothOnOff.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isBluetooth) {
                    Common.onBlueTooth("on");
                    btnBluetoothOnOff.setText("Bluetooth on");
                    isBluetooth = false;
                } else {
                    Common.onBlueTooth("off");
                    isBluetooth = true;
                    btnBluetoothOnOff.setText("Bluetooth off");
                }
            }
        });

        btnWifiOnOff.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isWifi) {
                    Common.onWifi(mContext, "on");
                    btnWifiOnOff.setText("Wifi on");
                    isWifi = false;
                } else {
                    Common.onWifi(mContext, "off");
                    btnWifiOnOff.setText("Wifi off");
                    isWifi = true;
                }

            }
        });

    }
}

package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.common.utils.R;

public class StringToDateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_to_date);
        TextView tvCode = (TextView) findViewById(R.id.string_to_date_tv_code);
        tvCode.setText(" /**\n" +
                "     * Convert date in string format to Date format\n" +
                "     *\n" +
                "     * @param strdate which you have to convert in Date format\n" +
                "     * @param format  of the date like \"yyyy-MM-dd\"\n" +
                "     * @return date in Date format\n" +
                "     */\n" +
                "    public static Date stringToDate(String strdate, String format) {\n" +
                "        Date date = null;\n" +
                "        SimpleDateFormat formatter = new SimpleDateFormat(format);\n" +
                "        try {\n" +
                "            date = formatter.parse(strdate);\n" +
                "        } catch (ParseException e) {\n" +
                "            // TODO Auto-generated catch block\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "        return date;\n" +
                "    }");
    }


}

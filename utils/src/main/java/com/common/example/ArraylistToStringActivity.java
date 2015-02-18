package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.common.utils.R;

public class ArraylistToStringActivity extends Activity {

    private TextView tvCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylist_to_string);

        tvCode = (TextView) findViewById(R.id.arraylist_activity_tv_code);
        tvCode.setText(
                " \t CODE TO CONVERT STRING TO ARRAY LIST \n\n" +
                        " /**\n" +
                        "     * make arraylist from \",\" separated string\n" +
                        "     *\n" +
                        "     * @param string \",\" separated string\n" +
                        "     * @return array list\n" +
                        "     */\n" +
                        "    public static ArrayList<String> stringToArrayList(String string) {\n" +
                        "        ArrayList<String> strValueList = new ArrayList<String>(\n" +
                        "                Arrays.asList(string.split(\",\")));\n" +
                        "        return strValueList;\n" +
                        "    }\n" +
                        "\n\n\n" + " \t CODE TO CONVERT ARRAY LIST TO STRING \n\n" +
                        "    /**\n" +
                        "     * convert array list to \",\" separated string\n" +
                        "     *\n" +
                        "     * @param list array list\n" +
                        "     * @return \",\" separated string\n" +
                        "     */\n" +
                        "    public static String arrayListToString(ArrayList<String> list) {\n" +
                        "        String strValue = null;\n" +
                        "        StringBuilder sb = new StringBuilder();\n" +
                        "        for (String s : list) {\n" +
                        "            sb.append(s + \",\");\n" +
                        "            strValue = sb.toString();\n" +
                        "        }\n" +
                        "\n" +
                        "        if (strValue.length() > 0\n" +
                        "                && strValue.charAt(strValue.length() - 1) == ',') {\n" +
                        "            strValue = strValue.substring(0, strValue.length() - 1);\n" +
                        "        }\n" +
                        "        return strValue;\n" +
                        "    }");
    }

}

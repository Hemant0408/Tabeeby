package com.tabeeby.doctor.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Toast;

import com.tabeeby.doctor.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;


/**
 * Created by Lenovo R61 on 8/2/2016.
 */
public class Utils {

    public static String[] getCountryList(Context ctx) {
         final String[] country_list = {ctx.getString(R.string.Afghanistan), ctx.getString(R.string.Albania), ctx.getString(R.string.Algeria), ctx.getString(R.string.Andorra), ctx.getString(R.string.Angola), ctx.getString(R.string.Antigua_and_Barbuda), ctx.getString(R.string.Argentina), ctx.getString(R.string.Armenia), ctx.getString(R.string.Australia), ctx.getString(R.string.Austria), ctx.getString(R.string.Azerbaijan),
                ctx.getString(R.string.Bahamas), ctx.getString(R.string.Bahrain), ctx.getString(R.string.Bangladesh), ctx.getString(R.string.Barbados), ctx.getString(R.string.Belarus), ctx.getString(R.string.Belgium), ctx.getString(R.string.Belize), ctx.getString(R.string.Benin), ctx.getString(R.string.Bhutan), ctx.getString(R.string.Bolivia), ctx.getString(R.string.Bosnia_and_Herzegovina), ctx.getString(R.string.Botswana),
                ctx.getString(R.string.Brazil), ctx.getString(R.string.Brunei), ctx.getString(R.string.Bulgaria), ctx.getString(R.string.Burkina_Faso), ctx.getString(R.string.Burundi),
                ctx.getString(R.string.Cabo_Verde), ctx.getString(R.string.Cambodia), ctx.getString(R.string.Cameroon), ctx.getString(R.string.Canada), ctx.getString(R.string.Central_African_Republic_CAR), ctx.getString(R.string.Chad), ctx.getString(R.string.Chile), ctx.getString(R.string.China), ctx.getString(R.string.Colombia), ctx.getString(R.string.Comoros),
                ctx.getString(R.string.Democratic_Republic_of_the_Congo), ctx.getString(R.string.Republic_of_the_Congo), ctx.getString(R.string.Costa_Rica),
                ctx.getString(R.string.Cote_dIvoire), ctx.getString(R.string.Croatia), ctx.getString(R.string.Cuba), ctx.getString(R.string.Cyprus), ctx.getString(R.string.Czech_Republic),
                ctx.getString(R.string.Denmark), ctx.getString(R.string.Djibouti), ctx.getString(R.string.Dominica), ctx.getString(R.string.Dominican_Republic),
                ctx.getString(R.string.Ecuador), ctx.getString(R.string.Egypt), ctx.getString(R.string.El_Salvador), ctx.getString(R.string.Equatorial_Guinea), ctx.getString(R.string.Eritrea), ctx.getString(R.string.Estonia), ctx.getString(R.string.Ethiopia),
                ctx.getString(R.string.Fiji), ctx.getString(R.string.Finland), ctx.getString(R.string.France), ctx.getString(R.string.Gabon), ctx.getString(R.string.Gambia), ctx.getString(R.string.Georgia), ctx.getString(R.string.Germany), ctx.getString(R.string.Ghana), ctx.getString(R.string.Greece), ctx.getString(R.string.Grenada), ctx.getString(R.string.Guatemala), ctx.getString(R.string.Guinea), ctx.getString(R.string.Guinea_Bissau), ctx.getString(R.string.Guyana), ctx.getString(R.string.Haiti), ctx.getString(R.string.Honduras), ctx.getString(R.string.Hungary), ctx.getString(R.string.Iceland), ctx.getString(R.string.India), ctx.getString(R.string.Indonesia), ctx.getString(R.string.Iran), ctx.getString(R.string.Iraq), ctx.getString(R.string.Ireland), ctx.getString(R.string.Israel), ctx.getString(R.string.Italy), ctx.getString(R.string.Jamaica), ctx.getString(R.string.Japan), ctx.getString(R.string.Jordan), ctx.getString(R.string.Kazakhstan), ctx.getString(R.string.Kenya), ctx.getString(R.string.Kiribati), ctx.getString(R.string.Kosovo), ctx.getString(R.string.Kuwait), ctx.getString(R.string.Kyrgyzstan), ctx.getString(R.string.Laos), ctx.getString(R.string.Latvia), ctx.getString(R.string.Lebanon), ctx.getString(R.string.Lesotho), ctx.getString(R.string.Liberia), ctx.getString(R.string.Libya), ctx.getString(R.string.Liechtenstein), ctx.getString(R.string.Lithuania), ctx.getString(R.string.Luxembourg), ctx.getString(R.string.Macedonia), ctx.getString(R.string.Madagascar), ctx.getString(R.string.Malawi), ctx.getString(R.string.Malaysia), ctx.getString(R.string.Maldives), ctx.getString(R.string.Mali), ctx.getString(R.string.Malta), ctx.getString(R.string.Marshall_Islands), ctx.getString(R.string.Mauritania), ctx.getString(R.string.Mauritius), ctx.getString(R.string.Mexico),
                ctx.getString(R.string.Micronesia), ctx.getString(R.string.Moldova), ctx.getString(R.string.Monaco), ctx.getString(R.string.Mongolia), ctx.getString(R.string.Montenegro), ctx.getString(R.string.Morocco), ctx.getString(R.string.Mozambique), ctx.getString(R.string.Myanmar), ctx.getString(R.string.Namibia), ctx.getString(R.string.Nauru), ctx.getString(R.string.Nepal), ctx.getString(R.string.Netherlands), ctx.getString(R.string.New_Zealand), ctx.getString(R.string.Nicaragua), ctx.getString(R.string.Niger), ctx.getString(R.string.Nigeria), ctx.getString(R.string.North_Korea), ctx.getString(R.string.Norway), ctx.getString(R.string.Oman), ctx.getString(R.string.Pakistan), ctx.getString(R.string.Palau), ctx.getString(R.string.Palestine), ctx.getString(R.string.Panama), ctx.getString(R.string.Papua_New_Guinea), ctx.getString(R.string.Paraguay), ctx.getString(R.string.Peru), ctx.getString(R.string.Philippines), ctx.getString(R.string.Poland), ctx.getString(R.string.Portugal), ctx.getString(R.string.Qatar), ctx.getString(R.string.Romania), ctx.getString(R.string.Russia), ctx.getString(R.string.Rwanda), ctx.getString(R.string.Saint_Kitts_and_Nevis), ctx.getString(R.string.Saint_Lucia), ctx.getString(R.string.Saint_Vincent_and_the_Grenadines), ctx.getString(R.string.Samoa), ctx.getString(R.string.San_Marino), ctx.getString(R.string.Sao_Tome_and_Principe), ctx.getString(R.string.Saudi_Arabia),
                ctx.getString(R.string.Senegal), ctx.getString(R.string.Serbia), ctx.getString(R.string.Seychelles), ctx.getString(R.string.Sierra_Leone), ctx.getString(R.string.Singapore), ctx.getString(R.string.Slovakia), ctx.getString(R.string.Slovenia), ctx.getString(R.string.Solomon_Islands), ctx.getString(R.string.Somalia), ctx.getString(R.string.South_Africa), ctx.getString(R.string.South_Korea),
                ctx.getString(R.string.South_Sudan), ctx.getString(R.string.Spain), ctx.getString(R.string.Sri_Lanka), ctx.getString(R.string.Sudan), ctx.getString(R.string.Suriname), ctx.getString(R.string.Swaziland), ctx.getString(R.string.Sweden), ctx.getString(R.string.Switzerland), ctx.getString(R.string.Syria), ctx.getString(R.string.Taiwan), ctx.getString(R.string.Tajikistan), ctx.getString(R.string.Tanzania), ctx.getString(R.string.Thailand), ctx.getString(R.string.Timor_Leste), ctx.getString(R.string.Togo), ctx.getString(R.string.Tonga), ctx.getString(R.string.Trinidad_and_Tobago), ctx.getString(R.string.Tunisia), ctx.getString(R.string.Turkey), ctx.getString(R.string.Turkmenistan), ctx.getString(R.string.Tuvalu), ctx.getString(R.string.Uganda), ctx.getString(R.string.Ukraine), ctx.getString(R.string.United_Arab_Emirates_UAE), ctx.getString(R.string.United_Kingdom_UK), ctx.getString(R.string.United_States_of_America_USA), ctx.getString(R.string.Uruguay), ctx.getString(R.string.Uzbekistan), ctx.getString(R.string.Vanuatu), ctx.getString(R.string.Vatican_City_Holy_See), ctx.getString(R.string.Venezuela), ctx.getString(R.string.Vietnam), ctx.getString(R.string.Yemen), ctx.getString(R.string.Zambia), ctx.getString(R.string.Zimbabwe)
        };

       return country_list;
    }

    public static void storeSharedPreference(Context ctx, String key, String value) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String retrieveSharedPreference(Context ctx, String key) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        String value = sharedPref.getString(key, "false");
        return value;
    }

    public static void createToastShort(String message, Context ctx) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static void createToastLong(String message, Context ctx) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    public static void removeSharedPreference(Context ctx, String key) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.commit();
    }

    public static boolean isRTL(Context ctx) {
        Configuration config = ctx.getResources().getConfiguration();
        return config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }


    public static String convertTypedBodyToString(ResponseBody responseBody) {
        InputStream is;
        is = responseBody.byteStream();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            return out.toString();
            // Log.i("raw response", "" + out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

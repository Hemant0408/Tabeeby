package com.tabeeby.doctor.utiles;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.tabeeby.doctor.R;

/**
 * Created by Lenovo R61 on 8/2/2016.
 */
public class Utils {

    static Drawer result = null;
    static AccountHeader headerResult = null;

    public static void NavigationDrawer(final Activity mContext, final Toolbar toolbar, Bundle savedInstanceState) {
        final IProfile profile = new ProfileDrawerItem().withIcon(R.drawable.profile3);
        profile.withName("Tabeeby");
        profile.withIcon(R.drawable.ic_account_circle_36pt_3x);

        headerResult = new AccountHeaderBuilder()
                .withActivity(mContext)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(mContext)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        return false;
                    }
                })
                .build();

        result.addItem(new PrimaryDrawerItem().withName("Profile").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Appointments").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Patient").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Organization").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Patient").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Q and A").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Events").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Account Setting").withIcon(R.mipmap.ic_launcher));
        result.addItem(new PrimaryDrawerItem().withName("Logout").withIcon(R.mipmap.ic_launcher));

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


    public static void createToastShort(String message,Context ctx){
        Toast.makeText(ctx,message,Toast.LENGTH_SHORT).show();
    }
    public static void createToastLong(String message,Context ctx){
        Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();
    }


    public static void removeSharedPreference(Context ctx, String key){
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.shared_preference_file_key),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.commit();
    }


    public static final String[] country_list = { "Afghanistan","Albania","Algeria","Andorra","Angola","Antigua and Barbuda","Argentina","Armenia","Australia","Austria","Azerbaijan",
            "Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana",
            "Brazil","Brunei","Bulgaria","Burkina Faso","Burundi",
            "Cabo Verde","Cambodia","Cameroon","Canada","Central African Republic (CAR)","Chad","Chile","China","Colombia","Comoros",
            "Democratic Republic of the Congo","Republic of the Congo","Costa Rica",
            "Cote d'Ivoire","Croatia","Cuba","Cyprus","Czech Republic",
            "Denmark","Djibouti","Dominica","Dominican Republic",
            "Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia",
            "Fiji","Finland","France","Gabon","Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Honduras","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Israel","Italy","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico",
            "Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar (Burma)","Namibia","Nauru","Nepal","Netherlands","New Zealand","Nicaragua","Niger","Nigeria","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar","Romania","Russia","Rwanda","Saint Kitts and Nevis","Saint Lucia","Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia",
            "Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Korea",
            "South Sudan","Spain","Sri Lanka","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo","Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates (UAE)","United Kingdom (UK)","United States of America (USA)","Uruguay","Uzbekistan","Vanuatu","Vatican City (Holy See)","Venezuela","Vietnam","Yemen","Zambia","Zimbabwe"
    };
}

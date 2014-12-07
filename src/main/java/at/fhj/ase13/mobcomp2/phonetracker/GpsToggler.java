package at.fhj.ase13.mobcomp2.phonetracker;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;

/**
 * GPS enabling exploit according to http://stackoverflow.com/questions/4721449/how-can-i-enable-or-disable-the-gps-programmatically-on-android
 */
public class GpsToggler {

    public static void enableGps(Context context) {
        String provider = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (!provider.contains("gps")) { // if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            context. sendBroadcast(poke);
        }
    }

    public static void disableGps(Context context) {
        String provider = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (provider.contains("gps")) { // if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            context.sendBroadcast(poke);
        }
    }

    public static boolean isGpsEnabled(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}

package at.fhj.ase13.mobcomp2.phonetracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.SmsManager;

public class SmsLocationReporter {

    private Context context;

    private String receiver;

    public SmsLocationReporter(Context context, String receiver) {
        this.context = context;
        this.receiver = receiver;
    }

    public void report() {
        LocationManager locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);

        String usedProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER;
        Location location = locationManager.getLastKnownLocation(usedProvider);

        if (location == null) {
            return;
        }

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(receiver, null, context.getString(R.string.report_text,
                location.getLatitude(), location.getLongitude()), null, null);
    }
}

package at.fhj.ase13.mobcomp2.phonetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            return;
        }

        // if the main activity is disabled (= hidden), the tracking should currently active
        if (!MainActivity.isEnabled(context)) {
            String receiver = new PreferenceManager(context).getReportReceiver();

            if (receiver != null) {
                Log.d("PT:BootReceiver", "restarting reporting");
                LogAlarmReceiver.startAlarm(context, receiver);
            }
        }
    }
}

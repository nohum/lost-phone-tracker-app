package at.fhj.ase13.mobcomp2.phonetracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class LogAlarmReceiver extends BroadcastReceiver {

    private static final String ACTION_TRIGGER_ALARM = "at.fhj.ase13.mobcomp2.phonetracker.TRIGGER_ALARM";

    private static PendingIntent pendingTrigger;

    private static int ALARM_TIME = 60 * 10 * 1000;

    public static synchronized void enqueueAlarm(Context context) {
        Intent intent = new Intent();
        intent.setAction(ACTION_TRIGGER_ALARM);

        pendingTrigger = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + ALARM_TIME, ALARM_TIME, pendingTrigger);
    }

    public static synchronized void dequeueAlarm(Context context) {
        if (pendingTrigger == null) {
            return;
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingTrigger);

        pendingTrigger = null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !ACTION_TRIGGER_ALARM.equals(intent.getAction())) {
            Log.i("PT:LogAlarmReceiver", "received invalid intent");
            return;
        }


    }
}

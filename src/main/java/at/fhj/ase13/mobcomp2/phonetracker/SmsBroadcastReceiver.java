package at.fhj.ase13.mobcomp2.phonetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    public SmsBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            return;
        }

        Bundle extras = intent.getExtras();
        if (extras != null) {
            PreferenceManager prefs = new PreferenceManager(context);

            Object[] pdus = (Object[]) extras.get("pdus");

            boolean abort = false;
            for (Object pdu : pdus) {
                SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                String sender = message.getOriginatingAddress();
                String body = message.getMessageBody();

                if (prefs.isStartPasswordCorrect(body)) {
                    LogAlarmReceiver.startAlarm(context, sender);
                    abort = true;

                    break;
                } else if (prefs.isStopPasswordCorrect(body)) {
                    LogAlarmReceiver.stopAlarm(context);
                    abort = true;

                    break;
                }
            }

            if (abort) {
                // FIXME is not working under KITKAT anymore
                abortBroadcast();
            }
        }
    }
}

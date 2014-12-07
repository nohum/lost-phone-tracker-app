package at.fhj.ase13.mobcomp2.phonetracker;

import android.telephony.SmsManager;

public class SmsSender {


    public void sendSms() {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("", null, "", null, null);

    }
}

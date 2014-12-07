package at.fhj.ase13.mobcomp2.phonetracker;

import android.content.Context;

public class PreferenceManager {

    private Context context;

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public void setStartPassword(String password) {

    }

    public void setStopPassword (String password) {

    }

    public boolean isStartPasswordCorrect(String password) {
        return false;
    }

    public boolean isStopPasswordCorrect(String password) {
        return false;
    }
}

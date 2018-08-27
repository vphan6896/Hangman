package pokecrater1.hangman;

import android.app.Activity;

import java.lang.ref.WeakReference;

public class SecondClass {
    private static WeakReference<Activity> mActivityRef;
    public static void updateActivity(Activity activity) {
        mActivityRef = new WeakReference<Activity> (activity);
    }

    public static WeakReference<Activity> getWeakRef() {
        return mActivityRef;
    }

}

package suzp1984.github.io.androidclock;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

/**
 * Created by jacobsu on 5/22/16.
 */
public class ClockApplication extends Application {

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            Log.e("ClockApplication", "start StrictMode");
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDialog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                        .detectAll().penaltyLog().build());
        }

        super.onCreate();
    }
}

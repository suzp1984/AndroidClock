package suzp1984.github.io.androidclock;

import android.os.Bundle;

import org.apache.cordova.CordovaActivity;

public class CordovaClockActivity extends CordovaActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(launchUrl);
    }
}

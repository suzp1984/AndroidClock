package suzp1984.github.io.androidclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import suzp1984.github.io.androidclock.widget.ClockView;

public class MainActivity extends AppCompatActivity {

    private ClockView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClockView = (ClockView) findViewById(R.id.clock);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("MainActivity", "width = " + mClockView.getWidth() + "; height = " + mClockView.getHeight());

    }
}

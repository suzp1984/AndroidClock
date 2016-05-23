package suzp1984.github.io.androidclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import suzp1984.github.io.androidclock.widget.ClockView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.clock)
    ClockView mClockView;

    private Unbinder mButterUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButterUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mClockView != null) {
            Log.d("MainActivity", "width = " + mClockView.getWidth() + "; height = " + mClockView.getHeight());
        }

    }

    @Override
    protected void onDestroy() {
        mButterUnbinder.unbind();
        super.onDestroy();
    }
}

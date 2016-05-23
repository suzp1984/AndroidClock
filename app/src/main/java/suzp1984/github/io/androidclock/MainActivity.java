package suzp1984.github.io.androidclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import suzp1984.github.io.androidclock.widget.ClockView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.clock) ClockView clock;

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

        if (clock != null) {
            Log.d("MainActivity", "width = " + clock.getWidth() + "; height = " + clock.getHeight());
        }

    }

    @Override
    protected void onDestroy() {
        mButterUnbinder.unbind();
        super.onDestroy();
    }
}

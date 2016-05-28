package suzp1984.github.io.androidclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.native_clock)
    public void startNativeClock() {
        Intent intent = new Intent(this, NativeClockActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.cordova_clock)
    public void startCordovaClock() {
        Intent intent = new Intent(this, CordovaClockActivity.class);

        startActivity(intent);
    }
}

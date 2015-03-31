package vb.android.app.quality;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Vincent Brison.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Constants.FLAVOR);
    }
}

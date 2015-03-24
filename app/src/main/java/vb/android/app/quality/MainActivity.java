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
        TextView tw = (TextView) findViewById(R.id.textView);
        tw.setText(Constants.FLAVOR);

        if (2 > 3) {
            if (4 > 2) {

            }
        }

        NumberGenerator numberGenerator = new NumberGenerator();
        numberGenerator.generateAnyNumber();
    }
}

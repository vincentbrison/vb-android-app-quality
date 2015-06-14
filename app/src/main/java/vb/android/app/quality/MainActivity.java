package vb.android.app.quality;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Vincent Brison.
 */
public class MainActivity extends Activity implements PiTask.PiTaskCallback {

    private enum State {
        IDDLE,
        IS_COMPUTING,
        IS_PI_COMPUTED,
        IS_SENDING,
        IS_RANK_READY,
    }

    protected State mState = State.IDDLE;

    @InjectView(R.id.textViewName)
    protected TextView mTextViewName;

    @InjectView(R.id.textViewValue)
    protected TextView mTextViewValue;

    @InjectView(R.id.buttonCompute)
    protected Button mButtonCompute;

    @InjectView(R.id.editTextDigits)
    protected EditText mEditTextDigits;

    @InjectView(R.id.buttonSendPi)
    protected Button mButtonSendPi;

    @InjectView(R.id.buttonShareResult)
    protected Button mButtonShare;

    protected int mMax;
    protected long mStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mButtonSendPi.setEnabled(false);
        mButtonShare.setEnabled(false);
        mButtonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mState.equals(State.IS_COMPUTING)) {
                    setState(State.IS_COMPUTING);
                    int digits = Integer.parseInt(mEditTextDigits.getText().toString());
                    PiTask task = new PiTask(digits, MainActivity.this);
                    mStartTime = System.currentTimeMillis();
                    mMax = digits;
                    task.execute();
                }
            }
        });

        mButtonSendPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onPiComputed(double pi) {
        long millisecond = System.currentTimeMillis() - mStartTime;
        String value = "Pi = " + pi + "(computed in " + millisecond + " ms, for " + mMax + " " + getString(R.string.max_desc) + ")";
        mTextViewValue.setText(value);
        setState(State.IS_PI_COMPUTED);
    }

    private void setState(State state) {
        mState = state;
        if (state.equals(State.IS_PI_COMPUTED)) {
            mEditTextDigits.setEnabled(true);
            mButtonCompute.setEnabled(true);
            mButtonSendPi.setEnabled(true);
            mButtonShare.setEnabled(false);
        } else if (state.equals(State.IS_COMPUTING)) {
            mEditTextDigits.setEnabled(false);
            mButtonCompute.setEnabled(false);
            mButtonSendPi.setEnabled(false);
            mButtonShare.setEnabled(false);
            mTextViewValue.setText(R.string.computing);
        }
    }
}

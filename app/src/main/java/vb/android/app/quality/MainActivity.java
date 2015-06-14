package vb.android.app.quality;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Vincent Brison.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.textViewName)
    protected TextView mTextViewName;

    @InjectView(R.id.textViewValue)
    protected TextView mTextViewValue;

    @InjectView(R.id.buttonCompute)
    protected Button mButton;

    @InjectView(R.id.editTextDigits)
    protected EditText mEditTextDigits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    private static interface PiTaskCallback {
        public void onPiComputed(double pi);
    }

    private static class PiTask extends AsyncTask<Void, Void, Double> {

        private int mDigits;
        private WeakReference<PiTaskCallback> mCallback;

        public PiTask(int digits, PiTaskCallback callback) {
            mDigits = digits;
            mCallback = new WeakReference<>(callback);
        }

        @Override
        protected Double doInBackground(Void... params) {
            return new Pi().calcPiDigits(mDigits);
        }

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);
            if (mCallback.get() != null) {
                mCallback.get().onPiComputed(aDouble);
            }
        }
    }
}

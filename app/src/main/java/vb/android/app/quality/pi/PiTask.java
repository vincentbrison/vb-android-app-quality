package vb.android.app.quality.pi;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import vb.android.app.quality.Pi;

public class PiTask extends AsyncTask<Void, Void, Double> {

    public interface PiTaskCallback {
        public void onPiComputed(double pi);
    }

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

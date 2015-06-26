package vb.android.app.quality.pi;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import vb.android.app.quality.Pi;

/**
 * AsyncTask to compute PI.
 */
public class PiTask extends AsyncTask<Void, Void, Double> {

    private int mMax;
    private WeakReference<PiTaskCallback> mCallback;

    /**
     * Configure the computation task.
     * @param max is a limitation give to the computation.
     * @param callback will be trigger when computation end.
     */
    public PiTask(int max, PiTaskCallback callback) {
        mMax = max;
        mCallback = new WeakReference<>(callback);
    }

    @Override
    protected Double doInBackground(Void... params) {
        return new Pi().calcPiDigits(mMax);
    }

    @Override
    protected void onPostExecute(Double aDouble) {
        super.onPostExecute(aDouble);
        if (mCallback.get() != null) {
            mCallback.get().onPiComputed(aDouble);
        }
    }

    /**
     * Callback interface used when PI is computed.
     */
    public interface PiTaskCallback {
        /**
         * Call when PI is computed.
         * @param pi is the value of pi which was computed.
         */
        void onPiComputed(double pi);
    }
}

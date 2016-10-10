/*
 * Copyright 2015 Vincent Brison.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vb.android.app.quality.pi;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import vb.android.app.quality.PiCalculator;

/**
 * AsyncTask to compute PI.
 */
public class PiTask extends AsyncTask<Void, Void, Double> {

    private int mMax;
    private WeakReference<PiTaskCallback> mCallback;

    /**
     * Configure the computation task.
     *
     * @param max      is a limitation give to the computation.
     * @param callback will be trigger when computation end.
     */
    public PiTask(int max, PiTaskCallback callback) {
        mMax = max;
        mCallback = new WeakReference<>(callback);
    }

    @Override
    protected Double doInBackground(Void... params) {
        return new PiCalculator().calcPiDigits(mMax);
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
         *
         * @param pi is the value of pi which was computed.
         */
        void onPiComputed(double pi);
    }
}

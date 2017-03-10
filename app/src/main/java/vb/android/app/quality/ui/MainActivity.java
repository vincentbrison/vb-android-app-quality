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

package vb.android.app.quality.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vb.android.app.quality.InjectorHelper;
import vb.android.app.quality.R;
import vb.android.app.quality.pi.PiTask;
import vb.android.app.quality.rest.ApiInterface;
import vb.android.app.quality.rest.ResponseRank;

/**
 * Created by Vincent Brison.
 */
public class MainActivity extends Activity implements PiTask.PiTaskCallback, Observer<ResponseRank> {

    private enum State {
        IDLE,
        IS_COMPUTING,
        IS_PI_COMPUTED,
        IS_SENDING,
        IS_RANK_READY,
    }

    protected State mState = State.IDLE;

    @BindView(R.id.textViewName)
    protected TextView mTextViewName;

    @BindView(R.id.textViewValue)
    protected TextView mTextViewValue;

    @BindView(R.id.buttonCompute)
    protected Button mButtonCompute;

    @BindView(R.id.editTextDigits)
    protected EditText mEditTextDigits;

    @BindView(R.id.buttonSendPi)
    protected Button mButtonSendPi;

    @BindView(R.id.buttonShareResult)
    protected Button mButtonShare;

    @BindView(R.id.textviewRank)
    protected TextView mTextViewRank;

    @Inject
    protected ApiInterface mApi;

    protected int mMax;
    protected long mStartTime;
    protected long mTime;
    protected ResponseRank mResponseRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        InjectorHelper.getApplicationComponent().inject(this);
        mButtonSendPi.setEnabled(false);
        mButtonShare.setEnabled(false);
        mButtonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mState.equals(State.IS_COMPUTING) || mState.equals(State.IS_SENDING)) {
                    if (TextUtils.isEmpty(mEditTextDigits.getText().toString())) {
                        Toast.makeText(MainActivity.this,
                                R.string.enter_number_of_digits, Toast.LENGTH_SHORT).show();
                    } else {
                        setState(State.IS_COMPUTING);
                        int digits = Integer.parseInt(mEditTextDigits.getText().toString());
                        PiTask task = new PiTask(digits, MainActivity.this);
                        mStartTime = System.currentTimeMillis();
                        mMax = digits;
                        task.execute();
                    }
                }
            }
        });

        mButtonSendPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mState.equals(State.IS_COMPUTING) || mState.equals(State.IS_SENDING)) {
                    setState(State.IS_SENDING);
                    mApi.getRank(getString(R.string.algo), mTime, mMax)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(MainActivity.this);
                }
            }
        });

        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mState.equals(State.IS_RANK_READY)) {
                    Intent intentShare = new Intent(Intent.ACTION_SEND);
                    intentShare.setType("text/plain");
                    intentShare.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_title));
                    intentShare.putExtra(
                            Intent.EXTRA_TEXT, "My rank is " + mResponseRank.getRank() + " on Pi computing bench.");
                    startActivity(intentShare);
                }
            }
        });
    }

    @Override
    public void onPiComputed(double pi) {
        mTime = System.currentTimeMillis() - mStartTime;
        String value = "Pi = " + pi + "(computed in " + mTime
                + " ms, for " + mMax + " " + getString(R.string.max_desc) + ")";
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
            setProgressBarIndeterminateVisibility(false);
        } else if (state.equals(State.IS_COMPUTING)) {
            mEditTextDigits.setEnabled(false);
            mButtonCompute.setEnabled(false);
            mButtonSendPi.setEnabled(false);
            mButtonShare.setEnabled(false);
            setProgressBarIndeterminateVisibility(true);
        } else if (state.equals(State.IS_SENDING)) {
            mEditTextDigits.setEnabled(false);
            mButtonCompute.setEnabled(false);
            mButtonSendPi.setEnabled(false);
            mButtonShare.setEnabled(false);
            setProgressBarIndeterminateVisibility(true);
        } else if (state.equals(State.IS_RANK_READY)) {
            mEditTextDigits.setEnabled(true);
            mButtonCompute.setEnabled(true);
            mButtonSendPi.setEnabled(false);
            mButtonShare.setEnabled(true);
            setProgressBarIndeterminateVisibility(false);
        }
    }

    @Override
    public void onComplete() {
        setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, getString(R.string.network_issue), Toast.LENGTH_SHORT).show();
        setState(State.IS_PI_COMPUTED);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
    }

    @Override
    public void onNext(ResponseRank responseRank) {
        setState(State.IS_RANK_READY);
        mResponseRank = responseRank;
        mTextViewRank.setText(getString(R.string.your_rank_is, responseRank.getRank()));
    }
}

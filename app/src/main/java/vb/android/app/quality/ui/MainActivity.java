package vb.android.app.quality.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import vb.android.app.quality.Injector;
import vb.android.app.quality.R;
import vb.android.app.quality.app.QualityApplication;
import vb.android.app.quality.dagger.DaggerAppComponent;
import vb.android.app.quality.pi.PiTask;
import vb.android.app.quality.rest.APIInterface;
import vb.android.app.quality.rest.ResponseRank;

/**
 * Created by Vincent Brison.
 */
public class MainActivity extends Activity implements PiTask.PiTaskCallback, Observer<ResponseRank> {

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

    @InjectView(R.id.textviewRank)
    protected TextView mTextViewRank;

    @Inject
    protected APIInterface mApi;

    protected int mMax;
    protected long mStartTime;
    protected long mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Injector.INSTANCE.getApplicationComponent().inject(this);
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
                mApi.getRank(getString(R.string.algo), mTime, mMax).observeOn(AndroidSchedulers.mainThread()).subscribe(MainActivity.this);
            }
        });

        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intentShare = new Intent(Intent.ACTION_SEND);
                //intentShare.setType("text/plain");
                //intentShare.putExtra(Intent.EXTRA_SUBJECT, news.getTitle());
                //intentShare.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_news) + " " + news.getUrl());
            }
        });
    }

    @Override
    public void onPiComputed(double pi) {
        mTime = System.currentTimeMillis() - mStartTime;
        String value = "Pi = " + pi + "(computed in " + mTime + " ms, for " + mMax + " " + getString(R.string.max_desc) + ")";
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
        }
    }

    @Override
    public void onCompleted() {
        setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(ResponseRank responseRank) {
        mTextViewRank.setText("Your rank is " + responseRank.getRank());
    }
}

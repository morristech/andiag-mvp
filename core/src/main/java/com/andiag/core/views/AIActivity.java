package com.andiag.core.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andiag.core.presenters.AIPresenter;

/**
 * Created by Canalejas on 01/01/2017.
 */

public abstract class AIActivity<P extends AIPresenter> extends Activity implements AIDelegatedView {
    private final static String TAG = AICompatActivity.class.getSimpleName();

    protected P mPresenter;

    @Override
    public void detachView() {
        mPresenter.detach();
        mPresenter = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void attachView() {
        mPresenter.attach(getApplication(), this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitPresenter();
        attachView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onInitPresenter();
        attachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onViewCreated();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onViewDestroyed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detachView();
    }
}
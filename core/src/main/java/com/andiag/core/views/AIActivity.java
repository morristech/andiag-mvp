package com.andiag.core.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.andiag.core.presenters.AIPresenter;
import com.andiag.core.presenters.Presenter;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by Canalejas on 11/12/2016.
 */
public abstract class AIActivity<P extends AIPresenter> extends AppCompatActivity implements AIDelegatedView {
    private final static String TAG = AIActivity.class.getSimpleName();

    protected P mPresenter;

    /**
     * {@link Exception} catch:
     * - {@link Fragment.InstantiationException}
     * - {@link IllegalAccessException}
     * - {@link InvocationTargetException}
     * - {@link NoSuchMethodException}
     */
    @SuppressWarnings("unchecked")
    public final void onInitPresenter() {
        if (getClass().getAnnotation(Presenter.class) != null) {
            try {
                mPresenter = ((Class<P>) getClass()
                        .getAnnotation(Presenter.class).presenter())
                        .getConstructor().newInstance();
            } catch (Exception e) {
                throw new IllegalStateException("No default constructor found in presenter");
            }
        } else {
            throw new IllegalStateException("Not annotated Activity. Try using @Presenter annotation");
        }
    }

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

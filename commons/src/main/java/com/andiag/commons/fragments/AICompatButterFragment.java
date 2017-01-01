package com.andiag.commons.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andiag.core.presenters.AIPresenter;
import com.andiag.core.views.AICompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Canalejas on 24/12/2016.
 */
public abstract class AICompatButterFragment<P extends AIPresenter> extends AICompatFragment<P> {
    private static final String EXTRA_LAYOUT = "extra_layout";

    Unbinder unbinder;
    @LayoutRes
    protected Integer mFragmentLayout;

    public AICompatButterFragment() {
    }

    /**
     * {@link AICompatButterFragment#mFragmentLayout} should be initialize here
     */
    protected abstract void onInitLayout();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_LAYOUT, mFragmentLayout);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitLayout();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_LAYOUT)) {
            mFragmentLayout = savedInstanceState.getInt(EXTRA_LAYOUT);
        }

        if (mFragmentLayout == null) {
            throw new IllegalStateException("Fragment Layout should have a valid value");
        }

        View fragmentView = inflater.inflate(mFragmentLayout, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unbinder = null;
    }
}
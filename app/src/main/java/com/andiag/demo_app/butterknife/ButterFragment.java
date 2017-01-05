package com.andiag.demo_app.butterknife;

import android.widget.Toast;

import com.andiag.commons.fragments.AIButterFragment;
import com.andiag.demo_app.R;
import com.andiag.demo_app.commons.CustomInterface;
import com.andiag.demo_app.commons.CustomPresenter;

import butterknife.OnClick;

/**
 * Created by Canalejas on 04/01/2017.
 */

public class ButterFragment extends AIButterFragment<CustomPresenter> implements CustomInterface {

    @OnClick(R.id.button_touch)
    public void onClickButton() {
        Toast.makeText(mParentContext, "I'm a button attached with Butterknife", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onInitLayout() {
        mFragmentLayout = R.layout.fragment_butterknife;
    }

    @Override
    public void onInitPresenter() {
        /**
         * It's recommended to use a different presenter for each view
         */
        mPresenter = CustomPresenter.getInstance();
        mPresenter.enableLogging();
    }

    /**
     * Example of callback from the presenter.
     *
     * @param text showed in toast
     */
    @Override
    public void presenterCallback(String text) {
        Toast.makeText(mParentContext, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void viewAttached() {
        Toast.makeText(mParentContext, "Fragment Attached to Presenter", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void viewCreated() {
        Toast.makeText(mParentContext, "Fragment View Created", Toast.LENGTH_SHORT).show();
    }
}

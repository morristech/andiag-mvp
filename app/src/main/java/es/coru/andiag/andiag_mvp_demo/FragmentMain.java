package es.coru.andiag.andiag_mvp_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.coru.andiag.andiag_mvp.views.AIFragment;

/**
 * Created by Canalejas on 29/12/2016.
 */

public class FragmentMain extends AIFragment<CustomFragmentPresenter> {

    @Override
    public void onInitPresenter() {
        mPresenter = CustomFragmentPresenter.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    /**
     * Example of callback from the presenter.
     *
     * @param text showed in toast
     */
    public void presenterCallback(String text) {
        Toast.makeText(mParentContext, text, Toast.LENGTH_SHORT).show();
    }
}

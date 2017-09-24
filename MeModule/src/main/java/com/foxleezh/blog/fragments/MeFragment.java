package com.foxleezh.blog.fragments;

import com.foxleezh.blog.memodule.BR;
import com.foxleezh.blog.memodule.R;
import com.foxleezh.blog.memodule.databinding.FragmentMeBinding;
import com.foxleezh.blog.viewmodels.MeViewModel;
import com.foxleezh.middleware.mvvm.BaseFragment;

/**
 * Created by Administrator on 2017/9/24.
 */

public class MeFragment extends BaseFragment<MeViewModel,FragmentMeBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected MeViewModel bindViewModel(FragmentMeBinding binding) {
        MeViewModel model= new MeViewModel(getActivity().getApplicationContext());
        binding.setVariable(BR.viewModel,model);
        return model;
    }
}

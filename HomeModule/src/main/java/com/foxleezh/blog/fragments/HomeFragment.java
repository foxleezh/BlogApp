package com.foxleezh.blog.fragments;

import com.foxleezh.blog.homemodule.BR;
import com.foxleezh.blog.homemodule.R;
import com.foxleezh.blog.homemodule.databinding.FragmentHomeBinding;
import com.foxleezh.blog.viewmodels.HomeViewModel;
import com.foxleezh.middleware.mvvm.BaseFragment;

/**
 * Created by Administrator on 2017/9/24.
 */

public class HomeFragment extends BaseFragment<HomeViewModel,FragmentHomeBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeViewModel bindViewModel(FragmentHomeBinding binding) {
        HomeViewModel model= new HomeViewModel(getActivity().getApplicationContext());
        binding.setVariable(BR.viewModel,model);
        return model;
    }
}

package com.foxlee.blog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.foxlee.blog.databinding.ActivityMainBinding;
import com.foxleezh.basecomponent.util.ContextUtil;
import com.foxleezh.blog.fragments.HomeFragment;
import com.foxleezh.blog.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {

    public static final int HOME_POS = 0;
    public static final int ME_POS = 1;

    private long exitTime;
    ActivityMainBinding binding;

    private HomeFragment homeFragment;
    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setVariable(com.foxlee.blog.BR.viewModel,new MainViewModel());
        setCurTab(0);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Observable<Notification<NewsModuleInfo>> combineRequestOb = ApiManager.doApi("http://owegaqfyy.bkt.clouddn.com/",HomeService.class).getNews(System.currentTimeMillis())
//                        .map(newsModuleInfo -> {
//                            Log.d("foxlee++++++++",Thread.currentThread().getName());
//                            return newsModuleInfo;
//                        })
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .materialize().share();
//
//                combineRequestOb.filter(Notification::isOnNext)
//                        .subscribe(m ->Log.d("foxlee++++++++",Thread.currentThread().getName()));
//                combineRequestOb.filter(Notification::isOnError)
//                        .subscribe(m ->Log.d("foxlee++++++++",Thread.currentThread().getName()));
//            }
//        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_home:
                setCurTab(HOME_POS);
                break;
            case R.id.rl_me:
                setCurTab(ME_POS);
                break;
        }
    }


    void setCurTab(int position) {
        Fragment fragment;
        switch (position) {
            case HOME_POS:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                fragment=homeFragment;
                binding.getViewModel().tabHomeSelected=true;
                binding.getViewModel().tabMeSelected=false;
                break;
            case ME_POS:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                }
                fragment=meFragment;
                binding.getViewModel().tabHomeSelected=false;
                binding.getViewModel().tabMeSelected=true;
                break;
            default:
                fragment=homeFragment;
                break;
        }
        binding.getViewModel().notifyPropertyChanged(com.foxlee.blog.BR.tabHomeSelected);
        binding.getViewModel().notifyPropertyChanged(com.foxlee.blog.BR.tabMeSelected);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.frameLayout_content, fragment);
        ft.commitAllowingStateLoss();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ContextUtil.toastTips(MainActivity.this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
//                TinkerUtils.setBackground(true);
//                if(shouldKill){
//                    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                    manager.killBackgroundProcesses(getPackageName());
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                }
            }

        }
        return true;
    }

}

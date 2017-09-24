package com.foxlee.blog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.foxlee.blog.databinding.ActivityMainBinding;
import com.foxleezh.basecomponent.util.ContextUtil;

public class MainActivity extends AppCompatActivity {

    private long exitTime;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setVariable(BR.viewModel,new MainViewModel());

//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Observable<Notification<NewsModuleInfo>> combineRequestOb = ApiManager.doApi("http://owegaqfyy.bkt.clouddn.com/",ApiService.class).getNews(System.currentTimeMillis())
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
                binding.getViewModel().tabHomeSelected=true;
                binding.getViewModel().tabMeSelected=false;
                binding.getViewModel().notifyPropertyChanged(BR.tabHomeSelected);
                binding.getViewModel().notifyPropertyChanged(BR.tabMeSelected);
                break;
            case R.id.rl_me:
                binding.getViewModel().tabHomeSelected=false;
                binding.getViewModel().tabMeSelected=true;
                binding.getViewModel().notifyPropertyChanged(BR.tabHomeSelected);
                binding.getViewModel().notifyPropertyChanged(BR.tabMeSelected);
                break;
        }
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

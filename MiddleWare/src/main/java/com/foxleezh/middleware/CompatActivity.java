package com.foxleezh.middleware;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.foxleezh.middleware.mvvm.BaseFragment;


public class CompatActivity extends AppCompatActivity{
    private String fragmentName;
    private boolean shouldClose;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		fragmentName=getIntent().getStringExtra("fragmentName");
        shouldClose=getIntent().getBooleanExtra("shouldClose",true);


        if(TextUtils.isEmpty(fragmentName)){
            ActivityInfo info= null;
            try {
                info = this.getPackageManager()
                        .getActivityInfo(getComponentName(),
                                PackageManager.GET_META_DATA);
                fragmentName =info.metaData.getString("fragmentName");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
//        shouldFull=getIntent().getBooleanExtra("shouldFill",false);
//		if(shouldFull){
//			requestWindowFeature(Window.FEATURE_NO_TITLE);
//			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//					WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		}
//        if(getIntent().getIntExtra("hiddenSoft",0)==1) {
//            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        }
        setContentView(R.layout.activity_compat);
        initFragment();
    }

    private void initFragment(){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
		Fragment fragment=getFragment();
		if(fragment==null){
			finish();
			return;
		}
        transaction.replace(R.id.fl_parent, fragment);
        transaction.commit();
    }

    public Fragment getFragment(){
		try {
			Class onwClass = Class.forName(fragmentName);
			Object instance = onwClass.newInstance();
			fragment= (Fragment) instance;
		} catch (Throwable e) {
			e.printStackTrace();
		}
        return fragment;
    }

    @Override
    public void finish() {
        if(fragment!=null&&fragment instanceof BaseFragment) {
            ((BaseFragment)fragment).finish();
        }
        super.finish();
//        overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment.onActivityResult(requestCode,resultCode,data);
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
        if(fragment!=null&&fragment instanceof BaseFragment) {
            ((BaseFragment)fragment).onRelease();
        }
	}

//    @Override
//    public void onClick(View v) {
//        if(fragment!=null&&fragment instanceof BaseVMFragment) {
//            ((BaseVMFragment)fragment).onClick(v);
//        }
//    }

	@Override
    public void onBackPressed() {
        if(fragment!=null&&fragment instanceof BaseFragment) {
            ((BaseFragment)fragment).onBackPressed();
        }
        if(shouldClose){
            super.onBackPressed();
        }
    }
}

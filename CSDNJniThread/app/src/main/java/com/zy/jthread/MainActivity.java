package com.zy.jthread;

import com.kasaax.commons.base.BaseDataBindingActivity;
import com.zy.jthread.databinding.ActivityMainBinding;


public class MainActivity extends BaseDataBindingActivity<ActivityMainBinding> {


    private ThreadTestHelper threadTestHelper;

    @Override
    protected void initDataBinding() {
        mBinding.setEventProcessor(new MainEventProcessor());
    }

    @Override
    protected void initData() {
        threadTestHelper = new ThreadTestHelper();

        mBinding.sampleText.setText(ThreadTestHelper.stringFromJNI());
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    public class MainEventProcessor {
        public void testNativeThread() {
            threadTestHelper.normalThread();
        }
        public void testNativeConsumptionTask() {
            threadTestHelper.consumptionTask();
        }
    }

}

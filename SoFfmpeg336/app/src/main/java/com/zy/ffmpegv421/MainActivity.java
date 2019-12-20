package com.zy.ffmpegv421;

import com.kasaax.commons.base.BaseDataBindingActivity;
import com.zy.ffmpegv421.databinding.ActivityMainBinding;

public class MainActivity extends BaseDataBindingActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDataBinding() {
        mBinding.setEventProcessor(new MainEventProcessor());
    }

    @Override
    protected void initData() {
        mBinding.sampleText.setText(FfmpegUtils.stringFromJNI());
    }

    public class MainEventProcessor {
        public void testParseMetaData() {
            mBinding.sampleText.setText("数据解析中，请稍候");
            String ret = FfmpegUtils.printMetaData("/mnt/sdcard/test.mp4");
            mBinding.sampleText.setText(ret);
        }
    }
}
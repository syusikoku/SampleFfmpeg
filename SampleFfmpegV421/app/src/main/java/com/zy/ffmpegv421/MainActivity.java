package com.zy.ffmpegv421;

import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Map<String, String> map = new LinkedHashMap<>();

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(FfmpegUtils.stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

}

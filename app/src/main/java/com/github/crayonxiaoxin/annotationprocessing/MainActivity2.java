package com.github.crayonxiaoxin.annotationprocessing;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.crayonxiaoxin.lib_annotations.BindView;
import com.github.crayonxiaoxin.lib_binding.Binding;


public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);
        textView.setText("123");
    }
}

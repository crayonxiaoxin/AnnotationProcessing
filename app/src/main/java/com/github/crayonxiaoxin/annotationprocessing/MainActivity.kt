package com.github.crayonxiaoxin.annotationprocessing

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import com.github.crayonxiaoxin.lib_annotations.BindView
import com.github.crayonxiaoxin.lib_binding.Binding

class MainActivity : AppCompatActivity() {

    @BindView(R.id.textView)
    lateinit var textView: TextView

    @BindView(R.id.layout)
    lateinit var layout:ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Binding.bind(this)

        textView.text = "Hello Kotlin"
        layout.setBackgroundColor(Color.CYAN)
    }
}
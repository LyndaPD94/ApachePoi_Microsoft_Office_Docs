package com.example.apachepoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.apache.poi.hssf.eventusermodel.HSSFListener;

public class MainActivity extends AppCompatActivity {
    Button btn2hssf,btn2xwpf,btn2hslf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn2hssf=(Button)findViewById(R.id.btn2hssf);
        btn2xwpf=(Button)findViewById(R.id.btn2xwpfwbk);
        btn2hslf=(Button) findViewById(R.id.button2hslfss);

        btn2hssf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HssfWbk.class);
                startActivity(intent);
            }
        }); btn2xwpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,XWPFWbk.class);
                startActivity(intent);
            }
        }); btn2hslf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HslfSlsh.class);
                startActivity(intent);
            }
        });
    }
}
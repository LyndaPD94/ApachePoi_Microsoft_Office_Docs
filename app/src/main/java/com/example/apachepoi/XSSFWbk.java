package com.example.apachepoi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class XSSFWbk extends AppCompatActivity {
    EditText xddftv;
    Button btnexprtxls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xssfwbk);
        xddftv=(EditText) findViewById(R.id.xddftv);
        btnexprtxls=(Button) findViewById(R.id.exprtxls1);
        btnexprtxls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    exprtxls();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void exprtxls() throws IOException {

        try {
            File exportDir = new File(Environment.getExternalStorageDirectory()+"/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "xssf_example.xlsx");
                file.createNewFile();
                try {

                    Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.getCause();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }catch(IOException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error 2:File does not contain any data.", Toast.LENGTH_LONG).show();
        }
    }
}
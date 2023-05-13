package com.example.apachepoi;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XWPFWbk extends AppCompatActivity {
    Button btnexpdocx;
    EditText docxtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwpfwbk);
        btnexpdocx=(Button) findViewById(R.id.exprtdocx);
        docxtv=(EditText) findViewById(R.id.doocxtv);
        btnexpdocx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    exportdoc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void exportdoc() throws IOException {
        String input="/sdcard/Documents/moon1.png";
        try {
            File exportDir = new File(Environment.getExternalStorageDirectory()+"/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "xwpf_example.docx");
                file.createNewFile();
                try {
                    //XWPFDocument xwpfDocument=new XWPFDocument();

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
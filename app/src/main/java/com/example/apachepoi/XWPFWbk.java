package com.example.apachepoi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.system.ErrnoException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
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
    private void exportdoc() throws ErrnoException {
        String input="/sdcard/Documents/moon1.png";
        try {
            File exportDir = new File(Environment.getExternalStorageDirectory()+"/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "datos.docx");
                file.createNewFile();

                try {
                    XWPFDocument document = new XWPFDocument();
                    XWPFParagraph paragraph1=document.createParagraph();
                    XWPFRun run1=paragraph1.createRun();
                    run1.setText(wordp.getText().toString());
                    run1.setFontSize(12);
                    try{
                        FileInputStream fileInputStream=new FileInputStream(Environment.getExternalStorageDirectory()+"/Documents/moon1.png");
                        XWPFPicture xwpfPicture= run1.addPicture(fileInputStream,2,"moon1.png",1,1);
                        System.out.println(xwpfPicture.getDescription());
                        xwpfPicture.getPictureData();

                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    FileOutputStream fileOut = new FileOutputStream(file);
                    document.write(fileOut);
                    fileOut.close();
                    Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
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
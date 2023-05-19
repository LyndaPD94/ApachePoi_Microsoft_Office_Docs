package com.example.apachepoi;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.apache.poi.hslf.model.HeadersFooters;
import org.apache.poi.hslf.record.Slide;
import org.apache.poi.hslf.usermodel.HSLFAutoShape;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextBox;
import org.apache.poi.hssf.usermodel.EscherGraphics2d;
import org.apache.poi.sl.usermodel.SlideShow;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class HslfSlsh extends AppCompatActivity {
    Button exprtpptx;
    EditText pptxtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hslf_slsh);
        exprtpptx=(Button)findViewById(R.id.btnexprtpptx);
        pptxtv=(EditText)findViewById(R.id.hslftv);

        exprtpptx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   // hslfBullet();
                    hslfheadfoot();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void hslfheadfoot() throws IOException {

        try {
            File exportDir = new File(Environment.getExternalStorageDirectory()+"/Download");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "hslf_example.ppt");
                file.createNewFile();
                try {
                    HSLFSlideShow slideShow =new HSLFSlideShow();
                    HSLFSlide slide=slideShow.createSlide();
                    HeadersFooters headersFooters=slideShow.getSlideHeadersFooters();
                    headersFooters.setFootersText("Foot");
                    headersFooters.setHeaderText("Head");
                    headersFooters.setFooterVisible(true);
                    headersFooters.setHeaderVisible(true);
                    HSLFSlide slide1=slide.getSlideShow().createSlide();
                    FileOutputStream fileOut = new FileOutputStream(file);

                    //Slide slide3=slide.getSlideRecord();

                    HSLFTextBox textBox=slide1.createTextBox();
                    textBox.setText("textbox").setFontSize(12.5);

                    slideShow.write(fileOut);
                    fileOut.close();
                    Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.getCause();
                    Toast.makeText(getApplicationContext(), "Error 1", Toast.LENGTH_LONG).show();
                }
            }catch(IOException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error 2", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error 3:File does not contain any data.", Toast.LENGTH_LONG).show();
        }
    }
    private void hslfheadfoot2() throws IOException {
       /* HSLFSlideShow slideShow = new HSLFSlideShow();
        HeadersFooters headerFooter=slideShow.getSlideHeadersFooters();
        headerFooter.setHeaderText("Header");
        headerFooter.setFootersText("Footer");
        slideShow.createSlide();
        slideShow.createMasterSheet();
    }
        private void hslfBullet()  {
            try {
                File exportDir = new File(Environment.getExternalStorageDirectory() + "/Download");
                if (!exportDir.exists()) {
                    exportDir.mkdirs();
                }
                try {
                    File file = new File(exportDir, "hslf_example.ppt");
                    file.createNewFile();
                    try {
                        HSLFSlideShow slideShow = new HSLFSlideShow();
                        HSLFSlide slide = slideShow.createSlide();
                        HSLFTextBox textBox = slide.createTextBox();
                        HSLFTextParagraph textParagraph = textBox.getTextParagraphs().get(0);
                        //textParagraph.getTextRuns().setFontSize(40d);
                        textParagraph.setBullet(true);
                        textParagraph.setIndent(0d);
                        textParagraph.setLeftMargin(0.5);
                        textParagraph.setBulletChar('\u263A');
                        textBox.setText("Bullet1 \r" + "Bullet2 \r" + "Bullet3 \r" + "Bullet4 \r");
                        slide.addShape(textBox);
                        FileOutputStream fileOut = new FileOutputStream(file);
                        slideShow.write(fileOut);
                        fileOut.close();
                        Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.getCause();
                        Toast.makeText(getApplicationContext(), "Error 1", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error 2", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error 3:File does not contain any data.", Toast.LENGTH_LONG).show();
            }*/
        }
    }
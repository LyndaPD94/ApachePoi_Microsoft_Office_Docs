package com.example.apachepoi;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.hslf.exceptions.HSLFException;
import org.apache.poi.hslf.model.HeadersFooters;
import org.apache.poi.hslf.record.ColorSchemeAtom;
import org.apache.poi.hslf.record.MasterTextPropAtom;
import org.apache.poi.hslf.usermodel.HSLFAutoShape;
import org.apache.poi.hslf.usermodel.HSLFBackground;
import org.apache.poi.hslf.usermodel.HSLFMasterSheet;
import org.apache.poi.hslf.usermodel.HSLFObjectShape;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFShapeContainer;
import org.apache.poi.hslf.usermodel.HSLFSheet;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideMaster;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTable;
import org.apache.poi.hslf.usermodel.HSLFTableCell;
import org.apache.poi.hslf.usermodel.HSLFTextBox;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.hslf.usermodel.HSLFTitleMaster;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideShow;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideMasterId;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class HslfSlsh extends AppCompatActivity {
    Button exprtpptx;
    EditText pptxtv;
    NoClassDefFoundError noClassDefFoundError;

    List<HSLFTextParagraph> paragraphList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hslf_slsh);
        exprtpptx = (Button) findViewById(R.id.btnexprtpptx);
        pptxtv = (EditText) findViewById(R.id.hslftv);

        exprtpptx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // hslfBullet();
                    hslfejmp();
                    // slideTable();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void hslfejmp() throws IOException, InvalidFormatException, NoClassDefFoundError, ClassNotFoundException {

        try {
            File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "hslf_example.ppt");
                file.createNewFile();
                try {
                    HSLFSlideShow slideShow = new HSLFSlideShow();
                    HSLFSlide slide = slideShow.createSlide();
                    HeadersFooters headersFooters = slideShow.getSlideHeadersFooters();
                    headersFooters.setFootersText("Foot");
                    headersFooters.setFooterVisible(true);
                    addTextbox(slide);


                    HSLFSlide slide1=slide.getSlideShow().createSlide();
                    HSLFBackground background=slide1.getBackground().getSheet().getBackground();
                    HSLFSheet sheet=slide1.getMasterSheet();
                    HSLFMasterSheet masterSheet=sheet.getMasterSheet();

                    HSLFShape shape= slide1.getPlaceholder(Placeholder.CENTERED_TITLE);
                    HSLFTextParagraph hslfTextRuns = null;
                    HSLFTextRun run=new HSLFTextRun(hslfTextRuns);
                    HSLFTextParagraph textParagraph= run.getTextParagraph();



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
        }
    }

    private void hslfheadfoot() throws IOException {
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

    private void addTextbox(HSLFSlide slide) throws IOException,NoClassDefFoundError,InvalidFormatException, NoClassDefFoundError, ClassNotFoundException{
        try {
            HSLFTextBox txtbx = new HSLFTextBox();
            HSLFTextParagraph txtbxp = txtbx.getTextParagraphs().get(0);
            txtbxp.getTextRuns().get(0).setFontSize(24.5);
            txtbxp.getTextRuns().get(0).setFontFamily("Times New Roman");
            txtbxp.setBullet(true);
            txtbxp.setIndent(0.5);
            txtbxp.setLeftMargin(20.0);
            txtbxp.setRightMargin(20.0);
            txtbxp.setBulletSize(5.0);
            txtbxp.setBulletChar('\u263A');
            txtbx.setHorizontalCentered(Boolean.TRUE);
            txtbx.setText("one\r" + "two\r" + "three");
            txtbx.setShapeType(ShapeType.ACCENT_CALLOUT_1);
            slide.addShape(txtbx);

            Toast.makeText(getApplicationContext(), "Textbox Created", Toast.LENGTH_LONG).show();
        } catch (NoClassDefFoundError e) {
            Toast.makeText(getApplicationContext(), "No textbox", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            HSLFException exception=new HSLFException("Cannot create file");
            e.addSuppressed(exception);

        }

    }
}
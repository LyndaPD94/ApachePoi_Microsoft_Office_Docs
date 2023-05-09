package com.example.apachepoi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.XSSFTableStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
                } catch (IOException e) {
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
                    XSSFWorkbook wb=new XSSFWorkbook();
                    XSSFSheet sheet=wb.createSheet("Sheet 1");



                    FileOutputStream fileOut = new FileOutputStream(file);
                    wb.write(fileOut);
                    fileOut.close();
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
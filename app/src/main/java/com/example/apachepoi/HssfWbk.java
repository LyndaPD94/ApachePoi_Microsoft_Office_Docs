package com.example.apachepoi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFChart;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HssfWbk extends AppCompatActivity {
    Button btnexprtxl;
    EditText xlsxtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hssf_wbk);
        btnexprtxl=(Button) findViewById(R.id.btnexpxls);
        xlsxtv=(EditText) findViewById(R.id.xlstv);

        btnexprtxl.setOnClickListener(new View.OnClickListener() {
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

            File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            File file = new File(exportDir, "hssf_example.xls");
            file.createNewFile();
                if (file.exists()) {
                    System.out.println("file.xls " + exportDir.getAbsolutePath());
                    Toast.makeText(getApplicationContext(), "Writing data to excel file...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ATTENTION:The file already exists!", Toast.LENGTH_LONG).show();
                }try {
                HSSFWorkbook workbook =new HSSFWorkbook();
                HSSFSheet sheet=workbook.createSheet("Sheet 1");
                HSSFRow row1=sheet.createRow(0);
                HSSFCell cell1=row1.createCell(0);
                cell1.setCellValue("Description");
                HSSFCell cell2=row1.createCell(1);
                cell2.setCellValue("Amount");
                HSSFRow row2=sheet.createRow(1);
                HSSFCell cell3=row2.createCell(0);
                cell3.setCellValue(5);
                HSSFCell cell4= row2.createCell(1);
                cell4.setCellValue(5);
                HSSFCell cell5=row2.createCell(2);
                cell5.setCellFormula("SUM(A2+B2)");
                HSSFSheet sheet1=workbook.createSheet("sheet 2");

                FileOutputStream fileOutputStream=new FileOutputStream(file);
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
            }
        } catch (Exception e) {

        }
    }

}
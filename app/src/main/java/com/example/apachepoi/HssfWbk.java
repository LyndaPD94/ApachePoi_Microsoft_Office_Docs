package com.example.apachepoi;

import static com.example.apachepoi.Structure_BBDD.COLUMNAID;
import static com.example.apachepoi.Structure_BBDD.COLUMNDID;
import static com.example.apachepoi.Structure_BBDD.DATABASE_NAME;
import static com.example.apachepoi.Structure_BBDD.TABLE1;

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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFChart;
import org.apache.poi.hssf.usermodel.HSSFChildAnchor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

public class HssfWbk extends AppCompatActivity {
    Button btnexprtxl, btnexprtsql;
    EditText xlsxtv;
    DB_Helper helper;
    ArrayList<Sales> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hssf_wbk);
        btnexprtxl = (Button) findViewById(R.id.btnexpxls);
        xlsxtv = (EditText) findViewById(R.id.xlstv);
        btnexprtsql = (Button) findViewById(R.id.exphssfsql);
        btnexprtsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    exprtSQLTbl();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error");
                    Toast.makeText(getApplicationContext(), "Error: unable to export xlsx file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnexprtxl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    exprtxls();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error");
                    Toast.makeText(getApplicationContext(), "Error: unable to export database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void exprtxls() throws IOException {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Documents/hssf_example.xls");
            file.createNewFile();
            try {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Sheet 1");
                HSSFRow row1 = sheet.createRow(0);
                HSSFCell cell1 = row1.createCell(0);
                cell1.setCellValue("Description");
                HSSFCell cell2 = row1.createCell(1);
                cell2.setCellValue("Amount");

                HSSFRow row2 = sheet.createRow(1);
                HSSFCell cell3 = row2.createCell(0);
                cell3.setCellValue("Item 1");
                HSSFCell cell4 = row2.createCell(1);
                cell4.setCellValue(5);


                HSSFRow row3 = sheet.createRow(2);
                HSSFCell cell6 = row3.createCell(0);
                cell6.setCellValue("Item 2");
                HSSFCell hssfCell = row3.createCell(1);
                hssfCell.setCellValue(5);

                HSSFRow row4 = sheet.createRow(3);
                HSSFCell cell7 = row4.createCell(0);
                cell7.setCellValue("Item 3");
                HSSFCell hssfCell1 = row4.createCell(1);
                hssfCell1.setCellValue(5);
                HSSFRow row5 = sheet.createRow(4);
                HSSFCell cell8 = row5.createCell(0);
                cell8.setCellValue("Item 4");
                HSSFCell hssfCell2 = row5.createCell(1);
                hssfCell2.setCellValue(5);


                HSSFRow row6 = sheet.createRow(5);
                HSSFCell cell5 = row6.createCell(0);
                cell5.setCellValue("TOTAL");
                HSSFCell cell9 = row6.createCell(1);
                cell9.setCellFormula("SUM(B2:B5)");

                HSSFSheet sheet1 = workbook.createSheet("sheet 2");

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }
    }

    private void exprtSQLTbl() {
        try {
            File dbFile = getDatabasePath(DATABASE_NAME);
            helper = new DB_Helper(getApplicationContext(), "POI.db", null, 1);
            System.out.println(dbFile);
            File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            File file = new File(exportDir, "example_hssf_sql.xls");
            file.createNewFile();
            try {
                if (file.exists()) {
                    System.out.println("file.xls " + exportDir.getAbsolutePath());
                    Toast.makeText(getApplicationContext(), "Writing data to excel file...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ATTENTION:The file already exists!", Toast.LENGTH_LONG).show();
                }
                HSSFWorkbook wb = new HSSFWorkbook();
                SQLiteDatabase db = helper.getWriteableDatabase();
                Cursor cur = helper.exportAll();
                Sheet sheet = wb.createSheet("Sqlite data");
                data = new ArrayList<>();
                db = helper.getReadableDatabase();
                Row row = sheet.createRow(0);
                cur = db.rawQuery("select * from " + TABLE1, null);
                while (cur.moveToNext()) {
                    String arrStr[] = {
                            String.valueOf(cur.getString(0)),
                            String.valueOf(cur.getString(1)),
                            String.valueOf(cur.getString(2)),
                            String.valueOf(cur.getString(3)),
                            String.valueOf(cur.getString(4)),
                            String.valueOf(cur.getString(5)),
                            String.valueOf(cur.getString(6))};
                    for (int j = 0; j < arrStr.length; j++) {
                        while (cur.moveToPosition(j++)) {
                            Row row8 = sheet.createRow(j);
                            Cell cell8 = row8.createCell(0);
                            cell8.setCellValue(cur.getInt(0));
                            Cell cell9 = row8.createCell(1);
                            cell9.setCellValue(cur.getString(1));
                            Cell cell10 = row8.createCell(2);
                            cell10.setCellValue(cur.getString(2));
                            Cell cell11 = row8.createCell(3);
                            cell11.setCellValue(cur.getFloat(3));
                            Cell cell12 = row8.createCell(4);
                            cell12.setCellValue(cur.getFloat(4));
                            Cell cell13 = row8.createCell(5);
                            cell13.setCellValue(cur.getFloat(5));
                            Cell cell14 = row8.createCell(6);
                            cell14.setCellValue(cur.getString(6));
                        }
                    }
                }
//-----------------------------------------------Headings-------------------------------------
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(cur.getColumnName(0));
                Cell cell = row.createCell(1);
                cell.setCellValue(cur.getColumnName(1));
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(cur.getColumnName(2));
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(cur.getColumnName(3));
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(cur.getColumnName(4));
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(cur.getColumnName(5));
                Cell cell6 = row.createCell(6);
                cell6.setCellValue(cur.getColumnName(6));
                cur.close();
//------------------------------------------------------------------------------------------------

                Row rowa = sheet.getRow(0);
                rowa.setHeightInPoints(Float.parseFloat("16.75"));
                Cell cella = rowa.getCell(0);
                cella.getCellStyle().setAlignment(HorizontalAlignment.CENTER);

//--------------------------------------------------------------------------------------------------
                FileOutputStream fileOut = new FileOutputStream(file);
                wb.write(fileOut);
                fileOut.close();
                Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }
    }
}



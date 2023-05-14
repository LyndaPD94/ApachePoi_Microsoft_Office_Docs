package com.example.apachepoi;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XWPFWbk extends AppCompatActivity {
    Button btnexpdocx;
    EditText docxtv;
    private Color RRGGBB;

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
                    XWPFDocument xwpfDocument=new XWPFDocument();
                    XWPFParagraph xwpfParagraph=xwpfDocument.createParagraph();
                    XWPFRun xwpfRun=xwpfParagraph.createRun();
                    xwpfRun.setText(docxtv.getText().toString());
                    xwpfRun.setFontFamily("Arial");
                    xwpfRun.addBreak();
                    XWPFTable table=xwpfDocument.createTable();
                    XWPFTableRow row=table.createRow();

                    XWPFTableCell cell=row.createCell();
                    row.getCell(0).setText("Description");
                    row.getCell(1).setText("Amount");
                    XWPFTableRow row2=table.createRow();
                   XWPFTableCell cell1=row2.createCell();
                   row2.getCell(0).setText("6");
                   row2.getCell(1).setText("5");
                   xwpfRun.addBreak();

                   XWPFParagraph paragraph= xwpfRun.getDocument().createParagraph();
                   XWPFRun run2=paragraph.createRun();
                   run2.addBreak();
                   run2.setFontFamily("Times New Roman");
                   run2.setText("Se tomaron las estimaciones de las infraestructuras con la asunción que la tierra está cubierta con arbusto al nivel mediano y tiene arboles frutales y especies que han sido sembrado por los dueños de la tierra hace años y tiene la edad de producción máxima. La superficie es semi plano y hay un río menos de 50 metros de la tierra. Según su ubicación geográfica en el país, se asumo que el suelo es arcilloso-francoso con un porcentaje alto de materia orgánica");
                   paragraph.setPageBreak(true);
                   


                    FileOutputStream fileOut=new FileOutputStream(file);
                    xwpfDocument.write(fileOut);
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
    private void exportdoc2(){
        /*xwpfRun.setColor("RRGGBB");
                    xwpfRun.setFontSize(11.5);
                    //xwpfRun.setFontFamily("Arial");
                    //xwpfRun.addBreak();
                    xwpfDocument.getXWPFDocument().createChart(5,5);*/
    }
}
package com.example.apachepoi;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.hslf.model.HeadersFooters;

import org.apache.poi.hwpf.model.FormattedDiskPage;
import org.apache.poi.hwpf.usermodel.HeaderStories;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFFootnotes;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFNum;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStyleImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class XWPFWbk extends AppCompatActivity {
    Button btnexpdocx;
    EditText docxtv;
    private Color RRGGBB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwpfwbk);
        btnexpdocx = (Button) findViewById(R.id.exprtdocx);
        docxtv = (EditText) findViewById(R.id.doocxtv);
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
        String input = "/sdcard/Documents/moon1.png";
        try {
            File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "xwpf_example.docx");
                file.createNewFile();
                try {
                    XWPFDocument xwpfDocument = new XWPFDocument();
                    XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
                    XWPFRun xwpfRun = xwpfParagraph.createRun();
                    xwpfRun.setText("El jugo de limón se usa en la medicina tradicional como diaforético y diurético, como gárgaras, loción y tónico. La sal es indispensable para el cuerpo, al igual que el agua y el oxígeno. Tu cuerpo necesita sal para funcionar normalmente, sin embargo, el exceso de sal puede causar estrés en el corazón. Es mejor usar sal del Himalaya en lugar de sal de mesa normal. La pimienta negra también se ha utilizado como medicina tradicional durante mucho tiempo. Por lo tanto, no es sorprendente que esta combinación pueda ayudar a combatir las siguientes dolencias.");
                    xwpfRun.setFontFamily("Arial");
                    xwpfRun.addBreak();
                    XWPFTable table = xwpfDocument.createTable();
                    XWPFTableRow row = table.createRow();

                    XWPFTableCell cell = row.createCell();
                    row.getCell(0).setText("Description");
                    row.getCell(1).setText("Amount");
                    XWPFTableRow row2 = table.createRow();
                    XWPFTableCell cell1 = row2.createCell();
                    row2.getCell(0).setText("6");
                    row2.getCell(1).setText("5");
                    xwpfRun.addBreak();


                    XWPFParagraph paragraph = xwpfRun.getDocument().createParagraph();
                    XWPFRun run2 = paragraph.createRun();
                    run2.addBreak();
                    run2.setFontFamily("Times New Roman");
                    run2.setText("Se tomaron las estimaciones de las infraestructuras con la asunción que la tierra está cubierta con arbusto al nivel mediano y tiene arboles frutales y especies que han sido sembrado por los dueños de la tierra hace años y tiene la edad de producción máxima. La superficie es semi plano y hay un río menos de 50 metros de la tierra. Según su ubicación geográfica en el país, se asumo que el suelo es arcilloso-francoso con un porcentaje alto de materia orgánica");
                    paragraph.setPageBreak(true);
                    XWPFRun run3 = paragraph.createRun();
                    run3.setText("El jugo de limón se usa en la medicina tradicional como diaforético y diurético, como gárgaras, loción y tónico. La sal es indispensable para el cuerpo, al igual que el agua y el oxígeno. Tu cuerpo necesita sal para funcionar normalmente, sin embargo, el exceso de sal puede causar estrés en el corazón. Es mejor usar sal del Himalaya en lugar de sal de mesa normal. La pimienta negra también se ha utilizado como medicina tradicional durante mucho tiempo. Por lo tanto, no es sorprendente que esta combinación pueda ayudar a combatir las siguientes dolencias.");
                    run3.setFontSize(17);
                    run3.setFontFamily("Book Antiqua");

                    paragraph.setIndentationFirstLine(1);
                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                    paragraph.setSpacingAfterLines(1);
                    paragraph.setBorderBottom(Borders.DOTTED);
                    paragraph.setBorderTop(Borders.DOTTED);
                    paragraph.setBorderLeft(Borders.ARCHED_SCALLOPS);
                    paragraph.setBorderRight(Borders.ARCHED_SCALLOPS);
                    run3.addBreak(BreakType.PAGE);

                    XDDFChart chart = run3.getDocument().createChart();
                    /*XDDFValueAxis yvalue=chart.createValueAxis(AxisPosition.LEFT);
                    XDDFChartAxis xaxis=chart.createCategoryAxis(AxisPosition.BOTTOM);
                    XDDFChartData chartData=chart.createData(ChartTypes.BAR,xaxis,yvalue);

                    yvalue.setTitle("Title Y Axis");
                    XDDFValueAxis xvalueAxis=chart.createValueAxis(AxisPosition.BOTTOM);
                    xvalueAxis.setTitle("Title X Axis");*/



                    FileOutputStream fileOut = new FileOutputStream(file);
                    xwpfDocument.write(fileOut);
                    fileOut.close();
                    Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.getCause();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error 2:File does not contain any data.", Toast.LENGTH_LONG).show();
        }
    }

    private void exportdoc2() throws IOException, InvalidFormatException {
        try {
            XWPFDocument xwpfDocument = new XWPFDocument();
            XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
            XWPFRun xwpfRun = xwpfParagraph.createRun();
            xwpfRun.setColor("RRGGBB");
            xwpfRun.setFontSize(11.5);
            //xwpfRun.setFontFamily("Arial");
            //xwpfRun.addBreak();
            xwpfDocument.getXWPFDocument().createChart(5, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
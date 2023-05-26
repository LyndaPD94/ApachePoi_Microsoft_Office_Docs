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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisLabelAlignment;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.BarGrouping;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLegendEntry;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFSeriesAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFChart;
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class XWPFWbk extends AppCompatActivity {
    Button btnexpdocx;
    EditText docxtv;
    public XDDFDataSource<String>xddfDataSource;
    public XDDFNumericalDataSource<Number>xddfNumericalDataSource;
    List<XDDFChartData>xddfChartData;
    List<InputData>inputData;

    private static final int COLUMN_LANGUAGES = 0;
    private static final int COLUMN_COUNTRIES = 1;
    private static final int COLUMN_SPEAKERS = 2;


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
                    XWPFRun run4=paragraph.createRun();
                    XWPFParagraph paragraph1=run4.getDocument().createParagraph();
                    paragraph1.setIndentationFirstLine(1);
                    paragraph1.setAlignment(ParagraphAlignment.CENTER);
                    paragraph1.setSpacingAfterLines(1);
                    paragraph1.setBorderBottom(Borders.BASIC_BLACK_SQUARES);
                    paragraph1.setBorderTop(Borders.BASIC_BLACK_SQUARES);
                    paragraph1.setBorderLeft(Borders.BASIC_BLACK_SQUARES);
                    paragraph1.setBorderRight(Borders.BASIC_BLACK_SQUARES);
                    run4.setText("Chart");
                    run4.addBreak();
                    insertChart(run4);



                    /*XDDFChart chart = run3.getDocument().createChart();
                    XDDFCategoryAxis categoryAxis=chart.createCategoryAxis(AxisPosition.BOTTOM);
                    categoryAxis.getOrAddMajorGridProperties().getLineProperties().addDashStop();
                    categoryAxis.setTitle("Title");
                    categoryAxis.getLabelAlignment().compareTo(AxisLabelAlignment.CENTER);
                    XDDFValueAxis valueAxis=chart.createValueAxis(AxisPosition.BOTTOM);
                    //XDDFChartData xddfChartData=chart.createData(ChartTypes.BAR,categoryAxis,valueAxis);
                    //xddfChartData.getValueAxes().set(0,valueAxis);
                    //XDDFChartAxis axis= chart.createCategoryAxis(AxisPosition.BOTTOM);
                    //XDDFValueAxis valueAxis=chart.createValueAxis(AxisPosition.BOTTOM);
                    //XDDFChartData chartData=chart.createData(ChartTypes.BAR,axis,valueAxis);
                    //XDDFChartLegend chartLegend=chart.getOrAddLegend();
                    //XDDFLegendEntry legendEntry= chartLegend.addEntry();
                    //XDDFTextBody textBody=legendEntry.getTextBody();
                    //valueAxis.setTitle("Title");*/



                    FileOutputStream fileOut = new FileOutputStream(file);
                    xwpfDocument.write(fileOut);
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
            Toast.makeText(getApplicationContext(), "Error 2:File does not contain any data.", Toast.LENGTH_LONG).show();
        }
    }
private void insertChart(XWPFRun run4){
    try {
        File file2 = new File(Environment.getExternalStorageDirectory()+"/Documents/bar-chart-data.txt");
        try (BufferedReader modelReader = Files.newBufferedReader(file2.toPath(), StandardCharsets.UTF_8)) {

            String chartTitle = modelReader.readLine();  // first line is chart title
            String seriesText = modelReader.readLine();
            String[] series = seriesText == null ? new String[0] : seriesText.split(",");

            // Category Axis Data
            List<String> listLanguages = new ArrayList<>(10);

            // Values
            List<Double> listCountries = new ArrayList<>(10);
            List<Double> listSpeakers = new ArrayList<>(10);

            // set model
            String ln;
            while ((ln = modelReader.readLine()) != null) {
                String[] vals = ln.split(",");
                listCountries.add(Double.valueOf(vals[0]));
                listSpeakers.add(Double.valueOf(vals[1]));
                listLanguages.add(vals[2]);
            }

            String[] categories = listLanguages.toArray(new String[0]);
            Double[] values1 = listCountries.toArray(new Double[0]);
            Double[] values2 = listSpeakers.toArray(new Double[0]);


            XWPFChart chart = run4.getDocument().createChart(XDDFChart.DEFAULT_WIDTH * 10, XDDFChart.DEFAULT_HEIGHT * 15);
            XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle(series[2]);
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle(series[0] + "," + series[1]);
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
            leftAxis.setMajorTickMark(AxisTickMark.OUT);
            leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

            final int numOfPoints = categories.length;
            final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
            final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_COUNTRIES, COLUMN_COUNTRIES));
            final String valuesDataRange2 = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_SPEAKERS, COLUMN_SPEAKERS));
            final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, COLUMN_LANGUAGES);
            final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(values1, valuesDataRange, COLUMN_COUNTRIES);
            valuesData.setFormatCode("General");
            values1[6] = 16.0; // if you ever want to change the underlying data, it has to be done before building the data source
            final XDDFNumericalDataSource<? extends Number> valuesData2 = XDDFDataSourcesFactory.fromArray(values2, valuesDataRange2, COLUMN_SPEAKERS);
            valuesData2.setFormatCode("General");


            XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            bar.setBarGrouping(BarGrouping.CLUSTERED);

            XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
            series1.setTitle(series[0], chart.setSheetTitle(series[0], COLUMN_COUNTRIES));

            XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
            series2.setTitle(series[1], chart.setSheetTitle(series[1], COLUMN_SPEAKERS));

            bar.setVaryColors(true);
            bar.setBarDirection(BarDirection.COL);
            chart.plot(bar);
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.LEFT);
            legend.setOverlay(false);

            chart.setTitleText(chartTitle);
            chart.setTitleOverlay(false);
            chart.setAutoTitleDeleted(false);
          ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }catch (Exception e){
        e.printStackTrace();
    }
}
    private void exportdoc2() throws IOException, InvalidFormatException {
        try {
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
                        XWPFRun run3 = xwpfParagraph.createRun();
                        XDDFChart chart = run3.getDocument().createChart();
                        XDDFChartAxis axis= chart.createCategoryAxis(AxisPosition.BOTTOM);
                        XDDFValueAxis valueAxis=chart.createValueAxis(AxisPosition.BOTTOM);
                        XDDFChartData chartData=chart.createData(ChartTypes.BAR,axis,valueAxis);
                    } catch (Exception e) {
                        e.getCause();
                        Toast.makeText(getApplicationContext(), "No chart", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error 2:File does not contain any data.", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private   List<XDDFValueAxis> getValueAxes(){
        List<InputData>inputData=new ArrayList<>();
        InputData input= new InputData();
        input.setTwo(Float.parseFloat(String.valueOf(2)));
        input.setEleven(Float.parseFloat(String.valueOf(11)));
        input.setThree(Float.parseFloat(String.valueOf(3)));
        input.setThree(Float.parseFloat(String.valueOf(3)));
        inputData.add(input);
        return getValueAxes();
    }
}
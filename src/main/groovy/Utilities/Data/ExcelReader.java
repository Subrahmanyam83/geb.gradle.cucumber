package Utilities.Data;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by in02183 on 2/8/2016.
 */
public class ExcelReader{

    public String path;
    public FileInputStream fis = null;
    private HSSFWorkbook workbook = null;
    private HSSFSheet sheet = null;
    private HSSFRow row = null;
    private HSSFCell cell = null;
    private String sheetName;
    private int columnIndex;
    private String cellText;

    public ExcelReader(String path,String sheetName) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new HSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            this.sheetName = sheetName;
            fis.close();
        } catch (Exception e) {
        }

    }

    /*Returns the row count in a sheet*/
    public int getRowCount(String sheetName) {
        System.out.println("Im in Exc elcel readder getrow count class");
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }
    }

    /*Get Excel Data*/
    public String getData(int rowNumber, String columnName) {
        int index = workbook.getSheetIndex(sheetName);
        sheet = workbook.getSheetAt(index);
        int columnIndex = getColumnIndex(columnName);
        if (rowNumber >= sheet.getPhysicalNumberOfRows()) {
            return "";
        } else {
            row = sheet.getRow(rowNumber);
            cell = row.getCell(columnIndex);
            if (cell != null) {
                if (cell.getCellTypeEnum() == CellType.STRING) {
                    cellText = cell.getStringCellValue().trim();
                } else if (cell.getCellTypeEnum() == CellType.NUMERIC
                        || cell.getCellTypeEnum() == CellType.FORMULA) {
                    cellText = String.valueOf(cell.getNumericCellValue()).replaceFirst(".0", "");
                }
            }
        }
        return cellText;
    }

    public String[][] getDataArrayBySheet(String sheetName) throws Throwable{
        int rows=-1;
        int columns=-1;
        String[][] data=null;
        try{

            int index = workbook.getSheetIndex(sheetName);
            boolean flag = false;
            if (index == -1)
            {
                return null;
            }
            sheet = workbook.getSheetAt(index);
            rows=sheet.getPhysicalNumberOfRows();
            columns=sheet.getRow(0).getLastCellNum();
            data=new String[rows-1][columns];
            for (int i = 1; i < rows; i++) {
                for(int j=0;j<columns;j++)
                {
                    try
                    {
                        row = sheet.getRow(i);
                        data[i-1][j]=row.getCell(j).toString();
                    }
                    catch(NullPointerException e)
                    {
                        break;
                    }
                }
            }
            return data;
        }

        catch(Exception e)
        {
            return null;
        }
    }

    /*Returns Required Column Number*/
    public int getColumnIndex(String columnName) {
        row = sheet.getRow(0);
        int cellNumber = row.getPhysicalNumberOfCells();
        for (int j = 0; j < cellNumber; j++) {
            String text = row.getCell(j).toString();
                if (text.equalsIgnoreCase(columnName)) {
                    columnIndex = j;
                }
        }
        return columnIndex;
    }


    public  List<String> getRequiredCSVColumnData(String filePath, String columnName, int columnNo) throws IOException {
        String csvFile = "C://Users//IN02150//Downloads//variants_25556_32515.CSV";
        List<String> requiredData = new LinkedList<String>();
        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        String[] row;
        while((row = csvReader.readNext()) != null) {
            if(row[columnNo]!= columnName){
                requiredData.add(row[columnNo]);
            }
        }
        return requiredData;
    }
}
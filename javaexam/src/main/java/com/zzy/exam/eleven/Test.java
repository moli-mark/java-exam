package com.zzy.exam.eleven;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;

/**
 * @author molimark<br />
 * @date: 2022/12/2 10:58<br/>
 * @description: <br/>
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //创建Excel，读取文件内容
        File file=new File("D:\\examtest\\t.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(FileUtils.openInputStream(file));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet=workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum=sheet.getLastRowNum();
        for (int i=0;i<=lastRowNum;i++){
            Row row=sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum=row.getLastCellNum();
            for (int j=0;j<lastCellNum;j++){
                DataFormatter formatter=new DataFormatter();
                String value=formatter.formatCellValue(row.getCell(j));
                System.out.print(value+" ");
            }
            System.out.println();
        }
    }
}

package com.zzy.exam.two;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl;

import java.io.*;
import java.net.URL;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        CustomXWPFDocument doc = new CustomXWPFDocument(new FileInputStream("D:\\examtest\\test2.docx"));//导入模板文件
        doc.createPicture(doc.addPictureData(new FileInputStream("D:\\examtest\\a\\3.png"),XWPFDocument.PICTURE_TYPE_PNG),doc.getNextPicNameNumber(XWPFDocument.PICTURE_TYPE_PNG),200, 150);
        Map<String, Object> params = new HashMap<>();//文字类 key-value
        params.put("$$UserNo","1");
        params.put("$$userName","zhangsan");
        Map<String,String> picParams = new HashMap<>();//图片类 key-url
        picParams.put("$$image","D:\\examtest\\a\\3.png");
        List<IBodyElement> ibes = doc.getBodyElements();
        for (IBodyElement ib : ibes) {
            if (ib.getElementType() == BodyElementType.TABLE) { //文件中有表格
                replaceTable(ib, params, picParams, doc);
            }
        }
        doc.write(new FileOutputStream("D:\\examtest\\res2.docx"));
    }

    public static void replaceTable(IBodyElement para ,Map<String, Object> params,
                                    Map<String, String> picParams, XWPFDocument indoc)
            throws Exception {
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        table = (XWPFTable) para;
        rows = table.getRows();
        for (XWPFTableRow row : rows) {
            cells = row.getTableCells();
            int cellsize = cells.size();
            int cellcount = 0;
            for(cellcount = 0; cellcount<cellsize;cellcount++){
                XWPFTableCell cell = cells.get(cellcount);
                List<XWPFParagraph> ps = cell.getParagraphs();
                System.out.println("cell:"+cell.getText());
                String text = cell.getText();
                boolean flag = false;
                boolean flag2 = false;
                for(String key : params.keySet()){
                    if(text.equals(key))flag=true;
                }
                for(String key : picParams.keySet()){
                    if(text.equals(key))flag2=true;
                }
                for (XWPFParagraph p : ps) {
                    List<XWPFRun> runs = p.getRuns();
                    for(int i=0;i<runs.size();i++){
                        XWPFRun run = runs.get(i);
                        if(flag2){
                            System.out.println("pic:"+picParams.get(text));
                            p.removeRun(i);
                            String imgFile = picParams.get(text);
                            int format = 0;
                            if (imgFile.endsWith(".emf"))
                                format = Document.PICTURE_TYPE_EMF;
                            else if (imgFile.endsWith(".wmf"))
                                format = Document.PICTURE_TYPE_WMF;
                            else if (imgFile.endsWith(".pict"))
                                format = Document.PICTURE_TYPE_PICT;
                            else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
                                format = Document.PICTURE_TYPE_JPEG;
                            else if (imgFile.endsWith(".png"))
                                format = Document.PICTURE_TYPE_PNG;
                            else if (imgFile.endsWith(".dib"))
                                format = Document.PICTURE_TYPE_DIB;
                            else if (imgFile.endsWith(".gif"))
                                format = Document.PICTURE_TYPE_GIF;
                            else if (imgFile.endsWith(".tiff"))
                                format = Document.PICTURE_TYPE_TIFF;
                            else if (imgFile.endsWith(".eps"))
                                format = Document.PICTURE_TYPE_EPS;
                            else if (imgFile.endsWith(".bmp"))
                                format = Document.PICTURE_TYPE_BMP;
                            else if (imgFile.endsWith(".wpg"))
                                format = Document.PICTURE_TYPE_WPG;
                            if(i==0){
                                System.out.println("hi");
                                CTR ctr = p.createRun().getCTR();
                                p.getRun(ctr).setText("");
                                p.getRun(ctr).addPicture(new FileInputStream(imgFile), format, "res2",Units.toEMU(10000000),Units.toEMU(100000000));
                            }
                            else p.insertNewRun(i).setText("");
                        }
                        if(flag) {
                            p.removeRun(i);
                            if(i==0){
                                p.insertNewRun(i).setText((String) params.get(text));
                            }else{
                                p.insertNewRun(i).setText("");
                            }
                        }
                    }
                }
            }
        }
    }
}
package twelve;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author molimark<br />
 * @date: 2022/12/2 15:30<br/>
 * @description: <br/>
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("./test.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        String sql = "select * from t_news order by Id asc";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        String[] title = {"Id","Stuno","newsTitle","newsDate"};
        File file = new File("./news.xls");
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        //创建sheet
        WritableSheet sheet = workbook.createSheet("sheet1", 0);
        Label label = null;
        for(int i=0;i<title.length;i++){
            label = new Label(i,0,title[i]);
            sheet.addCell(label);
        }
        int idx = 1;
        while(resultSet.next()){
            String id= resultSet.getString(1);
            String stuno = resultSet.getString(2);
            String newsTitle = resultSet.getString(3);
            String newsDate = resultSet.getString(4);
            label = new Label(0,idx,id);
            sheet.addCell(label);
            label = new Label(1,idx,stuno);
            sheet.addCell(label);
            label = new Label(2,idx,newsTitle);
            sheet.addCell(label);
            label = new Label(3,idx,newsDate);
            sheet.addCell(label);
            idx++;
        }
        workbook.write();    //写入数据
        workbook.close();  //关闭连接
        stmt.close();
        connection.close();
    }
}

package com.zzy.exam.five;

/**
 * @author molimark<br />
 * @date: 2022/12/2 13:37<br/>
 * @description: <br/>
 */

import com.zzy.exam.five.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cxx
 * Dom操作xml
 * @Date: 2018/5/29 20:19
 */
public class DomDemo {
    public static Map<String,Integer> map = new HashMap<>();
    //用Element方式
//    public static void element(NodeList list){
//        for (int i = 0; i <list.getLength() ; i++) {
//            Element element = (Element) list.item(i);
//            NodeList childNodes = element.getChildNodes();
//            for (int j = 0; j <childNodes.getLength() ; j++) {
//                if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
//                    //获取节点
//                    System.out.print(childNodes.item(j).getNodeName() + ":");
//                    //获取节点值
//                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
//                }
//            }
//        }
//    }

    public static void draw(DefaultPieDataset pds,String filePath){

        try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createPieChart("不同班级人数的分布图", pds, false, false, true);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            // 得到图块,准备设置标签的字体
            PiePlot plot = (PiePlot) chart.getPlot();
            // 设置标签字体
            plot.setLabelFont(font);
            plot.setStartAngle(new Float(3.14f / 2f));
            // 设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            // 设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            // 设置标签生成器(默认{0})
            // {0}:key {1}:value {2}:百分比 {3}:sum
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}占{2})"));
            // 将内存中的图片写到本地硬盘
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void node(NodeList list){
        for (int i = 0; i <list.getLength() ; i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            String name="";
            Integer count;
            for (int j = 0; j <childNodes.getLength() ; j++) {
                if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    if(childNodes.item(j).getNodeName().equals("name")){
                        name = childNodes.item(j).getFirstChild().getNodeValue();
                    }else if(childNodes.item(j).getNodeName().equals("count")){
                        map.put(name,Integer.parseInt(childNodes.item(j).getFirstChild().getNodeValue()));
                    }
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());

                }
            }
        }
    }

    public static void main(String[] args) {
        //1.创建DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse("D:\\javacoding\\juc\\src\\main\\java\\com\\zzy\\exam\\five\\ttest.xml");
            NodeList sList = d.getElementsByTagName("class");
            //element(sList);
            node(sList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultPieDataset pds = new DefaultPieDataset();
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            pds.setValue(entry.getKey(), entry.getValue());
        }
        String filePath = "D:\\examtest\\pie.jpg";
        draw(pds,filePath);
    }
}

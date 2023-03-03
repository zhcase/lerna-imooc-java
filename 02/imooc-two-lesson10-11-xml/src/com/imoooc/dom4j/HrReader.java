package  com.imoooc.dom4j;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.sql.SQLOutput;
import java.util.List;

public class HrReader {
    public void readXml(){
        String file= "I:/java/lerna/imoooc/lerna-imooc-java/02/imooc-two-lesson10-11-xml/src/hr.xml";
        // SAXReader类是读取XML文件的核心类，用于将xml解析后以“树”的形式保存在内存中
        SAXReader reader=new SAXReader();
        try {
            Document document = reader.read(file);
            // 获取XML文档的根节点
            Element root = document.getRootElement();
            // elements 方法用于获取指定的标签集合
            List<Element> employees=root.elements("employee");
            for(Element employee : employees){
                // element 方法用于获取唯一的子节点对象
              Element name=employee.element("name");
              String empName=name.getText();
              // getText();方法用于获取标签文本
              System.out.println(empName);
              System.out.println(employee.elementText("age"));
              System.out.println(employee.elementText("salary"));
              Element department=employee.element("department");
              System.out.println(department.element("dname").getText());
              System.out.println(department.elementText("address"));
              Attribute att =employee.attribute("no");
              System.out.println(att.getText());
            }
        }
        catch (DocumentException e){

            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
            HrReader reader=new HrReader();
            reader.readXml();
    }
}


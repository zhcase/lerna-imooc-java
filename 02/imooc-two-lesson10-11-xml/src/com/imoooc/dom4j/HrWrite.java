package com.imoooc.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class HrWrite {
    public void writeXml(){
        String file= "I:/java/lerna/imoooc/lerna-imooc-java/02/imooc-two-lesson10-11-xml/src/hr.xml";
        SAXReader reader=new SAXReader();
        try {
        Document document=reader.read(file);
        Element root=document.getRootElement();
        Element employee = root.addElement("employee");
        employee.addAttribute("no","3311");
        Element name=employee.addElement("name");
        name.setText("李铁柱");
        employee.addElement("age").setText("37");
        employee.addElement("salary").setText("3600");
        Element department=employee.addElement("department");
        department.addElement("dname").setText("人事部");
        department.addElement("address").setText("xxx大厦-B105");
        Writer write=new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            System.out.println(document);
        document.write(write);
        write.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
            HrWrite hrWrite=new HrWrite();
            hrWrite.writeXml();
    }
}

package com.imooc.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerSample1 {
    public static void main(String[] args) throws IOException, TemplateException {
//        1.加载模板
        Configuration config = new Configuration(Configuration.VERSION_2_3_32);
        //设置加载的目录
        config.setClassForTemplateLoading(FreemarkerSample1.class, "");
        //得到模板对象
        Template t = config.getTemplate("sample1.ftl");
//        2.创建数据
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("site", "百度");
        data.put("url", "http://www.baidu.com");
//        3.产生输出
        t.process(data, new OutputStreamWriter(System.out));
    }
}

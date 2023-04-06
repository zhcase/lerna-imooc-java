package com.imooc.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量值
 */
@Component
public class Constant {
    public  static  final String SALT="7878@wweASSDW77";
    public  static  final  String IMOOC_MALL_USER="imooc_small_user";

    public  static  String FILE_UPLOAD_DIR;
    @Value("${file.upload.dir}")
    public  void  setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR=fileUploadDir;
    }

}

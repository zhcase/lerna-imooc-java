package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMailExceptionEnum;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.AddProductRequest;
import com.imooc.mall.model.request.UpdateProductRequest;
import com.imooc.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * 后台商品管理Controller
 */

@RestController
public class ProductAdminController {
    @Autowired
    ProductService productService;

    @PostMapping("admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductRequest addProductRequest) throws ImoocMallException {
        productService.add(addProductRequest);
        return ApiRestResponse.success();
    }

    @PostMapping("admin/upload/file")
    public ApiRestResponse upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws ImoocMallException, IOException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成文件名称uuid
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid + suffixName;
        //创建文件
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new ImoocMallException(ImoocMailExceptionEnum.MKDIR_FAILED);
            }
        }
        file.transferTo(destFile);
        try {
            return ApiRestResponse.success(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/" + newFileName);
        } catch (URISyntaxException e) {
            return ApiRestResponse.error(ImoocMailExceptionEnum.UPLOAD_FAILED);
//            throw new RuntimeException(e);
        }
    }

    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
            throw new RuntimeException(e);
        }
        return effectiveURI;
    }
    @ApiOperation("后台更新商品")
    @PostMapping("admin/product/update")
    public  ApiRestResponse updateProduct(@Valid  @RequestBody UpdateProductRequest updateProductRequest){
       Product  product=new Product();
        BeanUtils.copyProperties(updateProductRequest,product);
        try {
            productService.update(product);
        } catch (ImoocMallException e) {
            throw new RuntimeException(e);
        }
        return ApiRestResponse.success();
    }
    @ApiOperation("后台删除商品")
    @PostMapping("admin/product/delete")
    public  ApiRestResponse deleteProduct(@RequestParam Integer id){
        try {
            productService.delete(id);
        } catch (ImoocMallException e) {
            throw new RuntimeException(e);
        }
        return ApiRestResponse.success();
    }


}

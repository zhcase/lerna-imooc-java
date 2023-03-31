package com.imooc.springbootlearn;

import org.springframework.web.bind.annotation.*;

/**
 * 演示各种传参形式
 */
@RestController
@RequestMapping({"/prefix"})
public class ParaController {
    @GetMapping({"/firstrequest"})
    public String firstRequest() {
        return "第一个 spring接口 hello word";
    }

    @GetMapping({"/requestpara"})
    public String requestpara(@RequestParam Integer num) {
        return "para from request:" + num;
    }

    @GetMapping({"/para/{num}"})
    public String pathpara(@PathVariable Integer num) {
        return "para from path:" + num;

    }
    @GetMapping({"/multiurl1","/multiurl2"})
    public  String multiurl(@RequestParam Integer num){
        return "para from path:" + num;
    }

    @GetMapping({"/required"})
    public String required(@RequestParam(required = false,defaultValue = "0") Integer num) {
        return "para from request:" + num;
    }
}

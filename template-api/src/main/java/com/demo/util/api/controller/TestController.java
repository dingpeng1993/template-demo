package com.demo.util.api.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.util.common.object.response.BaseResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dp
 * @date 2020/11/22 7:24 下午
 */
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @GetMapping("/hello")
    public BaseResponse<ModelMap> test() {
        log.info("test");
        ModelMap modelMap = new ModelMap();
        modelMap.put("result", "helloWord");
        System.out.println("test");
        return BaseResponse.success(modelMap);
    }
}

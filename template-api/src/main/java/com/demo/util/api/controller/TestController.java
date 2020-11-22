package com.demo.util.api.controller;

import com.demo.util.common.object.response.BaseResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dp
 * @date 2020/11/22 7:24 下午
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    public BaseResponse<ModelMap> test() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("result", "helloWord");
        return BaseResponse.success(modelMap);
    }
}

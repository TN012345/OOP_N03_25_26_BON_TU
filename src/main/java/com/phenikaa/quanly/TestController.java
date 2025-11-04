package com.phenikaa.quanly;

import com.phenikaa.quanly.MyGlobal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-error")
    public String testError() {
        MyGlobal.indexError = -1;
        return "Index error = " + MyGlobal.indexError;
    }
}

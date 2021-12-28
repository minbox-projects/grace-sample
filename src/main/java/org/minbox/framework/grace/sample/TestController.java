package org.minbox.framework.grace.sample;

import org.minbox.framework.grace.expression.annotation.GraceRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 恒宇少年
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping
    @GraceRecorder(success = "参数：{#p0}，结果：{#result}", category = "index", operator = "{#name}")
    public String index(String name, String userId) {
        name = testService.getUserName(userId);
        testService.getUserList(userId);
        return "Hello, " + name;
    }
}

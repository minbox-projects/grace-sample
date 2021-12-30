package org.minbox.framework.grace.sample;

import org.minbox.framework.grace.core.GraceVariableContext;
import org.minbox.framework.grace.expression.annotation.GraceRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author 恒宇少年
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;

    @GetMapping
    @GraceRecorder(success = "参数：{#name}，结果：{#result}，地址：{#address}", category = "index", operator = "{#name}")
    public String index(String name, String userId) {
        name = testService.getUserName(userId);
        testService.getUserList(userId);
        GraceVariableContext.setVariable("address", "中国" + userId);
        return "Hello, " + name;
    }

    @GetMapping("/changePassword")
    @GraceRecorder(category = "Test", success = "修改用户：{@userService.getUserRealName(#map.get('userId'))}的密码，改后为：{#map.get('newPassword')}")
    public String useMap(@RequestParam HashMap<String, Object> map) {
        userService.updateAgeById((String) map.get("userId"), 35);
        return "The password changed.";
    }
}

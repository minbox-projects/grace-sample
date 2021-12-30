package org.minbox.framework.grace.sample;

import lombok.Data;
import org.minbox.framework.grace.expression.annotation.GraceRecorder;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑
 *
 * @author 恒宇少年
 */
@Service
public class UserService {
    @GraceRecorder(category = "User", success = "用户：{#userId} 查询到的昵称为：{#result}")
    public String getUserRealName(String userId) {
        return "恒宇少年";
    }

    @GraceRecorder(category = "User", success = "用户：{#userId}，年龄：{#result.age}")
    public User getUserById(String userId) {
        return new User();
    }

    @GraceRecorder(category = "User", condition = "{#age>20 and #age<60}", success = "用户：{@userService.getUserRealName(#userId)}，年龄超过{#age}")
    public void updateAgeById(String userId, int age) {
        System.out.println(age);
    }

    @Data
    public static class User {
        private String userId;
        private String userName;
        private int age;
    }
}

package org.minbox.framework.grace.sample;

import org.minbox.framework.grace.expression.annotation.GraceFunction;
import org.minbox.framework.grace.expression.annotation.GraceFunctionDefiner;
import org.minbox.framework.grace.expression.annotation.GraceRecorder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 恒宇少年
 */
@Service
public class TestService {
    @GraceRecorder(condition = "{#result!=null and #age>10}", success = "参数：{#p0}，返回结果：{#result}", category = "getUserName")
    public String getUserName(String userId) {
        if (userId.equals("11")) {
            throw new RuntimeException("");
        }
        return userId + "...";
    }

    @GraceRecorder(condition = "{#result!=null and #result.size()>0}", success = "获取用户列表成功，第一个数据：{#result.get(0)}", category = "getUserList")
    public List<String> getUserList(String userId) {
        List<String> userIdList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userIdList.add(userId + i);
        }
        return userIdList;
    }
}

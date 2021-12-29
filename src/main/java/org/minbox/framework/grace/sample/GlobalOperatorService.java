package org.minbox.framework.grace.sample;

import org.minbox.framework.grace.core.operator.GraceLoadOperatorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 恒宇少年
 */
@Service
public class GlobalOperatorService implements GraceLoadOperatorService {
    @Override
    public String getOperatorName() {
        return "恒宇少年";
    }

    @Override
    public String getOperatorId() {
        return "123";
    }

    @Override
    public Map<String, Object> getExtra() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 11);
        return map;
    }
}

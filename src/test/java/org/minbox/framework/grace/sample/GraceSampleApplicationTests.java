package org.minbox.framework.grace.sample;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootTest
class GraceSampleApplicationTests {
    /**
     * 线程安全测试
     * 100个线程跑10秒，报告如下所示：
     * --------------------------------------------------------
     * Started at:  2021-12-28 17:52:57.455
     * Invocations:  37987
     * Success:  37989
     * Errors:  0
     * Thread Count:  100
     * Warm up:  0ms
     * Execution time:  10000ms
     * Throughput:  3794/s (Required: -1/s) - PASSED
     * Memory cost:  16byte
     * Min latency:  1.056123ms (Required: -1.0ms) - PASSED
     * Max latency:  388.305ms (Required: -1.0ms) - PASSED
     * Avg latency:  26.377914ms (Required: -1.0ms) - PASSED
     * --------------------------------------------------------
     */
    @Test
    @JunitPerfConfig(threads = 100, duration = 10000)
    void threadSafetyTest() {
        RestTemplate restTemplate = new RestTemplate();
        String randomString = new Random().nextInt(100) + "";
        restTemplate.getForEntity("http://127.0.0.1:8080?name=admin" + randomString + "&userId=" + randomString, String.class);
    }

}

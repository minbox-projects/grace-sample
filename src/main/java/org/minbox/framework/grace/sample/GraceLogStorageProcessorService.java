package org.minbox.framework.grace.sample;

import lombok.extern.slf4j.Slf4j;
import org.minbox.framework.grace.processor.GraceLogObject;
import org.minbox.framework.grace.processor.GraceLogStorageProcessor;
import org.springframework.stereotype.Service;

/**
 * @author 恒宇少年
 */
@Service
@Slf4j
public class GraceLogStorageProcessorService implements GraceLogStorageProcessor {
    @Override
    public void storage(GraceLogObject graceLogObject) {
        log.info("位置：{}，日志内容：{}", graceLogObject.getGeneratedLocation(), graceLogObject.getContent());
    }
}

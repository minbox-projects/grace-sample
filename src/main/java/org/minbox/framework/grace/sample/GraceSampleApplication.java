package org.minbox.framework.grace.sample;

import org.minbox.framework.grace.core.GraceRecorderAopBeanDefinitionRegistrar;
import org.minbox.framework.grace.expression.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.BeanFactoryResolver;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Configuration
@Import(GraceRecorderAopBeanDefinitionRegistrar.class)
public class GraceSampleApplication implements CommandLineRunner {
    @Autowired
    private BeanFactory beanFactory;

    public static void main(String[] args) {
        SpringApplication.run(GraceSampleApplication.class, args);
    }

    @Bean
    ExpressionFunctionFactory expressionFunctionFactory() {
        List<String> basePackages = AutoConfigurationPackages.get(beanFactory);
        Arrays.asList("org.minbox.framework.grace.sample","com.yuqiyu");
        return new ExpressionFunctionFactory(basePackages);
    }

    @Override
    public void run(String... args) throws Exception {
        ExpressionVariables variables = ExpressionVariables.initialize();
        variables.addVariable("userId", "11123");
        GraceRecordContext.pushExpressionVariables(variables);

        ExpressionVariables variables2 = GraceRecordContext.popExpressionVariables();
        GraceCachedExpressionEvaluator evaluator = new GraceCachedExpressionEvaluator();
        GraceEvaluationContext context = evaluator.createEvaluationContext(variables2);
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));

        Class clazz = GraceCachedExpressionEvaluator.class;
        AnnotatedElementKey elementKey = new AnnotatedElementKey(clazz, clazz);
        String result = null;
        //result = evaluator.parseExpression("你好啊 {#getUserName(#userId)}", elementKey, context);
        //     result = evaluator.parseExpression(String.class,"{@testService.getUserName(#userId)}", elementKey, context);
        //System.out.println(result);
        result = evaluator.parseExpression(String.class, "{#reverseString(#userId)}", elementKey, context);
        System.out.println(result);
        result = evaluator.parseExpression(String.class, "{#reverseString(#userId)}", elementKey, context);
        System.out.println(result);
    }
}

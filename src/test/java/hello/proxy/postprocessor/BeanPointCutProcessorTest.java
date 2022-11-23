package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BeanPointCutProcessorTest {

    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        //A 는 빈으로 등록된다.
        B beanA = (B) applicationContext.getBean("beanA");
        beanA.helloB();

        assertThatThrownBy(() -> applicationContext.getBean(A.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);

    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {
        @Bean("beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AtoBProcessor atoBProcessor() {
            return new AtoBProcessor();
        }

    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }

    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }

    }

    @Slf4j
    static class AtoBProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName ={}, bean ={}", beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }

}

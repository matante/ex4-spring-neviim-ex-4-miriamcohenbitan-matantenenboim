package hac.ex4spring;

import hac.ex4spring.listeners.SessionListenerCounter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /**
     * bean for sessions
     * @return returns listener bean
     */
    @Bean
    public ServletListenerRegistrationBean<SessionListenerCounter> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<SessionListenerCounter> listenerRegBean = new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new SessionListenerCounter());
        return listenerRegBean;
    }
}

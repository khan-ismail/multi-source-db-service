package zerotoismail.com.datasourcelearningserviceorg.security.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zerotoismail.com.datasourcelearningserviceorg.security.JwtProvider;

@Configuration
public class FilterConfig {

    private JwtProvider jwtProvider;

    public FilterConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public FilterRegistrationBean<TenantFilter> tenantFilter() {
        FilterRegistrationBean<TenantFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantFilter(jwtProvider));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}

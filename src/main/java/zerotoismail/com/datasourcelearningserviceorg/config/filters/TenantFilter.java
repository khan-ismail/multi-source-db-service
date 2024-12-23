package zerotoismail.com.datasourcelearningserviceorg.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import zerotoismail.com.datasourcelearningserviceorg.config.TenantContext;
import zerotoismail.com.datasourcelearningserviceorg.security.JwtProvider;

import java.io.IOException;

public class TenantFilter implements Filter {

    private final JwtProvider jwtProvider;

    public TenantFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");

        if (authorizationHeader != null) {
            try {
                String tenantId = jwtProvider.getTenantIdFromJwt(authorizationHeader);
                if (tenantId != null) {
                    TenantContext.setCurrentTenant(Long.valueOf(tenantId));
                }
            } catch (Exception e) {
                throw new IllegalStateException("Unauthorized access", e);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

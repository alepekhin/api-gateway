package alepekhin.apigateway.config;

import alepekhin.apigateway.common.Constants;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class MdcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString().replace("-", "");
        // try to take it from request
        if (request instanceof HttpServletRequest) {
            String rqId = ((HttpServletRequest) request).getHeader(Constants.X_REQUEST_ID);
            if (rqId != null) {
                requestId = rqId;
            }
        }
        MDC.put(Constants.MDC_RQID, requestId);
        chain.doFilter(request, response);
    }

}

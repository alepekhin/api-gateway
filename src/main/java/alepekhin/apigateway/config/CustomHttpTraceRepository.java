package alepekhin.apigateway.config;

import alepekhin.apigateway.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Репозитарий, содержащий логирование реквеста и респонса
 * см.
 * https://www.baeldung.com/spring-boot-actuator-http
 * https://stackoverflow.com/questions/33744875/spring-boot-how-to-log-all-requests-and-responses-with-exceptions-in-single-pl
 *
 */
@Slf4j
@Repository
public class CustomHttpTraceRepository implements HttpTraceRepository {

    AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

    @Override
    public List<HttpTrace> findAll() {
        return Collections.singletonList(lastTrace.get());
    }

    @Override
    public void add(HttpTrace trace) {
        if (!trace.getRequest().getUri().toString().contains("actuator")) {
            StringBuilder message = new StringBuilder()
                    .append("Endpoint request: ")
                    .append(" URL=[")
                    .append(trace.getRequest().getUri())
                    .append("], ")
                    .append("method=[")
                    .append(trace.getRequest().getMethod())
                    .append("], ")
                    .append("[mdc.rqid:")
                    .append(MDC.get(Constants.MDC_RQID))
                    .append("], ")
                    .append("Endpoint response: ")
                    .append("status=[")
                    .append(trace.getResponse().getStatus())
                    .append("] ");
            log.info(message.toString());
            lastTrace.set(trace);
        }
    }
}

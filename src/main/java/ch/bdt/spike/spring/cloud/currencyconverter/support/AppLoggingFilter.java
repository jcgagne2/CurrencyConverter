package ch.bdt.spike.spring.cloud.currencyconverter.support;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppLoggingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        log.info("--- SERVICE CIBLE : REQUÊTE ENTRANTE ---");
        log.info("URI: {}", httpRequest.getRequestURI());

        // Log les Headers
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = httpRequest.getHeader(name);
            log.info("  Header {}: {}", name, value);
        }

        chain.doFilter(request, response);
    }
    // Les méthodes init et destroy peuvent être laissées vides
}
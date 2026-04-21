package com.csf.api_pag.infrastructure.web.config;

import com.csf.api_pag.infrastructure.pagbank.PagBankSignatureValidator;
import com.csf.api_pag.infrastructure.web.interceptor.WebhookInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("prod-homo")
public class WebMvcConfig implements WebMvcConfigurer {

    private final PagBankSignatureValidator pagBankSignatureValidator;

    public WebMvcConfig(PagBankSignatureValidator pagBankSignatureValidator) {
        this.pagBankSignatureValidator = pagBankSignatureValidator;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebhookInterceptor(pagBankSignatureValidator))
                .addPathPatterns("/api/v1/webhook/pagbank/**");
    }

}

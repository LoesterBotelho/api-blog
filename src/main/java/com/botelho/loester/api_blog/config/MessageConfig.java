package com.botelho.loester.api_blog.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {

        var source = new ReloadableResourceBundleMessageSource();

        source.setBasename("classpath:messages");
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        source.setFallbackToSystemLocale(false);

        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {

        var resolver = new AcceptHeaderLocaleResolver();

        resolver.setDefaultLocale(Locale.of("pt", "BR"));

        resolver.setSupportedLocales(List.of(
                Locale.of("pt", "BR"),
                Locale.of("en", "US"),
                Locale.of("es", "ES"),
                Locale.of("fr", "FR"),
                Locale.of("de", "DE")
        ));

        return resolver;
    }
}
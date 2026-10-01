package com.api.blog_api.config;

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

        // Idioma padrão
        resolver.setDefaultLocale(Locale.of("pt", "BR"));

	    // pt-BR = Português do Brasil
	    // en-US = Inglês dos Estados Unidos
	    // es-ES = Espanhol da Espanha
	    // fr-FR = Francês da França
	    // de-DE = Alemão da Alemanha
        
        // Idiomas aceitos
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
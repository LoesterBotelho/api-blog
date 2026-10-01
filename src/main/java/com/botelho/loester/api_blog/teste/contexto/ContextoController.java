package com.botelho.loester.api_blog.teste.contexto;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contexto")
public class ContextoController {

    @GetMapping
    public ContextoResponseDto contexto(Locale locale) {
        // Normaliza o locale caso ele venha sem a região (ex: "es" vira "es-ES")
        Locale localeCompleto = normalizarLocale(locale);
        
        ZoneId zoneId = timezonePara(localeCompleto);
        LocalDateTime agora = LocalDateTime.now(zoneId);

        DateTimeFormatter formatoData = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(localeCompleto);

        NumberFormat numero = NumberFormat.getNumberInstance(localeCompleto);
        numero.setMinimumFractionDigits(2);
        numero.setMaximumFractionDigits(2);

        NumberFormat moeda = NumberFormat.getCurrencyInstance(localeCompleto);
        moeda.setCurrency(Currency.getInstance(moedaPara(localeCompleto)));

        return new ContextoResponseDto(
                localeCompleto.toLanguageTag(),
                zoneId.getId(),
                agora.format(formatoData),
                moeda.format(1250.90),
                numero.format(1250.90)
        );
    }

    private Locale normalizarLocale(Locale locale) {
        if (!locale.getCountry().isEmpty()) {
            return locale;
        }
        return switch (locale.getLanguage()) {
            case "en" -> Locale.of("en", "US");
            case "es" -> Locale.of("es", "ES");
            case "fr" -> Locale.of("fr", "FR");
            case "de" -> Locale.of("de", "DE");
            default -> Locale.of("pt", "BR");
        };
    }

    private String moedaPara(Locale locale) {
        return switch (locale.getCountry()) {
            case "US" -> "USD";
            case "ES", "FR", "DE" -> "EUR";
            default -> "BRL";
        };
    }

    private ZoneId timezonePara(Locale locale) {
        return switch (locale.getCountry()) {
            case "US" -> ZoneId.of("America/New_York");
            case "ES" -> ZoneId.of("Europe/Madrid");
            case "FR" -> ZoneId.of("Europe/Paris");
            case "DE" -> ZoneId.of("Europe/Berlin");
            default -> ZoneId.of("America/Sao_Paulo");
        };
    }
}
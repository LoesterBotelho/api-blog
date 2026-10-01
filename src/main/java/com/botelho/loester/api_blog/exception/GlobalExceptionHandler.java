package com.botelho.loester.api_blog.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDto> tratarRegistroNaoEncontrado(
            RegistroNaoEncontradoException ex,
            Locale locale) {

        String mensagem = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                locale
        );

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroResponseDto> tratarRegraDeNegocio(
            RegraDeNegocioException ex,
            Locale locale) {

        String mensagem = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                locale
        );

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDto> tratarErroValidacao(
            MethodArgumentNotValidException ex,
            Locale locale) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        erro -> erro.getField(),
                        LinkedHashMap::new,
                        Collectors.toList()
                ))
                .entrySet()
                .stream()
                .map(entry -> {

                    var erros = entry.getValue();

                    var erroNotBlank = erros.stream()
                            .filter(erro ->
                                    "NotBlank".equals(erro.getCode())
                            )
                            .findFirst();

                    var erro = erroNotBlank.orElse(erros.get(0));

                    return erro.getField() + ": " +
                            messageSource.getMessage(
                                    erro,
                                    locale
                            );
                })
                .collect(Collectors.joining("; "));

        if (mensagem.isEmpty()) {
            mensagem = messageSource.getMessage(
                    "error.validation",
                    null,
                    locale
            );
        }

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponseDto> tratarErroJsonInvalido(
            HttpMessageNotReadableException ex,
            Locale locale) {

        String mensagem = messageSource.getMessage(
                "error.json.invalid",
                null,
                locale
        );

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponseDto> tratarErroTipoParametro(
            MethodArgumentTypeMismatchException ex,
            Locale locale) {

        String mensagem = messageSource.getMessage(
                "error.parameter.invalid",
                new Object[]{
                        ex.getName(),
                        ex.getValue()
                },
                locale
        );

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResponseDto> tratarMetodoNaoSuportado(
            HttpRequestMethodNotSupportedException ex,
            Locale locale) {

        String mensagem = messageSource.getMessage(
                "error.method.not.supported",
                new Object[]{
                        ex.getMethod()
                },
                locale
        );

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponseDto> tratarViolacaoIntegridade(
            DataIntegrityViolationException ex,
            Locale locale) {

        String mensagem = messageSource.getMessage(
                "error.data.integrity",
                null,
                locale
        );

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDto> tratarErroInesperado(
            Exception ex,
            Locale locale) {

        String mensagem = messageSource.getMessage(
                "error.internal",
                null,
                locale
        );

        ErroResponseDto erro = new ErroResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                mensagem,
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }
}
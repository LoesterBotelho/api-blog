package com.botelho.loester.api_blog.exception;

public class RegraDeNegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String messageKey;

    private final Object[] args;

    public RegraDeNegocioException(
            String messageKey,
            Object... args) {

        super(messageKey);

        this.messageKey = messageKey;

        this.args = args;
    }

    public String getMessageKey() {

        return messageKey;
    }

    public Object[] getArgs() {

        return args;
    }
}

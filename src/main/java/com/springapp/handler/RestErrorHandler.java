package com.springapp.handler;

import com.springapp.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

/**
 * This class contains logic for managing validation errors.
 */

@ControllerAdvice
public class RestErrorHandler {

    private MessageSource messageSource;

    @Autowired
    public RestErrorHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorList processValidationError(final MethodArgumentNotValidException ex) {

        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error entityNotFound(final EntityNotFoundException ex){
        final Long entityId = ex.getId();
        return new Error("exercise", "Exercise " + entityId + " not found");
    }

    private ErrorList processFieldErrors(final List<FieldError> fieldErrors) {
        final ErrorList errorList = new ErrorList();

        for (final FieldError fieldError : fieldErrors) {
            final String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            errorList.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return errorList;
    }

    private String resolveLocalizedErrorMessage(final FieldError fieldError) {
        final Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}

package org.progress.crm.exceptions;

public class ValidationException extends CustomException {

    private final String message;

    public ValidationException(String param_error, String paramName) {
        this.message = param_error + " " + paramName;
    }

    public ValidationException(String param_error, String paramName, String message) {
        this.message = param_error + " " + paramName;
    }
    
    public ValidationException(String param_error, String paramName, String start, String end) {
        this.message = param_error + " " + paramName;
    }

    @Override
    public String getMessage() {
        return "Введены неправильные данные: " + this.message;
    }
}

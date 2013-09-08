package org.progress.crm.exceptions;

public class TryCountException extends CustomException {

    @Override
    public String getMessage() {
        return "Исчерпано количество попыток";
    }
}

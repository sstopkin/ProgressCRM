package org.progress.crm.exceptions;

public class TaskIsSolvedException extends CustomException {

    @Override
    public String getMessage() {
        return "Задание было выполнено ранее";
    }
}

package com.example.cinema.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 1. Аннотация @ResponseStatus (опционально, но рекомендуется)
@ResponseStatus(HttpStatus.CONFLICT) // Возвращает HTTP 409 Conflict
public class DuplicateDataException extends RuntimeException {

    // 2. Конструктор (обязательно)
    public DuplicateDataException(String message) {
        super(message); // Вызов конструктора суперкласса (RuntimeException)
    }

    // 3. (Опционально) Можно добавить дополнительные конструкторы, поля и методы
    //    для большей гибкости

    // Например, конструктор с возможностью указать причину исключения
    public DuplicateDataException(String message, Throwable cause) {
        super(message, cause);
    }

    // (Опционально) Поле для хранения конкретного типа дубликата
    private String duplicatedField;

    public DuplicateDataException(String message, String duplicatedField) {
        super(message);
        this.duplicatedField = duplicatedField;
    }
}

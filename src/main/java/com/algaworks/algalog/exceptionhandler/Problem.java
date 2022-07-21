package com.algaworks.algalog.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Problem {

    private Integer httpStatus;
    private LocalDateTime dateTime;
    private String title;
    private List<ErrorField> fieldList;

    @Getter
    @AllArgsConstructor
    public static class ErrorField {

        private String name;
        private String message;
    }

}

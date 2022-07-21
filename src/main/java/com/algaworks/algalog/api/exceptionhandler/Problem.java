package com.algaworks.algalog.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
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

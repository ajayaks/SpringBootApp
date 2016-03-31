package com.vz.ts.vt.lighting.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse extends SuccessResponse {
    private List<ValidationError> errors = new ArrayList<>();

}

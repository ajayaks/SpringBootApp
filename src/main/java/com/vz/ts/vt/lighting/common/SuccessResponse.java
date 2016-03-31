package com.vz.ts.vt.lighting.common;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class SuccessResponse {
    private String message;
    private int status;
}

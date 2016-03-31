package com.vz.ts.vt.lighting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Gateway {
	private int gatewayId;
	private String gatewayName;
	private Integer clientId;
	private Integer longitude;
	private Integer latitude;
}

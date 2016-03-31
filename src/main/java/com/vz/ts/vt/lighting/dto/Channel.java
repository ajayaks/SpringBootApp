package com.vz.ts.vt.lighting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Channel {
	private Integer clientId;
	private Integer gatewayId;
	private PowerParameter powerParameters;
	private String thingId;
	private StatusParameter statusParameters;
	private String timeStamp;

}

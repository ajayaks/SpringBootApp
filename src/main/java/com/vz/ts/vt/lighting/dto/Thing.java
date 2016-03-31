package com.vz.ts.vt.lighting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Thing {
	private Integer thingId;
	private String thingName;
	private String MACAddress;
	private Integer clientId;
	private String gatewayName;
	private Integer gatewayId;
	private String creationDateTime;
	private String address;
	private String longitude;
	private String latitude;
	private Long serial;
	
}

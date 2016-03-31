package com.vz.ts.vt.lighting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusParameter {
	
	private Integer abnormalLampCondition;
	private Integer ballast;
	private Integer communication;
	private Integer controllerFault;
	private Integer dayBurning;
	private Integer dimmingShort;
	private Integer eMFault;
	private Integer eventOverFlow;
	private Integer lamp;
	private Integer lampCyclic;
	private Integer lampStatus;
	private Integer photocellFault;
	private Integer photocellFeedback;
	private Integer photocellOscillatin;
	private Integer photocellStatus;
	private Integer poleFault;
	private Integer rTCStatus;
	private Integer relayWeld;
	private Integer voltageUnderOver;
}

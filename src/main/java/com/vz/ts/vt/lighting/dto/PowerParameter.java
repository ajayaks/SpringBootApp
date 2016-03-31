package com.vz.ts.vt.lighting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PowerParameter {

	private Double burnHours;

	private Double cumilitivekWH;

	private Double current;

	private Double dimming;

	private Double kiloWatt;

	private Double lampSteadyCurrent;

	private Double mode;

	private Double powerFactor;

	private Double temperature;

	private Double voltage;

}

package com.vz.ts.vt.lighting.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.datacontract.schemas._2004._07.RESTfulWCFLG.Channels;
import org.datacontract.schemas._2004._07.RESTfulWCFLG.DCU;
import org.datacontract.schemas._2004._07.RESTfulWCFLG.Mode;
import org.datacontract.schemas._2004._07.RESTfulWCFLG.SLC;
import org.datacontract.schemas._2004._07.RESTfulWCFLG.SwitchOnOffDim;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.tempuri.ILightingGaleServiceProxy;

import com.vz.ts.vt.lighting.dto.Actuate;
import com.vz.ts.vt.lighting.dto.Channel;
import com.vz.ts.vt.lighting.dto.Gateway;
import com.vz.ts.vt.lighting.dto.PowerParameter;
import com.vz.ts.vt.lighting.dto.StatusParameter;
import com.vz.ts.vt.lighting.dto.Thing;

@Component
public class LightingService {

	public Gateway getGatewayById(String clientId, int gatewayId) {
		List<Gateway> gatewaysList = getAllGateways(clientId);
		Gateway gateway = null;
		for (Gateway gateWay : gatewaysList) {
			if (gateWay.getGatewayId() == gatewayId) {
				gateway = gateWay;
			}
		}
		return gateway;
	}

	public List<Gateway> getAllGateways(String clientId) {
		List<Gateway> gatewaysList = null;
		Gateway gateway = null;
		if (!StringUtils.isEmpty(clientId)) {
			ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
			DCU[] dcuVal;
			try {
				dcuVal = proxy.getAllDCUs(clientId);
				for (DCU dcu : dcuVal) {
					gatewaysList = new ArrayList<Gateway>();
					gateway = Gateway.builder().clientId(dcu.getClientID()).gatewayId(dcu.getDCUId())
							.gatewayName(dcu.getDCUName()).latitude(null).longitude(null).build();
					gatewaysList.add(gateway);

				}
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return gatewaysList;
	}

	public List<Thing> getAllThings(String clientId) {
		List<Thing> things = null;
		if (!StringUtils.isEmpty(clientId)) {
			ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
			SLC[] dcuVal;
			List<SLC> slcs = null;
			try {
				dcuVal = proxy.getAllSLCs(clientId);
				slcs = Arrays.asList(dcuVal);
				things = new ArrayList<Thing>();
				for (SLC slc : slcs) {
					Thing thing = Thing.builder().thingId(slc.getSLCId()).address(slc.getAddress())
							.clientId(slc.getClientID()).creationDateTime(slc.getCreationDateTime())
							.gatewayName(slc.getGateway()).gatewayId(94).latitude(slc.getLatitude()).longitude(slc.getLongitude())
							.MACAddress(slc.getMACAddress()).serial(slc.getSerial()).thingName(slc.getSLCName())
							.gatewayName(slc.getGateway()).build();
					things.add(thing);
				}
				
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		return things;
	}

	public List<Thing> getThingsByGateway(String clientId, String dcuId) {
		List<Thing> things = null;
		if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(dcuId)) {
			ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
			SLC[] slcVal;
			List<SLC> slcs = null;

			try {
				slcVal = proxy.getSLCsByDCUId(clientId, dcuId);
				slcs = Arrays.asList(slcVal);
				things = new ArrayList<Thing>();
				for (SLC slc : slcs) {

					Thing thing = Thing.builder().thingId(slc.getSLCId()).address(slc.getAddress())
							.creationDateTime(slc.getCreationDateTime()).gatewayName(slc.getGateway())
							.latitude(slc.getLatitude()).longitude(slc.getLongitude()).MACAddress(slc.getMACAddress())
							.serial(slc.getSerial()).build();
					things.add(thing);
				}
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		return things;
	}

	public List<Thing> getThingLocationById(String clientId, String slcId) {
		List<Thing> things = null;
		String idMsg = "clientId = " + clientId;
		if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(slcId)) {
			ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
			SLC[] slcVal;
			List<SLC> slcs = null;

			try {
				slcVal = proxy.getSLCLocationDetailsById(clientId, slcId);
				slcs = Arrays.asList(slcVal);
				things = new ArrayList<Thing>();
				for (SLC slc : slcs) {

					Thing thing = Thing.builder().thingId(slc.getSLCId()).address(slc.getAddress())
							.creationDateTime(slc.getCreationDateTime()).gatewayName(slc.getGateway())
							.latitude(slc.getLatitude()).longitude(slc.getLongitude()).MACAddress(slc.getMACAddress())
							.serial(slc.getSerial()).build();
					things.add(thing);
				}
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		return things;
	}

	public List<Thing> getAllThingLocationDetails(String clientId) {
		List<Thing> things = null;
		if (!StringUtils.isEmpty(clientId)) {
			ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
			SLC[] slcVal;
			List<SLC> slcs = null;

			try {
				slcVal = proxy.getAllSLCLocationDetails(clientId);
				slcs = Arrays.asList(slcVal);
				things = new ArrayList<Thing>();
				for (SLC slc : slcs) {

					Thing thing = Thing.builder().thingId(slc.getClientID()).address(slc.getAddress())
							.creationDateTime(slc.getCreationDateTime()).gatewayName(slc.getGateway())
							.latitude(slc.getLatitude()).longitude(slc.getLongitude()).MACAddress(slc.getMACAddress())
							.serial(slc.getSerial()).build();
					things.add(thing);
				}
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		return things;
	}

	public List<Channel> getLatestReading(String clientId, String dcuId, String slcId) {
		List<Channel> channels = null;
		if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(dcuId) && !StringUtils.isEmpty(slcId)) {
			ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
			Channels[] channeVal;
			List<Channels> channelsResults = null;
			try {
				channeVal = proxy.getLatestReading(clientId, dcuId, slcId);
				channelsResults = Arrays.asList(channeVal);
				channels = new ArrayList<Channel>();
				for (Channels channelsResult : channelsResults) {
					PowerParameter powerParameter = null;
					
					StatusParameter statusParameter = null;
					
					if (channelsResult.getPowerParameters() != null) {
						powerParameter = PowerParameter.builder()
								.burnHours(channelsResult.getPowerParameters().getBurnHours())
								.cumilitivekWH(channelsResult.getPowerParameters().getCumilitivekWH())
								.current(channelsResult.getPowerParameters().getCurrent())
								.dimming(channelsResult.getPowerParameters().getDimming())
								.kiloWatt(channelsResult.getPowerParameters().getKiloWatt())
								.lampSteadyCurrent(channelsResult.getPowerParameters().getLampSteadyCurrent())
								.mode(channelsResult.getPowerParameters().getMode())
								.powerFactor(channelsResult.getPowerParameters().getPowerFactor())
								.voltage(channelsResult.getPowerParameters().getVoltage())
								.temperature(channelsResult.getPowerParameters()
										.getTemperature()).build();
					}
					if (channelsResult.getStatusParameters() != null) {
						statusParameter = StatusParameter.builder()
								.abnormalLampCondition(channelsResult.getStatusParameters().getAbnormalLampCondition())
								.ballast(channelsResult.getStatusParameters().getBallast())
								.communication(channelsResult.getStatusParameters().getCommunication())
								.controllerFault(channelsResult.getStatusParameters().getControllerFault())
								.dayBurning(channelsResult.getStatusParameters().getDayBurning())
								.dimmingShort(channelsResult.getStatusParameters().getDimmingShort())
								.eMFault(channelsResult.getStatusParameters().getEMFault())
								.eventOverFlow(channelsResult.getStatusParameters().getEventOverFlow())
								.lamp(channelsResult.getStatusParameters().getLamp())
								.lampCyclic(channelsResult.getStatusParameters().getLampCyclic())
								.lampStatus(channelsResult.getStatusParameters().getLampStatus())
								.photocellFault(channelsResult.getStatusParameters().getPhotocellFault())
								.photocellFeedback(channelsResult.getStatusParameters().getPhotocellFeedback())
								.photocellOscillatin(channelsResult.getStatusParameters().getPhotocellOscillatin())
								.photocellStatus(channelsResult.getStatusParameters().getPhotocellStatus())
								.poleFault(channelsResult.getStatusParameters().getPoleFault())
								.relayWeld(channelsResult.getStatusParameters().getRelayWeld())
								.rTCStatus(channelsResult.getStatusParameters().getRTCStatus())
								.voltageUnderOver(channelsResult.getStatusParameters().getVoltageUnderOver()).build();
					}
					Channel channel = Channel.builder().powerParameters(powerParameter)
							.statusParameters(statusParameter).clientId(channelsResult.getClientId())
							.gatewayId(channelsResult.getDCUId()).thingId(channelsResult.getSLCIds())
							.timeStamp(channelsResult.getTimeStamp()).build();
					channels.add(channel);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return channels;
	}

	public Integer actuate(Actuate actuate,String clientId, String gatewayId, String thingId) {
		ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
		Integer switchofResult = null;
		if ((!StringUtils.isEmpty(clientId)  && !StringUtils.isEmpty(gatewayId) && !StringUtils.isEmpty(thingId))
			&& ((actuate.getDimming() != null && actuate.getState() != null))) {
			SwitchOnOffDim SwitchOnOffDim = new SwitchOnOffDim();
			SwitchOnOffDim.setClientId(Integer.parseInt(clientId));
			SwitchOnOffDim.setDimming(actuate.getDimming());
			SwitchOnOffDim.setGatewayId(Integer.parseInt(gatewayId));
			SwitchOnOffDim.setSLCIds(thingId);
			SwitchOnOffDim.setState(actuate.getState());
			try {
				switchofResult = proxy.switchOnOffDim(SwitchOnOffDim);
			} catch (RemoteException ex) {
				// TODO Auto-generated catch block
				throw new RuntimeException(ex);
			}
		}
		return switchofResult;
	}
	

	public Integer changeMode(Actuate actuate,String clientId, String gatewayId, String thingId) {
		ILightingGaleServiceProxy proxy = new ILightingGaleServiceProxy();
		Integer modeResult = null;
		if ((!StringUtils.isEmpty(clientId)  && !StringUtils.isEmpty(gatewayId) && !StringUtils.isEmpty(thingId) && actuate.getMode() != null )) {
			Mode objMode = new Mode();
			objMode.setClientId(Integer.parseInt(clientId));
			objMode.setGatewayId(Integer.parseInt(gatewayId));
			objMode.setMode(actuate.getMode().intValue());
			objMode.setSLCIds(thingId);
			try {
				modeResult = proxy.changeMode(objMode);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		return modeResult;
	}
}

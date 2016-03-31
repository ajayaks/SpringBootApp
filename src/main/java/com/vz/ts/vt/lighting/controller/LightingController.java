package com.vz.ts.vt.lighting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vz.ts.vt.lighting.common.ErrorResponse;
import com.vz.ts.vt.lighting.dto.Actuate;
import com.vz.ts.vt.lighting.dto.Channel;
import com.vz.ts.vt.lighting.dto.Gateway;
import com.vz.ts.vt.lighting.dto.Thing;
import com.vz.ts.vt.lighting.service.LightingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/lighting-controller")
@RestController
@RequestMapping(value = "/lt/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class LightingController {

	@Autowired
	LightingService lightingService;

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/gateways", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllGateways(@PathVariable(value = "clientId") String clientId) throws Exception {
		String idMsg = "clientId = " + clientId;
		if (StringUtils.isEmpty(clientId)) {
			return new ResponseEntity<>("Invalid url, " + idMsg, HttpStatus.BAD_REQUEST);
		}
		if (clientId == null) {
			return new ResponseEntity<>("Gateway not found, " + idMsg, HttpStatus.NOT_FOUND);
		}
		List<Gateway> gatewaysList = lightingService.getAllGateways(clientId);
		return new ResponseEntity<>(gatewaysList, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/things", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAllThings(@PathVariable(value = "clientId") String clientId) throws Exception {
		List<Thing> things = lightingService.getAllThings(clientId);
		return new ResponseEntity<>(things, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/gateways/{gatewayId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getGatewayById(@PathVariable(value = "clientId") String clientId,
			@PathVariable(value = "gatewayId") int gatewayId) throws Exception {

		Gateway gateway = lightingService.getGatewayById(clientId, gatewayId);

		return new ResponseEntity<>(gateway, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/gateways/{gatewayId}/things", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getThingsByGateway(@PathVariable(value = "clientId") String clientId,
			@PathVariable(value = "gatewayId") String gatewayId) throws Exception {
		System.out.println(" data from test" + clientId);
		List<Thing> things = lightingService.getThingsByGateway(clientId, gatewayId);

		return new ResponseEntity<>(things, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/things/{thingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getThingDetails(@PathVariable(value = "clientId") String clientId,
			@PathVariable(value = "thingId") String thingId) throws Exception {

		List<Thing> things = lightingService.getThingLocationById(clientId, thingId);
		return new ResponseEntity<>(things, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/things/location", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAllThingLocationDetails(@PathVariable(value = "clientId") String clientId)
			throws Exception {

		List<Thing> things = lightingService.getAllThingLocationDetails(clientId);
		return new ResponseEntity<>(things, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/{gatewayId}/{thingId}/reading", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getLatestReading(@PathVariable(value = "clientId") String clientId,
			@PathVariable(value = "gatewayId") String gatewayId, @PathVariable(value = "thingId") String thingId)
			throws Exception {

		List<Channel> channels = lightingService.getLatestReading(clientId, gatewayId, thingId);
		return new ResponseEntity<>(channels, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/{gatewayId}/{thingId}/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> actuate(@PathVariable(value = "clientId") String clientId,
			@PathVariable(value = "gatewayId") String gatewayId, @PathVariable(value = "thingId") String thingId,
			@RequestBody Actuate actuate) throws Exception {
		Integer switchofResult = lightingService.actuate(actuate, clientId, gatewayId, thingId);
		return new ResponseEntity<>(switchofResult, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, response = Void.class, message = "Command Found"),
			@ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request"),
			@ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized"),
			@ApiResponse(code = 404, response = ErrorResponse.class, message = "Device Not Found"),
			@ApiResponse(code = 406, response = ErrorResponse.class, message = "Validation Errors"),
			@ApiResponse(code = 500, response = ErrorResponse.class, message = "Unexpected Error") })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/{gatewayId}/{thingId}/mode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> changeMode(@PathVariable(value = "clientId") String clientId,
			@PathVariable(value = "gatewayId") String gatewayId, @PathVariable(value = "thingId") String thingId,
			@RequestBody Actuate actuate) throws Exception {
		Integer modeResult = lightingService.changeMode(actuate, clientId, gatewayId, thingId);
		return new ResponseEntity<>(modeResult, HttpStatus.OK);
	}

}
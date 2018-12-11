package com.tado.gateway.api;

import java.util.List;

import com.tado.gateway.api.dto.OverlayDto;
import com.tado.gateway.api.dto.OverlayRequestDto;
import com.tado.gateway.api.dto.ZoneDto;
import com.tado.gateway.api.dto.ZoneStateDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/secure/homes")
public interface HomeGatewayRestService {

	String BOOST_ZONE_URL = "/secure/homes/{homeId}/zones/{zoneId}/boost";
	String GET_HOME_ZONES_URL = "/secure/homes/{homeId}/zones";
	String GET_ZONE_STATE_URL = "/secure/homes/{homeId}/zones/{zoneId}/state";
	
	@ApiOperation(
            value = GET_HOME_ZONES_URL,
            notes = "Get list of home zones",
            httpMethod = "GET",
            consumes = "application/json",
            produces = "application/json",
            response = List.class)
	List<ZoneDto> getHomeZones(final Long homeId);
	
	@ApiOperation(
            value = GET_ZONE_STATE_URL,
            notes = "Get the state of the given zone",
            httpMethod = "GET",
            consumes = "application/json",
            produces = "application/json",
            response = ZoneStateDto.class)
	ZoneStateDto getZoneState(final Long homeId, final Long zoneId);
	
	@ApiOperation(
            value = BOOST_ZONE_URL,
            notes = "Boost the temperature of the given zone",
            httpMethod = "PUT",
            consumes = "application/json",
            produces = "application/json",
            response = OverlayDto.class)
	OverlayDto boostZoneOverlay(final OverlayRequestDto overlayRequestDto, final Long homeId, final Long zoneId);
}

package com.tado.gateway.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tado.gateway.api.dto.OverlayDto;
import com.tado.gateway.api.dto.OverlayRequestDto;
import com.tado.gateway.api.dto.ZoneDto;
import com.tado.gateway.api.dto.ZoneStateDto;
import com.tado.gateway.service.HomeGatewayService;

@RestController
public class HomeGatewayRestServiceImpl implements HomeGatewayRestService {

	private final HomeGatewayService homeGatewayService;
	
	@Autowired
	public HomeGatewayRestServiceImpl(final HomeGatewayService homeGatewayService) {
		this.homeGatewayService = homeGatewayService;
	}
	
	@Override
	@GetMapping(
			path = GET_HOME_ZONES_URL,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<ZoneDto> getHomeZones(@PathVariable final Long homeId) {
		return homeGatewayService.getHomeZones(homeId);
	}

	@Override
	@GetMapping(
			path = GET_ZONE_STATE_URL,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ZoneStateDto getZoneState(@PathVariable final Long homeId, @PathVariable final Long zoneId) {
		return homeGatewayService.getZoneState(homeId, zoneId);
	}

	@Override
	@PutMapping(
			path = BOOST_ZONE_URL,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public OverlayDto boostZoneOverlay(
			@RequestBody final OverlayRequestDto overlayRequestDto, 
			@PathVariable final Long homeId, @PathVariable final Long zoneId) {
		return homeGatewayService.boostZoneOverlay(overlayRequestDto, homeId, zoneId);
	}
}

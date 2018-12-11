package com.tado.gateway.service;

import java.util.List;

import com.tado.gateway.api.dto.OverlayDto;
import com.tado.gateway.api.dto.OverlayRequestDto;
import com.tado.gateway.api.dto.ZoneDto;
import com.tado.gateway.api.dto.ZoneStateDto;

public interface HomeGatewayService {

	/**
	 * Get list of home zones
	 * 
	 * @param homeId
	 * @return List<ZoneDto>
	 */
	List<ZoneDto> getHomeZones(final Long homeId);

	/**
	 * Get the given zone state
	 * 
	 * @param zoneId
	 * @return ZoneStateDto
	 */
	ZoneStateDto getZoneState(final Long homeId, final Long zoneId);

	/**
	 * Boost the given zone state
	 * 
	 * @param zoneId
	 * @return OverlayDto
	 */
	OverlayDto boostZoneOverlay(final OverlayRequestDto overlayRequestDto, final Long homeId, final Long zoneId);
}

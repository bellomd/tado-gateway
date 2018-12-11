package com.tado.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.tado.gateway.api.ApiUri;
import com.tado.gateway.api.dto.OverlayDto;
import com.tado.gateway.api.dto.OverlayRequestDto;
import com.tado.gateway.api.dto.ZoneDto;
import com.tado.gateway.api.dto.ZoneStateDto;

@Service
public class HomeGatewayServiceImpl implements HomeGatewayService {
	
	private final OAuth2RestTemplate oauth2RestTemplate;
	private final ApiUri tadoApi;
	
	@Autowired
	public HomeGatewayServiceImpl(
			final OAuth2RestTemplate oauth2RestTemplate, final ApiUri tadoApi) {
		this.oauth2RestTemplate = oauth2RestTemplate;
		this.tadoApi = tadoApi;
	}

	@Override
	public List<ZoneDto> getHomeZones(final Long homeId) {
		
		if (homeId == null) {
			throw new IllegalArgumentException("Home id cannot be null!");
		}
		
		final String path = tadoApi.getHomeZonesInfoUri(homeId);
		final ResponseEntity<List<ZoneDto>> response = oauth2RestTemplate.exchange(
				path, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ZoneDto>>(){});
		
		return response.getBody();
	}

	@Override
	public ZoneStateDto getZoneState(final Long homeId, final Long zoneId) {
		
		if (homeId == null || zoneId == null) {
			throw new IllegalArgumentException("Home id and zone id cannot be null!");
		}
		
		final String path = tadoApi.getZoneStateUri(homeId, zoneId);
		return oauth2RestTemplate.getForObject(path, ZoneStateDto.class);
	}

	@Override
	public OverlayDto boostZoneOverlay(
			final OverlayRequestDto overlayRequestDto, final Long homeId, final Long zoneId) {
		
		if (homeId == null || zoneId == null) {
			throw new IllegalArgumentException("Home id and zone id cannot be null!");
		}
		
		final String path = tadoApi.getOverlayUri(homeId, zoneId);
		final HttpEntity<OverlayRequestDto> entity = new HttpEntity<>(overlayRequestDto);
		final ResponseEntity<OverlayDto> responseEntity = oauth2RestTemplate.exchange(
				path, HttpMethod.PUT, entity, OverlayDto.class);
		
		return responseEntity.getBody();
	}
}

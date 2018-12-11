package com.tado.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import com.tado.gateway.api.ApiUri;
import com.tado.gateway.api.dto.OverlayDto;
import com.tado.gateway.api.dto.OverlayRequestDto;
import com.tado.gateway.api.dto.SettingDto;
import com.tado.gateway.api.dto.TemperatureDto;
import com.tado.gateway.api.dto.TerminationDto;
import com.tado.gateway.api.dto.ZoneDto;
import com.tado.gateway.api.dto.ZoneStateDto;
import com.tado.gateway.service.HomeGatewayServiceImpl;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class HomeGatewayServiceTest {
	
	private static final String HEATING_TYPE_VALUE = "HEATING";
	private static final String COOLING_TYPE_VALUE = "HEATING";
	private static final String MANUAL_OVERLAY_TYPE = "MANUAL";
	private static final String TADO_MODE = "HOME";
	private static final String POWER_STATUS = "ON";
	private static final String MOCKED_URL = "https://my.tado.com/api/v2/mock/url/example";

	@Mock
	private ApiUri tadoApi;
	
	@Mock
	private OAuth2RestTemplate oauth2RestTemplate;
	
	@InjectMocks
	private HomeGatewayServiceImpl homeGatewayService;
	
	@Test
	public void successGetGivenHomeZonesWhenHomeIdIsNotNull() {
		
		final Long homeId = new Random().nextLong();
		final Long zoneId = new Random().nextLong();
		
		final ZoneDto zoneDto = new ZoneDto();
		zoneDto.setDateCreated(LocalDateTime.now());
		zoneDto.setId(String.valueOf(zoneId));
		zoneDto.setName(String.valueOf(zoneId));
		zoneDto.setType(HEATING_TYPE_VALUE);
		
		ResponseEntity<List<ZoneDto>> responseEntity = new ResponseEntity<List<ZoneDto>>(
				Collections.singletonList(zoneDto), HttpStatus.OK);
		
		when(tadoApi.getHomeZonesInfoUri(homeId)).thenReturn(MOCKED_URL);
		
		when(oauth2RestTemplate.exchange(anyString(), any(HttpMethod.class), isNull(),
				any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
		
		final List<ZoneDto> results = homeGatewayService.getHomeZones(homeId);
		
		verify(tadoApi).getHomeZonesInfoUri(homeId);
		verify(oauth2RestTemplate).exchange(anyString(), any(HttpMethod.class), isNull(),
				any(ParameterizedTypeReference.class));
		
		assertNotNull(results);
		assertFalse(results.isEmpty());
		assertNotNull(results.get(0));
		assertEquals(HEATING_TYPE_VALUE, results.get(0).getType());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void failureGetGivenHomeZonesWhenHomeIdIsNullThrowException() {
		
		final Long homeId = null;
		
		homeGatewayService.getHomeZones(homeId);
		
		verify(tadoApi, never()).getHomeZonesInfoUri(homeId);
		verify(oauth2RestTemplate, never()).exchange(anyString(), any(HttpMethod.class), isNull(),
				any(ParameterizedTypeReference.class));
	}
	
	@Test
	public void successGetZoneStateWhenHomeIdAndZoneIdAreNotNull() {
		
		final Long homeId = new Random().nextLong();
		final Long zoneId = new Random().nextLong();
		
		final SettingDto settingDto = new SettingDto();
		settingDto.setPower(POWER_STATUS);
		settingDto.setTemperature(new TemperatureDto());
		settingDto.setType(COOLING_TYPE_VALUE);
		
		final ZoneStateDto zoneStateDto = new ZoneStateDto();
		zoneStateDto.setOverlayType(MANUAL_OVERLAY_TYPE);
		zoneStateDto.setTadoMode(TADO_MODE);
		zoneStateDto.setSetting(settingDto);
		
		when(tadoApi.getZoneStateUri(homeId, zoneId)).thenReturn(MOCKED_URL);
		
		when(oauth2RestTemplate.getForObject(MOCKED_URL, ZoneStateDto.class))
		.thenReturn(zoneStateDto);
		
		final ZoneStateDto result = homeGatewayService.getZoneState(homeId, zoneId);
		
		verify(tadoApi, times(1)).getZoneStateUri(homeId, zoneId);
		verify(oauth2RestTemplate, times(1)).getForObject(MOCKED_URL, ZoneStateDto.class);
		
		assertNotNull(result);
		assertNotNull(result.getSetting());
		assertEquals(MANUAL_OVERLAY_TYPE, result.getOverlayType());
		assertEquals(TADO_MODE, result.getTadoMode());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void failureGetZoneStateWhenHomeIdOrZoneIdIsNullThrowException() {
		
		final Long homeId = new Random().nextLong();
		final Long zoneId = null;
		
		homeGatewayService.getZoneState(homeId, zoneId);
		
		verify(tadoApi, never()).getZoneStateUri(homeId, zoneId);
		verify(oauth2RestTemplate, never()).getForObject(MOCKED_URL, ZoneStateDto.class);
	}
	
	@Test
	public void successBoostGivenHomeZoneOverlayWhenHomeIdAndZoneIdAreNotNull() {
		
		final Long homeId = new Random().nextLong();
		final Long zoneId = new Random().nextLong();
		
		final OverlayRequestDto requestDto = new OverlayRequestDto();
		requestDto.setSetting(new SettingDto());
		requestDto.setTermination(new TerminationDto());
		
		final OverlayDto responseDto = new OverlayDto();
		responseDto.setSetting(new SettingDto());
		responseDto.setTermination(new TerminationDto());
		responseDto.setType(HEATING_TYPE_VALUE);
		
		final ResponseEntity<OverlayDto> responseEntity = 
				new ResponseEntity<OverlayDto>(responseDto, HttpStatus.OK);
		
		when(tadoApi.getOverlayUri(homeId, zoneId)).thenReturn(MOCKED_URL);
		when(oauth2RestTemplate.exchange(
				anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
		.thenReturn(responseEntity);
		
		final OverlayDto result = homeGatewayService.boostZoneOverlay(requestDto, homeId, zoneId);
		
		verify(tadoApi, times(1)).getOverlayUri(homeId, zoneId);
		verify(oauth2RestTemplate, times(1)).exchange(
				anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));
		
		assertNotNull(result);
		assertEquals(HEATING_TYPE_VALUE, result.getType());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void failureBoostGivenHomeZoneOverlayWhenHomeIdOrZoneIdIsNullThrowException() {
		
		final Long homeId = null;
		final Long zoneId = new Random().nextLong();
		
		final OverlayRequestDto requestDto = new OverlayRequestDto();
		requestDto.setSetting(new SettingDto());
		requestDto.setTermination(new TerminationDto());
		
		homeGatewayService.boostZoneOverlay(requestDto, homeId, zoneId);
		
		verify(tadoApi, never()).getOverlayUri(homeId, zoneId);
		verify(oauth2RestTemplate, never()).exchange(
				anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));
	}
}

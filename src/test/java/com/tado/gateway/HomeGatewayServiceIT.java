package com.tado.gateway;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tado.gateway.api.HomeGatewayRestService;
import com.tado.gateway.api.dto.ZoneDto;
import com.tado.gateway.api.dto.ZoneStateDto;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class HomeGatewayServiceIT extends AbstractRestIT{
	
	@Ignore
	@Test
	public void successGetHomeZoneWithGivenHomeId() {
		
		final String homeId = "169731";
		String requestUrl = baseUrl + HomeGatewayRestService.GET_HOME_ZONES_URL;
		requestUrl = requestUrl.replace("{homeId}", homeId);
		
		final List<ZoneDto> zones = restTemplate.getForObject(requestUrl, List.class);
		
		assertNotNull(zones);
		assertFalse(zones.isEmpty());
	}
	
	@Ignore
	@Test
	public void successGetZoneStateForGivenZoneId() {
		
		final String homeId = "169731";
		
		// First get the zones from the home.
		String requestUrl = baseUrl + HomeGatewayRestService.GET_HOME_ZONES_URL;
		requestUrl = requestUrl.replace("{homeId}", homeId);
		
		final List<ZoneDto> zones = restTemplate.getForObject(requestUrl, List.class);
		
		assertNotNull(zones);
		assertFalse(zones.isEmpty());
		
		// Then get the zone state
		requestUrl = baseUrl + HomeGatewayRestService.GET_ZONE_STATE_URL;
		requestUrl = requestUrl.replace("{homeId}", homeId);
		requestUrl = requestUrl.replace("{zoneId}", zones.get(0).getId());
		
		final ZoneStateDto zoneState = restTemplate.getForObject(requestUrl, ZoneStateDto.class);
		
		assertNotNull(zoneState);
		assertNotNull(zoneState.getOverlayType());
		assertNotNull(zoneState.getSetting());
		assertNotNull(zoneState.getTadoMode());
	}
}

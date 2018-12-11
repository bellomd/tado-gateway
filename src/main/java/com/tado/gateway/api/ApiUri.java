package com.tado.gateway.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.tado.gateway.service.TextInterpolator;

@Component
@ConfigurationProperties("tado.api")
public class ApiUri {

	private static final String HOME_PATH_VARIABLE_NAME = "{home_id}";
	private static final String ZONE_PATH_VARIABLE_NAME = "{zone_id}";
	
	private String userInfoUri;
	private String homeZonesInfoUri;
	private String zoneStateUri;
	private String overlayUri;

	public String getUserInfoUri() {
		return userInfoUri;
	}

	public void setUserInfoUri(String userInfoUri) {
		this.userInfoUri = userInfoUri;
	}

	public String getHomeZonesInfoUri() {
		return homeZonesInfoUri;
	}
	
	public String getHomeZonesInfoUri(final Long homeId) {
		final String path = TextInterpolator.interpolateUri(
				homeZonesInfoUri, 
				new String[] {HOME_PATH_VARIABLE_NAME},
				homeId);
		
		return path;
	}

	public void setHomeZonesInfoUri(String homeZonesInfoUri) {
		this.homeZonesInfoUri = homeZonesInfoUri;
	}

	public String getZoneStateUri() {
		return zoneStateUri;
	}
	
	public String getZoneStateUri(final Long homeId, final Long zoneId) {
		final String path = TextInterpolator.interpolateUri(zoneStateUri, 
				new String[] {HOME_PATH_VARIABLE_NAME, ZONE_PATH_VARIABLE_NAME},  
				homeId, zoneId);
		
		return path;
	}

	public void setZoneStateUri(String zoneStateUri) {
		this.zoneStateUri = zoneStateUri;
	}

	public String getOverlayUri() {
		return overlayUri;
	}
	
	public String getOverlayUri(final Long homeId, final Long zoneId) {
		final String path = TextInterpolator.interpolateUri(overlayUri, 
				new String[] {HOME_PATH_VARIABLE_NAME, ZONE_PATH_VARIABLE_NAME},  
				homeId, zoneId);
		
		return path;
	}

	public void setOverlayUri(String overlayUri) {
		this.overlayUri = overlayUri;
	}
}

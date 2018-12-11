package com.tado.gateway.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeZoneDto implements Serializable {

	private static final long serialVersionUID = -2853619270490131869L;

	private List<ZoneDto> zones;

	public List<ZoneDto> getZones() {
		return zones;
	}

	public void setZones(List<ZoneDto> zones) {
		this.zones = zones;
	}
}

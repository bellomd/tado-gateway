package com.tado.gateway.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SettingDto implements Serializable {

	private static final long serialVersionUID = -1802517678367062193L;
	private String type;
	private String power;
	private TemperatureDto temperature;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public TemperatureDto getTemperature() {
		return temperature;
	}

	public void setTemperature(TemperatureDto temperature) {
		this.temperature = temperature;
	}
}

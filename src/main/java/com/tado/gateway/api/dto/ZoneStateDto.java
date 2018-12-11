package com.tado.gateway.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZoneStateDto implements Serializable {

	private static final long serialVersionUID = 2909480231115894275L;

	private String tadoMode;
	private SettingDto setting;
	private String overlayType;

	public String getTadoMode() {
		return tadoMode;
	}

	public void setTadoMode(String tadoMode) {
		this.tadoMode = tadoMode;
	}

	public SettingDto getSetting() {
		return setting;
	}

	public void setSetting(SettingDto setting) {
		this.setting = setting;
	}

	public String getOverlayType() {
		return overlayType;
	}

	public void setOverlayType(String overlayType) {
		this.overlayType = overlayType;
	}
}

package com.tado.gateway.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OverlayRequestDto implements Serializable {

	private static final long serialVersionUID = 3444101016274618404L;

	private SettingDto setting;
	private TerminationDto termination;

	public SettingDto getSetting() {
		return setting;
	}

	public void setSetting(SettingDto setting) {
		this.setting = setting;
	}

	public TerminationDto getTermination() {
		return termination;
	}

	public void setTermination(TerminationDto termination) {
		this.termination = termination;
	}
}

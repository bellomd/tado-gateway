package com.tado.gateway.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OverlayDto implements Serializable {

	private static final long serialVersionUID = 4888068262432808403L;

	private String type;
	private SettingDto setting;
	private TerminationDto termination;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

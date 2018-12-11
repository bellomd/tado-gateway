package com.tado.gateway.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TerminationDto implements Serializable {

	private static final long serialVersionUID = -8283108759585454015L;

	private String type;
	private long durationInSeconds;
	private LocalDateTime expiry;
	private long remainingTimeInSeconds;
	private LocalDateTime projectedExpiry;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(long durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}

	public LocalDateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	public long getRemainingTimeInSeconds() {
		return remainingTimeInSeconds;
	}

	public void setRemainingTimeInSeconds(long remainingTimeInSeconds) {
		this.remainingTimeInSeconds = remainingTimeInSeconds;
	}

	public LocalDateTime getProjectedExpiry() {
		return projectedExpiry;
	}

	public void setProjectedExpiry(LocalDateTime projectedExpiry) {
		this.projectedExpiry = projectedExpiry;
	}
}

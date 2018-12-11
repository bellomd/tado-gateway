package com.tado.gateway.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureDto implements Serializable {

	private static final long serialVersionUID = 555565003814777652L;

	private int celsius;
	private float fahrenheit;

	public int getCelsius() {
		return celsius;
	}

	public void setCelsius(int celsius) {
		this.celsius = celsius;
	}

	public float getFahrenheit() {
		return fahrenheit;
	}

	public void setFahrenheit(float fahrenheit) {
		this.fahrenheit = fahrenheit;
	}
}

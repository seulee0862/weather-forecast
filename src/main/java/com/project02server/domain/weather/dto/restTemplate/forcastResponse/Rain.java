package com.project02server.domain.weather.dto.restTemplate.forcastResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
class Rain {

	@JsonProperty("3h")
	private double _3h;
}

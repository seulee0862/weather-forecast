package com.project02server.common.code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public enum TemperatureDressCode {
	HighTemperature("민소매, 반팔, 반바지, 린넨소재의 옷"), //28도 이상
	WarmTemperature("반팔, 얇은 셔츠, 반바지, 면바지"), //23~27
	MildTemperature("블라우스, 긴팔 티, 면바지, 슬랙스"), //20~22
	CoolTemperature("얇은 가디건, 니트, 맨투맨, 후드, 긴바지"), //17~19
	ChillyTemperature("자켓 가디건, 청자켓, 니트, 스타킹, 청바지"), //12~16
	ColdTemperature("트랜치코트, 야상, 점퍼, 스타킹, 기모바지"), //9~11
	VeryColdTemperature("울코트, 히트텍, 가죽옷, 기모"), //5~8
	ExtremeColdTemperature("패딩, 두꺼운코트, 누빔옷, 장갑, 목도리"); //4도 이하

	private final String dressCode;

	TemperatureDressCode(String dressCode) {
		this.dressCode = dressCode;
	}

	public String getDressCode() {
		return dressCode;
	}

	public static TemperatureDressCode getDressCode(double temp) {
		List<Pair<Double, TemperatureDressCode>> temperatureRanges = Arrays.asList(
			Pair.of(4.0, TemperatureDressCode.ExtremeColdTemperature),
			Pair.of(9.0, TemperatureDressCode.VeryColdTemperature),
			Pair.of(12.0, TemperatureDressCode.ColdTemperature),
			Pair.of(17.0, TemperatureDressCode.ChillyTemperature),
			Pair.of(20.0, TemperatureDressCode.CoolTemperature),
			Pair.of(23.0, TemperatureDressCode.MildTemperature),
			Pair.of(28.0, TemperatureDressCode.WarmTemperature)
		);

		return temperatureRanges.stream()
			.filter(range -> temp < range.getLeft())
			.min(Comparator.comparing(Pair::getLeft))
			.map(Pair::getRight)
			.orElse(TemperatureDressCode.ExtremeColdTemperature);
	}
}

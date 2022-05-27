package cn.hhnail.backend;

import cn.hhnail.backend.enums.Languages;

import java.util.Arrays;

public class StreamTests {

	public static void main(String[] args) {
		String code = "gageag";
		// String code = "zh";
		// String code = null;
		boolean b = code != null && !Arrays.stream(Languages.values()).anyMatch(x -> x.getCode().equals(code));
		System.out.println(code != null);
		System.out.println(!Arrays.stream(Languages.values()).anyMatch(x -> x.getCode().equals(code)));
		System.out.println(Arrays.stream(Languages.values()).anyMatch(x -> x.getCode().equals(code)));
		System.out.println(b);
	}

}

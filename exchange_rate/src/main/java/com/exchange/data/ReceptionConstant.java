package com.exchange.data;

import lombok.Getter;

/**
 * 수취국가 명세
 */
public class ReceptionConstant {

	@Getter
	public enum REC_CODE {
		KROREA("KRW", "한국"),
		JAPAN("JPY", "일본"),
		PILIPINE("PHP", "필리핀"),
		USA("USD", "미국")
		;


		private String code;
		private String nation;

		REC_CODE(String _code, String _nation){
			code = _code;
			nation = _nation;
		}
	}

}

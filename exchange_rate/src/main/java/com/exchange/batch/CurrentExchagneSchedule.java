package com.exchange.batch;

import com.exchange.config.ExternalExchangeProperty;
import com.exchange.data.ReceptionConstant;
import com.exchange.data.dto.CurrentDto;
import com.exchange.data.entity.ExchangeRate;
import com.exchange.repository.ExchageRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CurrentExchagneSchedule {
	private static final String CRON_BEFORE_0AM = "0 0 15 * * *";
	private final String FORMAT_JSON_QUERY_PARAM = "&format=1";
	private final ExternalExchangeProperty EX;

	private final ExchageRateRepository exchageRateRepository;

	@Scheduled(fixedDelay = 2000, cron = CRON_BEFORE_0AM)
	public void stachCurrentExchangeRate(){
		List<String> fromNations = Arrays.asList(ReceptionConstant.REC_CODE.KROREA.getCode(), ReceptionConstant.REC_CODE.PILIPINE.getCode(), ReceptionConstant.REC_CODE.JAPAN.getCode());
		String defaultToNation = ReceptionConstant.REC_CODE.USA.getCode();

		final String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		for(String item : fromNations){
			WebClient webClient = WebClient.create();
			CurrentDto currentDto = webClient
					.mutate()
					.baseUrl("http://" + EX.getApiendpoint())
					.build()
					.get()
					.uri("/historical?access_key=" + EX.getAccesskey()+"&date="+now+"&currencies="+item+FORMAT_JSON_QUERY_PARAM)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToFlux(CurrentDto.class)
					.toStream()
					.collect(Collectors.toList())
					.get(0);
			ExchangeRate exchangeRate = ExchangeRate.createExchangeRate(item, defaultToNation, currentDto.getQuotes().get(item + defaultToNation));
			exchageRateRepository.save(exchangeRate);
		}

	}
}

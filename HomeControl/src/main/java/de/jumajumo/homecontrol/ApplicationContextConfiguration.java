package de.jumajumo.homecontrol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import de.jumajumo.core.service.weather.WeatherService;
import de.jumajumo.core.service.weather.WeatherServiceOpenWeatherMap;

@Configuration
@ComponentScan("de.jumajumo.homecontrol")
@EnableScheduling
@EnableAsync
@EnableWebMvc
@Import({ WebSocketConfig.class })
public class ApplicationContextConfiguration extends WebMvcConfigurerAdapter
{
	public ApplicationContextConfiguration()
	{
		super();
	}

	@Bean
	public WeatherService getWeatherService()
	{
		return new WeatherServiceOpenWeatherMap();
	}

}

package de.jumajumo.homecontrol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import de.jumajumo.core.service.filestore.FileStorageService;
import de.jumajumo.core.service.weather.WeatherService;
import de.jumajumo.core.service.weather.WeatherServiceOpenWeatherMap;
import de.jumajumo.homecontrol.service.filestore.FileStorageMongoDBServiceImpl;

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

	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver()
	{
		final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

		commonsMultipartResolver.setMaxUploadSize(20971520);
		commonsMultipartResolver.setMaxInMemorySize(1048576);

		return commonsMultipartResolver;
	}

	@Bean
	public FileStorageService fileStorageService()
	{
		final FileStorageMongoDBServiceImpl storageService = new FileStorageMongoDBServiceImpl();

		storageService.setHostName("127.0.0.1");
		storageService.setDbName("homecontrol");

		return storageService;
	}

}

package de.jumajumo.homecontrol.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import de.jumajumo.homecontrol.service.ECameraInformation;
import de.jumajumo.homecontrol.service.FtpClientServiceImpl;
import de.jumajumo.homecontrol.service.ImageStoreClientService;

@Configuration
@ComponentScan("de.jumajumo.homecontrol")
@EnableScheduling
@EnableAsync
@EnableWebMvc
public class ApplicationContextConfiguration extends WebMvcConfigurerAdapter
{
	public ApplicationContextConfiguration()
	{
		super();
	}

	@Bean
	public ImageStoreClientService getDoorCameraImageStore()
	{
		return new FtpClientServiceImpl(ECameraInformation.DOOR);
	}

	@Bean
	public ImageStoreClientService getBackCameraImageStore()
	{
		return new FtpClientServiceImpl(ECameraInformation.BACK);
	}
}

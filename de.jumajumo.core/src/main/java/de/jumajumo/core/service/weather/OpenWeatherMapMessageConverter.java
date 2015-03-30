package de.jumajumo.core.service.weather;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import de.jumajumo.core.type.CurrentWeather;

public class OpenWeatherMapMessageConverter implements
		HttpMessageConverter<CurrentWeather>
{

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType)
	{
		return true;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType)
	{
		return false;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes()
	{
		return Arrays.asList(MediaType.APPLICATION_JSON);
	}

	@Override
	public CurrentWeather read(Class<? extends CurrentWeather> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException
	{
		final CurrentWeather currentWeather = new CurrentWeather();

		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(inputMessage.getBody());

		while (parser.nextToken() != JsonToken.END_ARRAY)
		{
			String token = parser.getCurrentName();

			if ("sunrise".equals(token))
			{
				parser.nextToken();

				long sunrise = parser.getLongValue();
				currentWeather.setSunrise(new Date(sunrise * 1000));
			}
			if ("sunset".equals(token))
			{
				parser.nextToken();

				long sunset = parser.getLongValue();
				currentWeather.setSunset(new Date(sunset * 1000));
			}
		}
		parser.close();
		return currentWeather;
	}

	@Override
	public void write(CurrentWeather t, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException
	{
		throw new IllegalAccessError("write method not supported");
	}

}

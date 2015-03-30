package de.jumajumo.meetingpoint;

import javax.annotation.PostConstruct;

import org.socialsignin.springsocial.security.connect.SpringSocialSecurityConnectionFactory;
import org.socialsignin.springsocial.security.signup.SpringSocialSecurityConnectionSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableSocial
@PropertySource("classpath:/environment.properties")
public class SocialConfig implements SocialConfigurer
{

	// Handle to users connection repository - allows us to set connection sign
	// up in post construct
	private InMemoryUsersConnectionRepository usersConnectionRepository;

	@Autowired(required = false)
	private SpringSocialSecurityConnectionSignUp springSocialSecurityConnnectionSignUp;

	// @Autowired
	// private DataSource dataSource;

	// @Bean
	// public DataSource dataSource()
	// {
	// EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	// return builder.setType(EmbeddedDatabaseType.H2).build();
	// }

	// @Bean
	// public DataSourceTransactionManager transactionManager(DataSource
	// dataSource)
	// {
	// return new DataSourceTransactionManager(dataSource);
	// }

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig,
			Environment env)
	{
		// cfConfig.addConnectionFactory(new TwitterConnectionFactory(env
		// .getProperty("twitter.consumerKey"), env
		// .getProperty("twitter.consumerSecret")));
		cfConfig.addConnectionFactory(new FacebookConnectionFactory(env
				.getProperty("facebook.clientId"), env
				.getProperty("facebook.clientSecret")));
		cfConfig.addConnectionFactory(new GoogleConnectionFactory(env
				.getProperty("google.appId"), env
				.getProperty("google.appSecret")));
		cfConfig.addConnectionFactory(new SpringSocialSecurityConnectionFactory());

	}

	/**
	 * This is only needed because the official spring-social-security from
	 * SpringSocial is on the classpath
	 * 
	 * @return
	 */
	@Override
	public UserIdSource getUserIdSource()
	{
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			ConnectionFactoryLocator connectionFactoryLocator)
	{
		// usersConnectionRepository = new JdbcUsersConnectionRepository(
		// this.dataSource, connectionFactoryLocator,
		// Encryptors.noOpText());

		usersConnectionRepository = new InMemoryUsersConnectionRepository(
				connectionFactoryLocator);
		return usersConnectionRepository;

	}

	@PostConstruct
	// Registers a mechanism for implicit sign up if user id available from
	// provider
	// Remove if explicit user name selection is required
	public void registerConnectionSignUp()
	{
		usersConnectionRepository
				.setConnectionSignUp(springSocialSecurityConnnectionSignUp);
	}

}

package org.socialsignin.provider;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.web.ConnectInterceptor;

import de.jumajumo.meetingpoint.ConnectInterceptorList;

public abstract class AbstractProviderConfig<S>
{

	@Autowired
	@Qualifier("connectInterceptorList")
	private ConnectInterceptorList connectInterceptorList;

	protected abstract ConnectInterceptor<S> getConnectInterceptor();

	@PostConstruct
	public void register()
	{
		connectInterceptorList.add(getConnectInterceptor());
	}
}

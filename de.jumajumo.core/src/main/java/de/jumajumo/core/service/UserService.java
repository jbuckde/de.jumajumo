package de.jumajumo.core.service;

import de.jumajumo.core.persistence.UserProfile;

public interface UserService
{
	boolean isAuthenticated();

	UserProfile getUserProfile();

	UserProfile getByProviderUserId(final String providerUserId);

}

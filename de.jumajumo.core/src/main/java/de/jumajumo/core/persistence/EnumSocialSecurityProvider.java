package de.jumajumo.core.persistence;

public enum EnumSocialSecurityProvider
{
	GOOGLE(1), FACEBOOK(2);

	private int key;

	private EnumSocialSecurityProvider(final int key)
	{
		this.key = key;
	}

	public int getKey()
	{
		return this.key;
	}
}

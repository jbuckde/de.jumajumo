package de.jumajumo.core.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "USERPROFILE")
@NamedQueries({
		@NamedQuery(name = "UserProfile.findByLoginName", query = "select u from UserProfile u  where u.loginName = :loginName"),
		@NamedQuery(name = "UserProfile.findByProviderUserId", query = "select u from UserProfile u  where u.providerUserId = :providerUserId") })
public class UserProfile extends AbstractEntityObject implements EntityObject
{
	@Column(name = "LOGIN_NAME", length = 255)
	private String loginName;

	@Column(name = "USER_NAME", length = 255)
	private String userName;

	@Column(name = "DISPLAY_NAME", length = 255)
	private String displayName;

	@Column(name = "IMAGE_URL", length = 1024)
	private String imageUrl;

	@Column(name = "PROFILE_URL", length = 1024)
	private String profileUrl;

	@Column(name = "PROVIDER", length = 255)
	private EnumSocialSecurityProvider provider;

	@Column(name = "PROVIDER_USER_ID", length = 1024)
	private String providerUserId;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result
				+ ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result
				+ ((getProfileUrl() == null) ? 0 : getProfileUrl().hashCode());
		result = prime * result
				+ ((getProvider() == null) ? 0 : getProvider().hashCode());
		result = prime
				* result
				+ ((getProviderUserId() == null) ? 0 : getProviderUserId()
						.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (displayName == null)
		{
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (imageUrl == null)
		{
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (loginName == null)
		{
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (getProfileUrl() == null)
		{
			if (other.getProfileUrl() != null)
				return false;
		} else if (!getProfileUrl().equals(other.getProfileUrl()))
			return false;
		if (getProvider() != other.getProvider())
			return false;
		if (getProviderUserId() == null)
		{
			if (other.getProviderUserId() != null)
				return false;
		} else if (!getProviderUserId().equals(other.getProviderUserId()))
			return false;
		if (userName == null)
		{
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getDisplayName()
	{
		if (null != this.displayName && !StringUtils.isEmpty(this.displayName))
		{
			return this.displayName;
		}

		return this.userName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getProfileUrl()
	{
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl)
	{
		this.profileUrl = profileUrl;
	}

	public EnumSocialSecurityProvider getProvider()
	{
		return provider;
	}

	public void setProvider(EnumSocialSecurityProvider provider)
	{
		this.provider = provider;
	}

	public String getProviderUserId()
	{
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId)
	{
		this.providerUserId = providerUserId;
	}
}

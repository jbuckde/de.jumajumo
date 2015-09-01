package de.jumajumo.core.service.twitter;

import twitter4j.Status;
import twitter4j.StatusUpdate;

public interface TwitterService
{
	Status sendUpdate(final StatusUpdate statusUpdate);
}

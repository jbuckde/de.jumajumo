package de.jumajumo.homecontrol.service.sunset;

import java.util.Date;

public interface SunsetService
{

	boolean isItDarkAt(final Date timeToCheck);

}

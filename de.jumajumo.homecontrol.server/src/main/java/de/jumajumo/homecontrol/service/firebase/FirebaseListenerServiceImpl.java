package de.jumajumo.homecontrol.service.firebase;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.jumajumo.homecontrol.configuration.server.trigger.TriggerByFirebaseListener;
import de.jumajumo.homecontrol.service.TriggerService;

@Service
public class FirebaseListenerServiceImpl implements FirebaseListenerService
{
	private static final String NOACTION = "-nothing-";

	@Autowired
	private TriggerService triggerService;

	public FirebaseListenerServiceImpl()
	{
		super();

		this.setupListener();
	}

	private void setupListener()
	{
		final DatabaseReference ref;
		final ValueEventListener valueEventListener;
		InputStream serviceAccount = this.getClass().getResourceAsStream(
				"jumohome-firebase-adminsdk-7zr2h-0a7ffbc247.json");

		try
		{
			// Initialize the app with a service account, granting admin
			// privileges
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(
							GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://jumohome.firebaseIO.com").build();
			FirebaseApp.initializeApp(options);

			// As an admin, the app has access to read and write all data,
			// regardless of Security Rules
			ref = FirebaseDatabase.getInstance()
					.getReference("dispatcher/trigger/action");
			valueEventListener = new ValueEventListener()
			{
				@Override
				public void onDataChange(DataSnapshot dataSnapshot)
				{
					Object action = dataSnapshot.getValue();

					if (!NOACTION.equals(action))
					{
						executeAction((String) action);
						ref.setValueAsync(NOACTION);
					}
				}

				@Override
				public void onCancelled(DatabaseError error)
				{
					// TODO Auto-generated method stub

				}
			};

			ref.addValueEventListener(valueEventListener);

		} catch (IOException e)
		{
			throw new IllegalAccessError("google credentials not retrievable");
		}

	}

	private void executeAction(final String action)
	{
		List<TriggerByFirebaseListener> triggers = this.triggerService
				.findTriggerByFirebaseAction(action);

		for (TriggerByFirebaseListener triggerByFirebaseListener : triggers)
		{
			triggerService.executeTrigger(triggerByFirebaseListener);
		}
	}
}

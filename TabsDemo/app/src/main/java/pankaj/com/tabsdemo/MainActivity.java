package pankaj.com.tabsdemo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */

    private TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost = getTabHost();

        // Tab for Call
        TabHost.TabSpec callspec = tabHost.newTabSpec("Call");
        // setting Title and Icon for the Tab
        callspec.setIndicator("Call", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, CallActivity.class);
        callspec.setContent(photosIntent);

        // Tab for Logs
        TabHost.TabSpec  logsspec = tabHost.newTabSpec("Logs");
        logsspec.setIndicator("Logs", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, LogsActivity.class);
        logsspec.setContent(songsIntent);

        // Tab for Contacts
        TabHost.TabSpec  contactsspec = tabHost.newTabSpec("Contacts");
        contactsspec.setIndicator("Contacts", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, ContactsActivity.class);
        contactsspec.setContent(videosIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(callspec); // Adding call tab
        tabHost.addTab(logsspec); // Adding logs tab
        tabHost.addTab(contactsspec); // Adding contactS tab
    }
}

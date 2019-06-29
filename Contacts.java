package hk.ust.cse.comp107x.chatclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contacts extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar toolbar;
    String[] names;

    ListView friendView;

    ContactArrayAdapter mArrayAdapter;

    // This class stores all the information about your friend
    public class FriendInfo {
        int id;
        String name;
        String statusMsg;
    }

    List<FriendInfo> friendInfoList = null;

    String friendJsonString = "[" +
            "{" +
            "\"id\": 1," +
            "\"name\": \"John\"," +
            "\"statusMsg\": \"Imagine all the people ...\"" +
            "}," +
            "{" +
            "\"id\": 2," +
            "\"name\": \"Paul\"," +
            "\"statusMsg\": \"Let it be ...\"" +
            "}," +
            "{" +
            "\"id\": 3," +
            "\"name\": \"George\"," +
            "\"statusMsg\": \"Wait mister postman ...\"" +
            "}," +
            "{" +
            "\"id\": 4," +
            "\"name\": \"Ringo\"," +
            "\"statusMsg\": \"Yellow submarine ...\"" +
            "}" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // convert the Json string into a list of objects
        processFriendInfo(friendJsonString);

        mArrayAdapter = new ContactArrayAdapter(this, friendInfoList);

        // If you are using a ListView widget, then your activity should implement
        // the onItemClickListener. Then you should set the OnItemClickListener for
        // teh ListView.
        friendView = (ListView) findViewById(R.id.friendListView);
        friendView.setAdapter(mArrayAdapter);
        friendView.setOnItemClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Should set up to add new contacts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Intent mIntent = new Intent(this,ChatClient.class);
        TextView friendName = (TextView) view.findViewById(R.id.friendName);
        mIntent.putExtra(getString(R.string.friend), friendName.getText().toString());
        startActivity(mIntent);

    }

    // This class processes the Json string and converts it into a list of FriendInfo objects
    // We make use of the Gson library to do this automatically
    private void processFriendInfo(String infoString) {

        // Create a new Gson object
        // TODO Create a Gson Object
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();

        // Use the Gson library to automatically process the string and convert it into
        // the list of FriendInfo objects. The use of the library saves you the need for
        // writing your own code to process the Json string

        // TODO convert the string to a list of FriendInfo objects using Gson
        friendInfoList  = new ArrayList<FriendInfo>();
        friendInfoList= Arrays.asList(gson.fromJson(infoString,FriendInfo[].class));
    }
}



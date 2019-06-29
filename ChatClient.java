package hk.ust.cse.comp107x.chatclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class ChatClient extends AppCompatActivity implements View.OnClickListener {

    ImageButton sendButton;
    EditText messageText;
    RecyclerView messageList;
    // ArrayAdapter <String> mAdapter = null;
    // ArrayList<String> messages = null;
    RecyclerView.Adapter mAdapter = null;
    ArrayList<Message> messages = null;
    int in_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_client);

        sendButton = (ImageButton) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);

        messageText = (EditText) findViewById(R.id.messageText);

        // Create a new ArrayList of Message objects
        messages = new ArrayList<Message>();

        // Create a new custom ArrayAdapter. This custom Adapter is
        // implemented by us, and illustrates how an ArrayAdapter is
        // constructed given the data (from the Message objects)
        mAdapter = new MyAdapter(this, messages);

        // TODO RECYCLER VIEW

        // get the friend's name from the Intent
        Intent in = getIntent();
        String friendName = in.getStringExtra(getString(R.string.friend));

        // Set the toolbar title to friend's name. This is a little quirk
        // that once you set the toolbar to be an ActionBar, you have to
        // use this approach to set the title
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name) + ": " + friendName);

        messageList = (RecyclerView) findViewById(R.id.messageList);
        messageList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messageList.setLayoutManager(llm);
        messageList.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sendButton:

                String messString = messageText.getText().toString();

                // If the message is not empty string
                if (!messString.equals("")) {

                    // Create a new Message object and initialize it with the information
                    Message message = new Message("", messString, true, new Date());

                    // Add the Message object to the ArrayList
                    messages.add(message);

                    // Notify the adapter that the data has changed due to the addition
                    // of a new message object. This triggers an update of the ListView
                    mAdapter.notifyDataSetChanged();
                    sendMessage();
                    message = null;
                    messageText.setText("");
                }

                break;

            default:
                break;
        }
    }

    public void sendMessage() {

        String[] incoming = {"Hey, How's it going?",
                "Super! Let's do lunch tomorrow",
                "How about Mexican?",
                "Great, I found this new place around the corner",
                "Ok, see you at 12 then!"};

        if (in_index < incoming.length) {
            Message message = new Message("John", incoming[in_index], false,  new Date());
            messages.add(message);
            in_index++;
        }
        messageList.scrollToPosition(messages.size()-1);
        mAdapter.notifyDataSetChanged();

    }
}


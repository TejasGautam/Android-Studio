package hk.ust.cse.comp107x.greetfriend;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity  {
String [] names;

private static  final String TAG  = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       names = getResources().getStringArray(R.array.friends);
       setListAdapter((ListAdapter) new ArrayAdapter<String>(this,R.layout.friend_item,names));
        Log.i(TAG,"onCreate()");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStrart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent in = new Intent(this,ShowMessage.class) ;
        in.putExtra("message","Good Day "+ names[(int) id] +"!");
        startActivity(in);

    }


    }

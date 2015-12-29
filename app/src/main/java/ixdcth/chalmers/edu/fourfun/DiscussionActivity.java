package ixdcth.chalmers.edu.fourfun;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet20SendQuestion;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet25EndDiscussion;

public class DiscussionActivity extends Activity {

    ListView lv;
    TextView questionTv;
    Intent intent;
    private Client client;
    private Packet25EndDiscussion p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        lv=(ListView) findViewById(R.id.lView);
        questionTv=(TextView) findViewById(R.id.questionTV);
        intent = getIntent();
        showQuestion();
        showAnswers();
        client=Client.getInstance();
    }

    private void showAnswers() {
        ArrayList<String> answers = intent.getStringArrayListExtra("answers");
        for(String s : answers){
            Log.d("DISCACT: ", s);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, answers);
        lv.setAdapter(adapter);
    }

    private void showQuestion() {
        questionTv.setText(intent.getStringExtra("question"));
    }

    public void onClickDone(View v){
        Client client=Client.getInstance();
        new DoneDiscAsync().execute();
        Intent intent = new Intent(this, WaitingActivity.class);
        startActivity(intent);
    }

    private class DoneDiscAsync extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            p=new Packet25EndDiscussion();
            client.sendData(p.getData());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}

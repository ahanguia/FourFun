package ixdcth.chalmers.edu.fourfun;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet13CreateRoom;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet20SendQuestion;

public class QuestionActivity extends AppCompatActivity {
    private EditText ET;
    private Packet20SendQuestion p;
    private Client client;
    private String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ET = (EditText) findViewById(R.id.question_ET);
        client=Client.getInstance();
    }
    public void sendQuestion(View v){
        if(ET.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Perhaps i have to give the question to somebody else ? Please WRITE IT !!!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        question =ET.getText().toString();
        new SendQuestionAsync().execute();
        Intent intent = new Intent(this, WaitingActivity.class);
        startActivity(intent);
    }
    private class SendQuestionAsync extends AsyncTask<Void, Integer, Void> {

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

            p=new Packet20SendQuestion(question);
            client.sendData(p.getData());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}

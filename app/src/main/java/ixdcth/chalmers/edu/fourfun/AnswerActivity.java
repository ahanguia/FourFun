package ixdcth.chalmers.edu.fourfun;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet20SendQuestion;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet22SendAnswer;

public class AnswerActivity extends Activity {
    private EditText ET;
    private Packet22SendAnswer p;
    private Client client;
    private String answer;
    TextView roomNameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        loadRoomName();
        loadQuestion();
        ET = (EditText) findViewById(R.id.answer_ET);
        client= Client.getInstance();
    }

    private void loadQuestion() {
        Intent intent =new Intent();
        String question=intent.getStringExtra("question");
        TextView questionTV=(TextView) findViewById(R.id.questionTV);
        questionTV.setText(question);
    }

    private void loadRoomName() {
        roomNameTV=(TextView) findViewById(R.id.roomName);
        roomNameTV.setText("Room Name :" + ResourceState.roomName);
    }
    public void sendAnswer(View v){
        if(ET.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Perhaps i have to give the answer to somebody else ? Please WRITE IT !!!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        answer =ET.getText().toString();
        new SendAnswerAsync().execute();
        Intent intent = new Intent(this, WaitingActivity.class);
        startActivity(intent);
    }
    private class SendAnswerAsync extends AsyncTask<Void, Integer, Void> {

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

            p=new Packet22SendAnswer(answer);
            client.sendData(p.getData());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}

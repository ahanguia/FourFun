package ixdcth.chalmers.edu.fourfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.interfaces.WaitingInterface;

public class WaitingActivity extends AppCompatActivity implements WaitingInterface {

    private Client client ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        client=Client.getInstance();
        client.setWaitingInterface(this);
        Intent i=getIntent();
        String color=i.getStringExtra("color");
        Toast toast = Toast.makeText(this, color, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void gameBegin(Boolean q) {
        if(q==true){
            ResourceState.isQ=true;
            Intent intent = new Intent(this, QuestionActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void startDiscussion(String question, ArrayList<String> answers) {
        if(ResourceState.isQ==true){
            ResourceState.isQ=false;
            Intent intent = new Intent(this, DiscussionActivity.class);
            intent.putExtra("question",question);
            intent.putStringArrayListExtra("answers", answers);
            startActivity(intent);
        }
    }

    @Override
    public void answerReceived(String nameOfAnswerer) {
        //Think about delete it
        //GET NULL FROM Client CLASS !!!!! /!\
    }

    @Override
    public void questionRecieved(String question) {
        if(ResourceState.isQ==false){
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("question",question);
            startActivity(intent);
        }
    }
}

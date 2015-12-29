package ixdcth.chalmers.edu.fourfun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.gifAndQuotes;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.interfaces.WaitingInterface;

public class WaitingActivity extends Activity implements WaitingInterface {

    private Client client ;
    String color;
    ArrayList<String> quotes=new ArrayList<String>();
    TextView quoteTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        loadQuote();

        /*client=Client.getInstance();
        client.setWaitingInterface(this);
        Intent i=getIntent();
        color=i.getStringExtra("color");
        Toast toast = Toast.makeText(this, color, Toast.LENGTH_LONG);
        toast.show();*/
    }

    private void loadQuote() {
        quotes.add("What is Bruce Lee’s favorite drink? Wataaaaah!");
        quotes.add("You kill vegetarian vampires with a steak to the heart");
        quotes.add("The dyslexic devil worshipper sold his soul to Santa");
        quotes.add( "A blind man walks into a bar. And a table. And a chair");
        quotes.add("How does NASA organize their company parties? They planet");
        quotes.add("What kind of shoes do ninjas wear? Sneakers");
        int idx = new Random().nextInt(quotes.size());
        String random = quotes.get(idx);
        quoteTV=(TextView)findViewById(R.id.TV);
        quoteTV.setText(random);
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
            Intent intent = new Intent(this, AnswerActivity.class);
            intent.putExtra("question",question);
            startActivity(intent);
        }
    }
}

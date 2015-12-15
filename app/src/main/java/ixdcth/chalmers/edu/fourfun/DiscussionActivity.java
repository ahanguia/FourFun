package ixdcth.chalmers.edu.fourfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ixdcth.chalmers.edu.fourfun.net.Client;

public class DiscussionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
    }
    public void onClickDone(View v){
        Client client=Client.getInstance();
        Intent intent = new Intent(this, WaitingActivity.class);
        startActivity(intent);
    }
}

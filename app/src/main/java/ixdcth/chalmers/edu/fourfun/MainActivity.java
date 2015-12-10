package ixdcth.chalmers.edu.fourfun;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet13CreateRoom;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet14RoomCreated;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void start(View v){
        Intent intent = new Intent(this, CreateJoinRoomActivity.class);
        startActivity(intent);
    }


}

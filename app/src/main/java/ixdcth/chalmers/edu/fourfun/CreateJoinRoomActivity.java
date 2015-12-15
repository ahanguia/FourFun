package ixdcth.chalmers.edu.fourfun;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet13CreateRoom;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet14RoomCreated;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet13CreateRoom;

public class CreateJoinRoomActivity extends AppCompatActivity implements CreateJoinInterface {

    Client client;
    Connection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_join_room);
        connection = new Connection();
        client = Client.getInstance();
        client.start();

        Client.setCreateJoinInterface(this);


    }

    @Override
    public void loginAccept() {
        for (int i = 0; i < 1; i++) {
            System.out.println("BRAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }


    }

    @Override
    public void loginFailed(String reason) {

    }

    @Override
    public void roomCreated(String pColor) {
        Intent intent = new Intent(this, WaitingActivity.class);
        intent.putExtra("color", pColor);
        startActivity(intent);
    }

    @Override
    public void roomFailed(String reason) {
        Toast toast = Toast.makeText(this, "ROOM haven't been created because "+reason, Toast.LENGTH_LONG);
        toast.show();

    }

    @Override
    public void roomJoined(String colorID) {
        Intent intent = new Intent(this, WaitingActivity.class);
        intent.putExtra("color", colorID);
        startActivity(intent);
    }

    @Override
    public void joinFailed(String reason) {
        Toast toast = Toast.makeText(this, "ROOM haven't been joined because "+reason, Toast.LENGTH_LONG);
        toast.show();
    }

    private class Async extends AsyncTask<Void, Integer, Void> {

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

            Packet13CreateRoom packet13CreateRoom = new Packet13CreateRoom("My Room");
            client.sendData(packet13CreateRoom.getData());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }


    public void joinRoom(View v) {

    }
    public void createRoom(View v) {
        Log.i("ADAM","Room Created");
        new Async().execute();
    }
}

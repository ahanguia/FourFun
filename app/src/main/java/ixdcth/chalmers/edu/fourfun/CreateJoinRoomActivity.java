package ixdcth.chalmers.edu.fourfun;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet13CreateRoom;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet14RoomCreated;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet13CreateRoom;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet16JoinRoom;

public class CreateJoinRoomActivity extends Activity implements CreateJoinInterface {

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
        /*Toast toast = Toast.makeText(this, "ROOM haven't been created because "+reason, Toast.LENGTH_LONG);
        toast.show();*/
    }

    @Override
    public void roomJoined(String colorID) {
        Log.i("SIMON", "Room Joined");
        Intent intent = new Intent(this, WaitingActivity.class);
        intent.putExtra("color", colorID);
        startActivity(intent);
    }

    @Override
    public void joinFailed(String reason) {
        /*Toast toast = Toast.makeText(this, "ROOM haven't been joined because "+reason, Toast.LENGTH_LONG);
        toast.show();*/
    }

    public void createRoom(View v) {
        Log.i("ADAM", "Room Created");

        EditText ETroomName=(EditText) findViewById(R.id.roomNameET);
        ResourceState.roomName=ETroomName.getText().toString();
        new CreateAsync().execute();
    }
    public void joinRoom(View v) {
        Log.i("ADAM", "Room Joined");
        EditText ETroomName=(EditText) findViewById(R.id.roomNameET);
        ResourceState.roomName=ETroomName.getText().toString();
        new JoinAsync().execute();
    }

    private class CreateAsync extends AsyncTask<Void, Integer, Void> {

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

            Packet13CreateRoom packet13CreateRoom = new Packet13CreateRoom(ResourceState.roomName);
            client.sendData(packet13CreateRoom.getData());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }

    private class JoinAsync extends AsyncTask<Void, Integer, Void> {

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
            Packet16JoinRoom packet16JoinRoom = new Packet16JoinRoom(ResourceState.roomName);
            client.sendData(packet16JoinRoom.getData());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }




}

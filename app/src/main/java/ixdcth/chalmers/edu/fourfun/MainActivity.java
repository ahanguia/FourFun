package ixdcth.chalmers.edu.fourfun;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet13CreateRoom;
import ixdcth.chalmers.edu.fourfun.net.packet.Packet14RoomCreated;

public class MainActivity extends AppCompatActivity implements CreateJoinInterface{
    Client client;
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection=new Connection();
        client = new Client("adam","129.16.77.163",12753,connection);
        client.start();

        Client.setCreateJoinInterface(this);
        new Async().execute();

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

    }

    @Override
    public void roomJoined(String colorID) {

    }

    @Override
    public void joinFailed(String reason) {

    }
    private class Async extends AsyncTask<Void, Integer, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Packet13CreateRoom packet13CreateRoom=new Packet13CreateRoom("My Room");
            client.sendData(packet13CreateRoom.getData());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}

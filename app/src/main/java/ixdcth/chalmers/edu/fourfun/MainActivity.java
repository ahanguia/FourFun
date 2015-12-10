package ixdcth.chalmers.edu.fourfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;

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
    }

    @Override
    public void loginAccept() {

    }

    @Override
    public void loginFailed(String reason) {

    }

    @Override
    public String roomCreated(String pColor) {
        return null;
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
}

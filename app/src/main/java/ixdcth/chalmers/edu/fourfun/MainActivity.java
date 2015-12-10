package ixdcth.chalmers.edu.fourfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ixdcth.chalmers.edu.fourfun.net.Client;

public class MainActivity extends AppCompatActivity {
    Client client;
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection=new Connection();
        client = new Client("adam","129.16.77.163",12753,connection);
        client.start();

    }
}

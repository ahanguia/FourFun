package ixdcth.chalmers.edu.fourfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class WaitingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Intent i=getIntent();
        String color=i.getStringExtra("color");
        Toast toast = Toast.makeText(this, color, Toast.LENGTH_LONG);
        toast.show();
    }
}

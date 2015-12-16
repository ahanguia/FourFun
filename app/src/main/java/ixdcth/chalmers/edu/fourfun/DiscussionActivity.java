package ixdcth.chalmers.edu.fourfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ixdcth.chalmers.edu.fourfun.net.Client;

public class DiscussionActivity extends AppCompatActivity {

    ListView lv;
    TextView questionTv;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        lv=(ListView) findViewById(R.id.lView);
        questionTv=(TextView) findViewById(R.id.questionTV);
        showQuestion();
        showAnswers();
        intent =getIntent();
    }

    private void showAnswers() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, intent.getStringArrayExtra("answers"));
        lv.setAdapter(adapter);
    }

    private void showQuestion() {

        questionTv.setText(intent.getStringExtra("question"));
    }

    public void onClickDone(View v){
        Client client=Client.getInstance();
        Intent intent = new Intent(this, WaitingActivity.class);
        startActivity(intent);
    }
}

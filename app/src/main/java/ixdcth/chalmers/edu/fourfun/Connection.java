package ixdcth.chalmers.edu.fourfun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ixdcth.chalmers.edu.fourfun.net.ClientInterface;

/**
 * Created by adam on 10/12/2015.
 */
public class Connection implements ClientInterface{

    @Override
    public void loginAccept() {
        for (int i = 0; i < 1; i++) {
            System.out.println("Connected");
        }

    }

    @Override
    public void loginFailed(String reason) {

    }

    @Override
    public String roomCreated(String pColor) {
        return pColor;
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

    @Override
    public void gameBegin(boolean isQuestioneer) {

    }

    @Override
    public void questionReceived(String question) {

    }

    @Override
    public void answerReceived(String nameOfAnswerer) {

    }

    @Override
    public void startDiscussion(String[] answers) {

    }

}

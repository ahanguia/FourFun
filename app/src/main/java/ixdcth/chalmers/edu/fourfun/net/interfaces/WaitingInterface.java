package ixdcth.chalmers.edu.fourfun.net.interfaces;

import java.util.ArrayList;

/**
 * Created by adam on 15/12/2015.
 */
public interface WaitingInterface {
    public void gameBegin(Boolean q);
    public void startDiscussion(String question,ArrayList<String> answers);
    public void answerReceived(String nameOfAnswerer);//Think about delete it
    public void questionRecieved(String question);
}

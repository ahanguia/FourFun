package ixdcth.chalmers.edu.fourfun.net.interfaces;

/**
 * Created by Simpe on 2015-12-10.
 */
public interface CreateJoinInterface {

    public void loginAccept();
    public void loginFailed(String reason);
    public String roomCreated(String pColor);
    public void roomFailed(String reason);
    public void roomJoined(String colorID);
    public void joinFailed(String reason);

}

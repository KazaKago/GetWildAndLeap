import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
import javazoom.jlgui.basicplayer.BasicPlayer;

import java.io.IOException;

class MainListener extends Listener {

    private GetWildAndToughPlayer getWildAndToughPlayer = new GetWildAndToughPlayer();

    @Override
    public void onConnect(Controller controller) {
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);
            SwipeGesture swipe = new SwipeGesture(gesture);
            if (swipe.state() == State.STATE_STOP) {
                float x = swipe.direction().getX();
                float y = swipe.direction().getY();
                float z = swipe.direction().getZ();
                if (Math.abs(x) > Math.abs(y) && Math.abs(x) > Math.abs(z)) {
                    if (swipe.direction().getX() > 0) {
                        if (getWildAndToughPlayer.status() == BasicPlayer.PAUSED) {
                            getWildAndToughPlayer.resume();
                            System.out.println("GetWildAndResume");
                        } else if (getWildAndToughPlayer.status() == BasicPlayer.OPENED ||
                                getWildAndToughPlayer.status() == BasicPlayer.STOPPED) {
                            getWildAndToughPlayer.play();
                            System.out.println("GetWildAndPlay");
                        }
                    } else {
                        if (getWildAndToughPlayer.status() == BasicPlayer.PLAYING) {
                            getWildAndToughPlayer.pause();
                            System.out.println("GetWildAndPause");
                        }
                    }
                }
                if (Math.abs(y) > Math.abs(x) && Math.abs(y) > Math.abs(z)) {
                    if (swipe.direction().getY() > 0) {
                        getWildAndToughPlayer.stop();
                        getWildAndToughPlayer.play();
                        System.out.println("GetWildAndPlay");
                    } else {
                        if (getWildAndToughPlayer.status() == BasicPlayer.PLAYING ||
                                getWildAndToughPlayer.status() == BasicPlayer.PAUSED) {
                            getWildAndToughPlayer.stop();
                            System.out.println("GetWildAndStop");
                        }
                    }
                }
            }
        }
    }

}

class Main {
    public static void main(String[] args) {
        MainListener listener = new MainListener();
        Controller controller = new Controller();
        controller.addListener(listener);

        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.removeListener(listener);
    }
}

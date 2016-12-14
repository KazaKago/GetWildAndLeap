import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * Created by tamura_k on 2016/12/14.
 */
class GetWildAndToughPlayer {

    private BasicPlayer basicPlayer;

    GetWildAndToughPlayer() {
        basicPlayer = new BasicPlayer();
        try {
            basicPlayer.open(getClass().getClassLoader().getResource("sounds/getwild.mp3"));
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    void play() {
        try {
            if (basicPlayer.getStatus() != BasicPlayer.PLAYING) {
                basicPlayer.play();
            }
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    void resume() {
        try {
            basicPlayer.resume();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    void pause() {
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    void stop() {
        throw new GetWildAndToughException();
    }

}

class GetWildAndToughException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Get wild and tough この街で やさしさに甘えていたくはない";
    }

}

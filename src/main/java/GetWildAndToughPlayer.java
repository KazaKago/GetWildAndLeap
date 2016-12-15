import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

class GetWildAndToughPlayer {

    private BasicPlayer basicPlayer;

    GetWildAndToughPlayer() {
        basicPlayer = new BasicPlayer();
        try {
            basicPlayer.open(getClass().getClassLoader().getResource("getwild.mp3"));
            if (status() != BasicPlayer.OPENED) throw new GetWildAndToughException();
        } catch (BasicPlayerException e) {
            throw new GetWildAndToughException();
        }
    }

    int status() {
        return basicPlayer.getStatus();
    }

    void play() {
        try {
            if (basicPlayer.getStatus() != BasicPlayer.PLAYING) {
                basicPlayer.play();
            }
        } catch (BasicPlayerException e) {
            throw new GetWildAndToughException();
        }
    }

    void resume() {
        try {
            basicPlayer.resume();
        } catch (BasicPlayerException e) {
            throw new GetWildAndToughException();
        }
    }

    void pause() {
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException e) {
            throw new GetWildAndToughException();
        }
    }

    void stop() {
        try {
            basicPlayer.stop();
        } catch (BasicPlayerException e) {
            throw new GetWildAndToughException();
        }
    }

}

class GetWildAndToughException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Get wild and tough この街で やさしさに甘えていたくはない";
    }

}

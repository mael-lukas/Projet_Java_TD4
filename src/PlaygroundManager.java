import java.util.ArrayList;

public class PlaygroundManager {
    public ArrayList<Playground> playgroundList;
    private int maxPlayground = 10;
    public int currentPlayground = 0;

    public PlaygroundManager() {
        this.playgroundList = new ArrayList<Playground>();
        playgroundList.add(0,new Playground("./data/level1.txt", 6, 9));


    }

    public void setCurrentPlayground(int currentPlayground) {
        this.currentPlayground = currentPlayground;
    }
}

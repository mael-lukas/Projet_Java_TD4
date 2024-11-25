import java.util.ArrayList;

public class PlaygroundManager {
    public ArrayList<Playground> playgroundList;
    private int maxPlayground = 10;
    public int currentPlayground = 2;
    Camera camera;

    public PlaygroundManager(Camera camera) {
        this.camera = camera;
        this.playgroundList = new ArrayList<Playground>();
        playgroundList.add(0,new Playground("./data/level1.txt", 6, 9,camera));
        playgroundList.add(1,new Playground("./data/level2.txt",6,9,camera));
        playgroundList.add(2,new Playground("./data/large_map1.txt",66,56,camera));
    }

    public void setCurrentPlayground(int currentPlayground) {
        this.currentPlayground = currentPlayground;
    }
}

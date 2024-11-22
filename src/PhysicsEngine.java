import java.util.ArrayList;

public class PhysicsEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList = new ArrayList<DynamicSprite>();
    private ArrayList<ArrayList<Sprite>> environments;
    PlaygroundManager pgManager;

    public PhysicsEngine(PlaygroundManager pgManager) {
        this.pgManager = pgManager;
        this.environments = new ArrayList<ArrayList<Sprite>>();
        for (Playground pg : (pgManager.playgroundList)){
            environments.add(pg.getSolidSpriteList());
        }
    }

    public void addToMovingSpriteList(DynamicSprite dynamicSprite) {
        movingSpriteList.add(dynamicSprite);
    }

//    public void setEnvironment(ArrayList<Sprite> environment) {
//        this.environment = environment;
//    }

    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environments.get(pgManager.currentPlayground));
        }
    }
}

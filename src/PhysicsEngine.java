import java.util.ArrayList;

public class PhysicsEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList = new ArrayList<DynamicSprite>();
    private ArrayList<Sprite> environment;

    public void addToMovingSpriteList(DynamicSprite dynamicSprite) {
        movingSpriteList.add(dynamicSprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);
        }
    }

}

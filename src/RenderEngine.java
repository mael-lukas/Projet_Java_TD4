import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RenderEngine extends JPanel implements Engine {
    PlaygroundManager pgManager;
    private ArrayList<ArrayList<Sprite>> rendersList;

    public RenderEngine(PlaygroundManager pgManager) {
        this.rendersList = new ArrayList<ArrayList<Sprite>>();
        this.pgManager = pgManager;
        for (Playground pg : (pgManager.playgroundList)){
            rendersList.add(pg.getSpriteList());
        }
    }

    public void addToRenderList(Sprite sprite, int renderedMapIndex) {
        ArrayList<Sprite> renderedMap = rendersList.get(renderedMapIndex);
        renderedMap.add(sprite);
        rendersList.set(renderedMapIndex, renderedMap);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        ArrayList<Sprite> renderedMap = rendersList.get(pgManager.currentPlayground);
        for(Sprite sprite : renderedMap) {
            sprite.draw(g);
        }
        g.dispose();
    }
}

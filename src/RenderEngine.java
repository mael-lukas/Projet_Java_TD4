import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RenderEngine extends JPanel implements Engine {
    //private LinkedList<Displayable> renderList;
    PlaygroundManager pgManager;
    private ArrayList<ArrayList<Displayable>> rendersList;

    public RenderEngine(PlaygroundManager pgManager) {
       // renderList = new LinkedList<Displayable>();
        this.rendersList = new ArrayList<ArrayList<Displayable>>();
        this.pgManager = pgManager;
        for (Playground pg : (pgManager.playgroundList)){
            rendersList.add(pg.getSpriteList());
        }
    }

//    public void addToRenderList(ArrayList<Displayable> displayable){
//        if (!renderList.contains(displayable)){
//            renderList.addAll(displayable);
//        }
//    }

    public void addToRenderList(Displayable displayable, int renderedMapIndex) {
        ArrayList<Displayable> renderedMap = rendersList.get(renderedMapIndex);
        renderedMap.add(displayable);
        rendersList.set(renderedMapIndex, renderedMap);
        //rendersList.add(displayable);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        ArrayList<Displayable> renderedMap = rendersList.get(pgManager.currentPlayground);
        for(Displayable displayable : renderedMap) {
            displayable.draw(g);
        }
        g.dispose();
    }
}

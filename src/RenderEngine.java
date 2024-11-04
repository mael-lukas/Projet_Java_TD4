import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RenderEngine extends JPanel implements Engine {
    private LinkedList<Displayable> renderList;

    public RenderEngine() {
        renderList = new LinkedList<Displayable>();
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        for(Displayable displayable : renderList) {
            displayable.draw(g);
        }
        g.dispose();
    }
}

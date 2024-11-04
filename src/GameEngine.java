import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    private final DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // get integer code of the key pressed
        if(code == KeyEvent.VK_Z) { // KeyEvent.VK_xxx is  the key code for key xxx
            hero.setDirection(Direction.NORTH);
        }
        if(code == KeyEvent.VK_S) {
            hero.setDirection(Direction.SOUTH);
        }
        if(code == KeyEvent.VK_Q) {
            hero.setDirection(Direction.WEST);
        }
        if(code == KeyEvent.VK_D) {
            hero.setDirection(Direction.EAST);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

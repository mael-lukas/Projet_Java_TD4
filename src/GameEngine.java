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
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_Z) {
            hero.isWalking = true;
            hero.setDirection(Direction.NORTH);
        }
        if(code == KeyEvent.VK_S) {
            hero.isWalking = true;
            hero.setDirection(Direction.SOUTH);
        }
        if(code == KeyEvent.VK_Q) {
            hero.isWalking = true;
            hero.setDirection(Direction.WEST);
        }
        if(code == KeyEvent.VK_D) {
            hero.isWalking = true;
            hero.setDirection(Direction.EAST);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_Z) {
            hero.isWalking = false;
        }
        if(code == KeyEvent.VK_S) {
            hero.isWalking = false;
        }
        if(code == KeyEvent.VK_Q) {
            hero.isWalking = false;
        }
        if(code == KeyEvent.VK_D) {
            hero.isWalking = false;
        }
    }
}

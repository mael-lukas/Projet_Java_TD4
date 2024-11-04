import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private boolean isWalking = true;
    private double speed = 5;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame = 100;
    private Direction direction;

    public DynamicSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private boolean isWalkingPossible(ArrayList<Sprite> environment) {
        double future_x = this.x;
        double future_y = this.y;
        switch (direction) {
            case NORTH:
                future_y -= speed;
                break;
            case SOUTH:
                future_y += speed;
                break;
            case EAST:
                future_x += speed;
                break;
            case WEST:
                future_x -= speed;
                break;
        }
        Rectangle2D.Double hitbox = new Rectangle2D.Double(future_x, future_y, this.width, this.height);
        for(Sprite sprite : environment) {
            if((sprite instanceof SolidSprite) && (sprite != this) && (((SolidSprite)sprite).intersect(hitbox))) {
                return false;
            }
        }
        return true;
    }

    private void move() {
        switch(direction) {
            case NORTH:
                this.y -= speed;
                break;
            case SOUTH:
                this.y += speed;
                break;
            case EAST:
                this.x += speed;
                break;
            case WEST:
                this.x -= speed;
                break;
        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if(isWalkingPossible(environment)) {
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        int index = (int)(System.currentTimeMillis()/timeBetweenFrame)%spriteSheetNumberOfColumn;
        int attitude = direction.getFrameLineNumber();
        int srcx1 = (int)(index * this.width);
        int srcy1 = (int)(attitude * this.height);
        int srcx2 = (int)((index + 1) * this.width);
        int srcy2 = (int)((attitude + 1) * this.height);
        g.drawImage(image, (int)this.x, (int)this.y, (int)(this.x+this.width), (int)(this.y+this.height), srcx1, srcy1, srcx2, srcy2, null);
    }
}

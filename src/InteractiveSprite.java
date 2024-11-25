import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InteractiveSprite extends Sprite {
    private String name;

    public InteractiveSprite(Image image, double x, double y, double width, double height, String name) {
        super(image, x, y, width, height);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Rectangle2D getHitbox() {
        return new Rectangle2D.Double(x,y,width,height);
    }

    public boolean intersect(Rectangle2D.Double hitbox) {
        return this.getHitbox().intersects(hitbox);
    }
}

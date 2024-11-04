import java.awt.*;

public class Sprite implements Displayable {
    Image image;
    double x;
    double y;
    double width;
    double height;

    public Sprite(Image image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.image, (int) this.x, (int) this.y, (int) this.width, (int) this.height, null);
    }
}

import java.awt.*;

public class Sprite implements Displayable {
    Image image;
    double x;
    double y;
    double width;
    double height;
    Camera camera;

    public Sprite(Image image, double x, double y, double width, double height, Camera camera) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.camera = camera;
    }

    @Override
    public void draw(Graphics g) {
        double spriteScreenX = this.x - camera.worldX + camera.centerX;
        double spriteScreenY = this.y - camera.worldY + camera.centerY;
        if(this.x + 64 > camera.worldX - camera.centerX &&
                this.x - 64< camera.worldX + camera.centerX &&
                this.y + 64 > camera.worldY - camera.centerY &&
                this.y - 64 < camera.worldY + camera.centerY) {
            g.drawImage(this.image, (int) spriteScreenX, (int) spriteScreenY, (int) this.width, (int) this.height, null);
        }
    }
}

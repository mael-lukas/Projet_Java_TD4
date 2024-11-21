import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private double speed = 5;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame = 100;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private String direction;
    GameEngine ge;
    private Rectangle2D.Double hitbox;
    private int hitboxXOffset = 0;
    private int hitboxYOffset = 0;

    public DynamicSprite(Image image, double x, double y, double width, double height, GameEngine ge) {
        super(image, x, y, width, height);
        this.ge = ge;
        this.direction = "south";
        this.hitbox = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
    }

    public void setHitbox(int xOffset,int yOffset,int width,int height) {
        this.hitboxXOffset = xOffset;
        this.hitboxYOffset = yOffset;
        this.hitbox.x = this.x + xOffset;
        this.hitbox.y = this.y + yOffset;
        this.hitbox.width = width;
        this.hitbox.height = height;
    }

    private boolean isWalkingPossible(ArrayList<Sprite> environment) {
        double futureX = this.x;
        double futureY = this.y;
        switch (direction) {
            case "north":
                futureY -= speed;
                break;
            case "south":
                futureY += speed;
                break;
            case "east":
                futureX += speed;
                break;
            case "west":
                futureX -= speed;
                break;
        }
        this.hitbox.x = this.hitboxXOffset + futureX;
        this.hitbox.y = this.hitboxYOffset + futureY;
        for(Sprite sprite : environment) {
            if((sprite instanceof SolidSprite) && (sprite != this) && (((SolidSprite)sprite).intersect(this.hitbox))) {
                return false;
            }
        }
        return true;
    }

    private void move() {
        switch(direction) {
            case "north":
                this.y -= speed;
                break;
            case "south":
                this.y += speed;
                break;
            case "east":
                this.x += speed;
                break;
            case "west":
                this.x -= speed;
                break;
        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if(ge.upPressed || ge.downPressed || ge.leftPressed || ge.rightPressed) {
            if(ge.upPressed) {
                direction = "north";
            }
            if(ge.downPressed) {
                direction = "south";
            }
            if(ge.leftPressed) {
                direction = "west";
            }
            if(ge.rightPressed) {
                direction = "east";
            }
            if(isWalkingPossible(environment)) {
                move();
            }
            spriteCounter++;
            if(spriteCounter > 5) {
                spriteNumber = (spriteNumber + 1) % spriteSheetNumberOfColumn;
                spriteCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        int attitude = 0;
        switch(direction) {
            case "north":
                attitude = 2;
                break;
            case "south":
                attitude = 0;
                break;
            case "east":
                attitude = 3;
                break;
            case "west":
                attitude = 1;
                break;
        }
        int srcx1 = (int)(spriteNumber * this.width);
        int srcy1 = (int)(attitude * this.height);
        int srcx2 = (int)((spriteNumber + 1) * this.width);
        int srcy2 = (int)((attitude + 1) * this.height);
        g.drawImage(image, (int)this.x, (int)this.y, (int)(this.x+this.width), (int)(this.y+this.height), srcx1, srcy1, srcx2, srcy2, null);
    }
}

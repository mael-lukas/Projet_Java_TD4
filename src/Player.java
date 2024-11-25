import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player extends DynamicSprite {
    public int speed = 3;
    private final int spriteSheetNumberOfColumn = 10;
    public String direction;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private Rectangle2D.Double hitbox;
    private int hitboxXOffset = 0;
    private int hitboxYOffset = 0;
    PlaygroundManager pgManager;

    public Player(Image image, double x, double y, double width, double height, GameEngine ge, PlaygroundManager pgManager) {
        super(image, x, y, width, height, ge);
        this.direction = "south";
        this.ge = ge;
        this.pgManager = pgManager;
        this.hitbox = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
        setHitbox(10,18,27,28);
    }

    public void setHitbox(int xOffset,int yOffset,int width,int height) {
        this.hitboxXOffset = xOffset;
        this.hitboxYOffset = yOffset;
        this.hitbox.x = this.x + xOffset;
        this.hitbox.y = this.y + yOffset;
        this.hitbox.width = width;
        this.hitbox.height = height;
    }

    public boolean checkForCollision(ArrayList<Sprite> environment) {
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

    public void checkForInteraction(ArrayList<Sprite> environment) {
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
            if ((sprite instanceof InteractiveSprite) && (((InteractiveSprite)sprite).intersect(this.hitbox))) {
                String name = ((InteractiveSprite)sprite).getName();
                switch(name) {
                    case "door":
                        this.pgManager.setCurrentPlayground((this.pgManager.currentPlayground + 1)%2);
                        break;
                }
            }
        }
    }

    @Override
    public void move() {
        if (ge.running == true) {
            switch(direction) {
                case "north":
                    this.y -= (speed + 2);
                    break;
                case "south":
                    this.y += (speed + 2);
                    break;
                case "east":
                    this.x += (speed + 2);
                    break;
                case "west":
                    this.x -= (speed + 2);
                    break;
            }
        }
        else {
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
    }

    @Override
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
            if(checkForCollision(environment)) {
                move();
                checkForInteraction(environment);
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
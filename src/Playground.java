import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();
    private String mapPathName;
    public int maxMapCol;
    public int maxMapRow;
    Camera camera;
    int tileSize = 64;

    public Playground (String pathName, int maxMapCol, int maxMapRow, Camera camera){
        this.mapPathName = pathName;
        this.maxMapCol = maxMapCol;
        this.maxMapRow = maxMapRow;
        this.camera = camera;
        try{
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png"));
            final Image imageDoor = ImageIO.read(new File("./img/door.png"));
            final Image imageLadder1 = ImageIO.read(new File("./img/ladder1.png"));
            final Image imageLadder2 = ImageIO.read(new File("./img/ladder2.png"));
            final Image imageFloor = ImageIO.read(new File("./img/dungeon_floor.png"));
            final Image imageWall = ImageIO.read(new File("./img/dungeon_wall.png"));
            final Image imageOOB1 = ImageIO.read(new File("./img/oob1.png"));
            final Image imageOOB2 = ImageIO.read(new File("./img/oob2.png"));
            final Image imageOOB3 = ImageIO.read(new File("./img/oob3.png"));
            final Image imageOOB4 = ImageIO.read(new File("./img/oob4.png"));
            final Image imageOOB5 = ImageIO.read(new File("./img/oob5.png"));
            final Image imageOOB6 = ImageIO.read(new File("./img/oob6.png"));
            final Image imageOOB7 = ImageIO.read(new File("./img/oob7.png"));
            final Image imageOOB8 = ImageIO.read(new File("./img/oob8.png"));
            final Image imageOOB9 = ImageIO.read(new File("./img/oob9.png"));
            final Image imageOOBa = ImageIO.read(new File("./img/ooba.png"));
            final Image imageOOBb = ImageIO.read(new File("./img/oobb.png"));
            final Image imageOOBc = ImageIO.read(new File("./img/oobc.png"));
            final Image imageOOBd = ImageIO.read(new File("./img/oobd.png"));
            final Image imageLava = ImageIO.read(new File("./img/lava_floor.png"));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(imageTree, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case ' ' : environment.add(new Sprite(imageGrass,columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case 'R' : environment.add(new SolidSprite(imageRock, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case 'D': environment.add(new InteractiveSprite(imageDoor, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera,"door"));
                            break;
                        case 'L': environment.add(new InteractiveSprite(imageLadder1, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera,"ladder1"));
                            break;
                        case 'M': environment.add(new InteractiveSprite(imageLadder2, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera,"ladder2"));
                            break;
                        case '/': environment.add(new Sprite(imageFloor, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                                break;
                        case '0': environment.add(new SolidSprite(imageWall, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '1': environment.add(new SolidSprite(imageOOB1, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '2': environment.add(new SolidSprite(imageOOB2, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '3': environment.add(new SolidSprite(imageOOB3, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '4': environment.add(new SolidSprite(imageOOB4, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '5': environment.add(new SolidSprite(imageOOB5, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '6': environment.add(new SolidSprite(imageOOB6, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '7': environment.add(new SolidSprite(imageOOB7, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '8': environment.add(new SolidSprite(imageOOB8, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case '9': environment.add(new Sprite(imageOOB9, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case 'a': environment.add(new SolidSprite(imageOOBa, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case 'b': environment.add(new SolidSprite(imageOOBb, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case 'c': environment.add(new SolidSprite(imageOOBc, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case 'd': environment.add(new SolidSprite(imageOOBd, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera));
                            break;
                        case 'l': environment.add(new InteractiveSprite(imageLava, columnNumber*tileSize,
                                lineNumber*tileSize, tileSize, tileSize,camera,"lava"));
                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }
    public ArrayList<Sprite> getInteractiveSpriteList(){
        ArrayList <Sprite> interactiveSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof InteractiveSprite) interactiveSpriteArrayList.add(sprite);
        }
        return interactiveSpriteArrayList;
    }
    public ArrayList<Sprite> getSpriteList(){
        ArrayList<Sprite> spriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            spriteArrayList.add( sprite);
        }
        return spriteArrayList;
    }
}
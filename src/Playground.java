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
            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);
            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);
            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);
            final int imageDoorWidth = imageRock.getWidth(null);
            final int imageDoorHeight = imageRock.getHeight(null);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(imageTree, columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight, imageTreeWidth, imageTreeHeight,camera));
                            break;
                        case ' ' : environment.add(new Sprite(imageGrass,columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight, imageGrassWidth, imageGrassHeight,camera));
                            break;
                        case 'R' : environment.add(new SolidSprite(imageRock, columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight, imageRockWidth, imageRockHeight,camera));
                            break;
                        case 'D': environment.add(new InteractiveSprite(imageDoor, columnNumber*imageDoorWidth,
                                lineNumber*imageDoorHeight, imageDoorWidth, imageDoorHeight,camera,"door"));
                            break;

                    }
                    columnNumber++;
                }
                columnNumber =0;
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
public class Camera {
    public double worldX;
    public double worldY;
    public final double centerX = (1920 - 64)/2;
    public final double centerY = (1080 - 64)/2;

    public Camera(double x, double y) {
        this.worldX = x;
        this.worldY = y;
    }

    public void setCameraXandY(double x, double y) {
        this.worldX = x;
        this.worldY = y;
    }
}

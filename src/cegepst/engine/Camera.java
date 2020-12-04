package cegepst.engine;

public class Camera {

    private static Camera instance;
    private final int HALF_SCREEN_X;
    private final int HALF_SCREEN_Y;
    private int x;
    private int y;

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }

    private Camera() {
        int screenWidth = RenderingEngine.getInstance().getRenderWidth();
        int screenHeight = RenderingEngine.getInstance().getRenderHeight();
        HALF_SCREEN_X = screenWidth / 2;
        HALF_SCREEN_Y = screenHeight / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getHalfScreenX() {
        return HALF_SCREEN_X;
    }

    public int getHalfScreenY() {
        return HALF_SCREEN_Y;
    }
}

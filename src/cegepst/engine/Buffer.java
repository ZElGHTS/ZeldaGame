package cegepst.engine;

import java.awt.*;

public class Buffer {

    private Graphics2D graphics;

    public Buffer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        x -= Camera.getInstance().getX();
        y -= Camera.getInstance().getY();

        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void drawCircle(int x, int y, int radius, Paint paint) {
        x -= Camera.getInstance().getX();
        y -= Camera.getInstance().getY();

        graphics.setPaint(paint);
        graphics.fillOval(x, y, radius * 2, radius * 2);
    }

    public void drawText(String text, int x, int y, Paint paint) {
        //x -= Camera.getInstance().getX();
        //y -= Camera.getInstance().getY();

        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    public void drawImage(Image image, int x, int y) {
        x -= Camera.getInstance().getX();
        y -= Camera.getInstance().getY();

        graphics.drawImage(image, x, y, null);
    }

    public void drawHearts(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }
}

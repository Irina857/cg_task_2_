package com.example.cg_task_2.rasterization;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rasterization {


    public static void drawEllipseBorderCenterCoord(final GraphicsContext gc, double x, double y, double width, double height) {
        drawEllipseBorderAngleСoord(gc, x - width, y - height, width*2, height*2);
    }
    public static void drawEllipseBorderAngleСoord(final GraphicsContext gc, double x, double y, double width, double height) {

        double centerX = x + width / 2;
        double centerY = y + height / 2;

        double radiustepX = width / 2;
        double radiustepY = height / 2;

        double prevX = centerX + radiustepX;
        double prevY = centerY;

        for (double angle = 0; angle < 2 * Math.PI; angle += 0.01) {
            double newX = centerX + radiustepX * Math.cos(angle);
            double newY = centerY + radiustepY * Math.sin(angle);

            drawLine(gc, prevX, prevY, newX, newY);
            prevX = newX;
            prevY = newY;
        }
    }
    private static void drawLine(final GraphicsContext gc, double x1, double y1, double x2, double y2) {
        int pixelX1 = (int) Math.round(x1);
        int pixelY1 = (int) Math.round(y1);
        int pixelX2 = (int) Math.round(x2);
        int pixelY2 = (int) Math.round(y2);

        int differenceX = Math.abs(pixelX2 - pixelX1);
        int differenceY = Math.abs(pixelY2 - pixelY1);
        //
        int stepX = pixelX1 < pixelX2 ? 1 : -1;
        int stepY = pixelY1 < pixelY2 ? 1 : -1;

        int err = differenceX - differenceY;

        while (pixelX1 != pixelX2 || pixelY1 != pixelY2) {
            gc.getPixelWriter().setColor(pixelX1, pixelY1, Color.BLACK);

            int err2 = err * 2;

            if (err2 > -differenceY) {
                err -= differenceY;
                pixelX1 += stepX;
            }
            if (err2 < differenceX) {
                err += differenceX;
                pixelY1 += stepY;
            }
        }
    }

}

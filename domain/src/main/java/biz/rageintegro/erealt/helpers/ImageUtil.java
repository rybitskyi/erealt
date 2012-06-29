package biz.rageintegro.erealt.helpers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

    public static BufferedImage fit(File sourceFile, int width, int height) throws IOException {
        BufferedImage src = ImageIO.read(sourceFile);
		if (src == null) return null;
        int newWidth;
        int newHeight;

        Float scale;
        if (src.getWidth() > src.getHeight()) {
            scale = Float.valueOf(width) / Float.valueOf(src.getWidth());
        } else {
            scale = Float.valueOf(height) / Float.valueOf(src.getHeight());
        }

        newWidth = Float.valueOf(src.getWidth() * scale).intValue();
        newHeight = Float.valueOf(src.getHeight() * scale).intValue();

        // System.out.println("--- " + src.getWidth() + " - " + width);
        // System.out.println("--- " + src.getHeight() + " - " + height);
        // System.out.println("--- " + scale + " -- " + Float.valueOf(src.getWidth() * scale).intValue() + " -- " + Float.valueOf(src.getHeight() * scale).intValue());

        return scale(src, newWidth, newHeight);
    }

    public static ByteArrayOutputStream scale(ByteArrayInputStream bais, int width, int height) throws IOException {
        BufferedImage src = ImageIO.read(bais);
        BufferedImage dest = scale(src, width, height);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(dest, "JPG", baos);
        return baos;
    }

    public static BufferedImage scale(BufferedImage src, int width, int height) throws IOException {
        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dest.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance(
                (double) width / src.getWidth(),
                (double) height / src.getHeight());
        g.drawRenderedImage(src, at);
        return dest;
    }
}

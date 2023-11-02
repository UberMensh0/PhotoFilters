import java.awt.image.BufferedImage;
import java.awt.Color;

public class Filters {
    public static BufferedImage grayscale(BufferedImage image) {
        // start manipulating 2d array of given image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // get pixelObject of current pixel at (x,y)
                Color pixelColor = new Color(image.getRGB(x, y));

                // get color RGB value numbers (0-255)
                int r = pixelColor.getRed();
                int g = pixelColor.getGreen();
                int b = pixelColor.getBlue();

                // calculate average RGB value of the current pixel
                int gray = (r + g + b) / 3;

                // create a new Color and set new color to pixel at (x,y)
                Color grayColor = new Color(gray, gray, gray);
                image.setRGB(x, y, grayColor.getRGB());
            }
        }

        // return modified image
        return image;
    }

    public static BufferedImage sepia(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));

                int Red = pixelColor.getRed();
                int Green = pixelColor.getGreen();
                int Blue = pixelColor.getBlue();

                int sepiaRed = (int) (0.393 * Red + 0.769 * Green + 0.189 * Blue);
                int sepiaGreen = (int) (0.349 * Red + 0.686 * Green + 0.168 * Blue);
                int sepiaBlue = (int) (0.272 * Red + 0.534 * Green + 0.131 * Blue);


                sepiaRed = Math.min(255, sepiaRed);
                sepiaGreen = Math.min(255, sepiaGreen);
                sepiaBlue = Math.min(255, sepiaBlue);

                Color sepiaColor = new Color(sepiaRed, sepiaGreen, sepiaBlue);
                image.setRGB(x, y, sepiaColor.getRGB());
            }
        }

        // TODO
        return image;
    }

    public static BufferedImage invert(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth() / 2; x++) {

                int pixel1 = image.getRGB(x, y);
                int pixel2 = image.getRGB(image.getWidth() - x - 1, y);

                image.setRGB(x, y, pixel2);
                image.setRGB(image.getWidth() - x - 1, y, pixel1);

            }
        }
        // TODO
        return image;
    }

    public static BufferedImage blur(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();




        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                long totalRed = 0;
                long totalGreen = 0;
                long totalBlue = 0;

                int pixelCount = 0;


                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int nx = x + dx;
                        int ny = y + dy;


                        if (nx >= 0 && nx < width && ny >= 0 && ny < height) {

                            Color pixelColor = new Color(image.getRGB(nx, ny));


                            totalRed += pixelColor.getRed();
                            totalGreen += pixelColor.getGreen();
                            totalBlue += pixelColor.getBlue();


                            pixelCount++;
                        }
                    }
                }
                BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);


                int averageRed = (int)(totalRed / pixelCount);
                int averageGreen = (int)(totalGreen / pixelCount);
                int averageBlue = (int)(totalBlue / pixelCount);


                Color blurredColor = new Color(averageRed, averageGreen, averageBlue);
                image.setRGB(x, y, blurredColor.getRGB());
            }
        }

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        return newImage;
    }


}




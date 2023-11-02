import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Main extends Filters {
    public static void main(String[] args) {
        try {
            // parse arguments
            if (args.length != 2) {
                throw new Exception("Usage: java Main imageName filterName");
            }

            String fileName = args[0];
            String filterName = args[1];

            // read file
            File inputFile = new File(fileName);
            BufferedImage input_image = ImageIO.read(inputFile);
            BufferedImage output_image = null;

            // determine the type of filter and call specific filter functions
            if (Objects.equals(filterName, "grayscale")) {
                output_image = grayscale(input_image);
            }
            else if (Objects.equals(filterName, "sepia")) {
                output_image = sepia(input_image);
            }
            else if (Objects.equals(filterName, "invert")) {
                output_image = invert(input_image);
            }
            else if (Objects.equals(filterName, "blur")) {
                output_image = blur(input_image);
            }
            else {
                throw new Exception("Error: Invalid Filter Name");
            }

            // write new output.jpg
            File outputFile = new File("output.jpg");
            ImageIO.write(output_image, "jpg", outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package lab6;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for(int i = 1; i <= 3; i++) {
            imageProcessing(args, i);
        }
    }
    public static void imageProcessing(String[] args, int threadCount) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        long t0 = System.currentTimeMillis();
        forkJoinPool.submit(() -> {
            try{
                String sourcePath = args[0];
                String destPath = args[1];
                Path source = Path.of(sourcePath);
                Path dest = Path.of(destPath);

                List<Path> files = new ArrayList<>();
                try (Stream<Path> stream = Files.list(source)) {
                    files = stream.collect(Collectors.toList());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                List<Pair<Path, BufferedImage>> pairs;
                pairs = files.stream().parallel().map(path -> {
                    BufferedImage imageOut = null;
                    try {
                        BufferedImage imageIn = ImageIO.read(path.toFile());
                        imageOut = new BufferedImage(imageIn.getWidth(), imageIn.getHeight(), imageIn.getType());
                        for(int y = 0; y < imageIn.getHeight(); y++){
                            for(int x = 0; x < imageIn.getWidth(); x++){
                                imageOut.setRGB(x, y, imageIn.getRGB(x, y));
                            }
                        }
                    } catch(IOException ex){
                        ex.printStackTrace();
                    }
                    return  Pair.of(path, imageOut);
                }).collect(Collectors.toList());
                pairs.stream().parallel().map(pair -> {
                    BufferedImage imageOut = pair.getRight();
                    for(int y = 0; y < imageOut.getHeight(); y++){
                        for(int x = 0; x < imageOut.getWidth(); x++){
                            Color color = new Color(imageOut.getRGB(x, y));
                            int red = color.getRed();
                            int green = color.getGreen();
                            int blue = color.getBlue();
                            int grayScale = (red + green + blue) / 3;
                            Color colorOut = new Color(grayScale, grayScale, grayScale);
                            imageOut.setRGB(x, y, colorOut.getRGB());
                        }
                    }
                    String filename = pair.getLeft().getFileName().toString();
                    return Pair.of(Path.of(dest + "/" + filename), imageOut);
                }).forEach(pair -> {
                    try{
                        ImageIO.write(pair.getRight(), "jpg", pair.getLeft().toFile());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                });
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }).get();
        long duration = System.currentTimeMillis() - t0;
        System.out.println("Thread count: " + threadCount + "\tTime: " + (double)duration / 1000 + " seconds");
    }
}

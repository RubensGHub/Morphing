package morphing;

import java.util.ArrayList;
import java.util.List;

public class FormesSimple {
    private final ImageT srcImage;
    private final ImageT destImage;
    private final List<ImageT> frames;

    public FormesSimple(ImageT srcImage, ImageT destImage) 
    {
        this.srcImage = srcImage;
        this.destImage = destImage;
        this.frames = new ArrayList<>();
    }

    public List<ImageT> getFrames() 
    {
        return frames;
    }

    public void generateFrames(int nbFrames) 
    {
        int minLines = Math.min(srcImage.getLines().size(), destImage.getLines().size());

        for (int t = 0; t <= nbFrames; t++) 
        {
            double fraction = (double) t / nbFrames;
            ImageT frame = new ImageT(srcImage.getWidth(), srcImage.getHeight(), srcImage.getFormat());

            for (int i = 0; i < minLines; i++) 
            {
                Line srcLine = srcImage.getLines().get(i);
                Line destLine = destImage.getLines().get(i);

                Point newStart = srcLine.getStart().nextPoint(destLine.getStart(), fraction);
                Point newEnd = srcLine.getEnd().nextPoint(destLine.getEnd(), fraction);

                Line newLine = new Line(newStart, newEnd);
                frame.addLine(newLine);
            }

            frames.add(frame);
        }
    }

    // Main pour tests
    /*public static void main(String[] args) 
    {
        Point A = new Point(10, 10);
        Point B = new Point(100, 10);
        Point C = new Point(100, 100);
        Point D = new Point(10, 100);

        Point A1 = new Point(10, 10);
        Point B1 = new Point(100, 10);
        Point C1 = new Point(55, 100);

        // Rectangle
        Line line1 = new Line(A, B);
        Line line2 = new Line(B, C);
        Line line3 = new Line(C, D);
        Line line4 = new Line(D, A);

        // Triangle
        Line line1T = new Line(A1, B1);
        Line line2T = new Line(B1, C1);
        Line line3T = new Line(C1, A1);

        ImageT rectImage = new ImageT(120, 120, "rect");
        rectImage.addLine(line1);
        rectImage.addLine(line2);
        rectImage.addLine(line3);
        rectImage.addLine(line4);

        ImageT triImage = new ImageT(120, 120, "tri");
        triImage.addLine(line1T);
        triImage.addLine(line2T);
        triImage.addLine(line3T);

        FormesSimple morpher = new FormesSimple(rectImage, triImage);
        morpher.generateFrames(10);

        List<ImageT> generatedFrames = morpher.getFrames();

        for (int t = 0; t < generatedFrames.size(); t++) 
        {
            ImageT frame = generatedFrames.get(t);
            System.out.println("Frame " + t + ":");
            for (int i = 0; i < frame.getLines().size(); i++) 
            {
                Line line = frame.getLines().get(i);
                Point start = line.getStart();
                Point end = line.getEnd();
                System.out.println("  Line " + (i + 1) + ":");
                System.out.println("    Start Point: (" + start.getPoint() + ")");
                System.out.println("    End Point: (" + end.getPoint() + ")");
            }
        }
    }*/
}

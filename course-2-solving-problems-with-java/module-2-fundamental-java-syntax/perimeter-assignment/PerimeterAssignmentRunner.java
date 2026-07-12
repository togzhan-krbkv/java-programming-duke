import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner 
{
    public double getPerimeter(Shape s)
    {
        double totalPerimeter = 0.0;
        Point prevPoint = s.getLastPoint();
        for (Point currPoint : s.getPoints())
        {
            totalPerimeter += prevPoint.distance(currPoint);
            prevPoint = currPoint;
        }
        return totalPerimeter;
    }

    public int getNumPoints(Shape s)
    {
        int numPoints = 0;
        for (Point currPoint : s.getPoints())
        {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s)
    {
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s)
    {
        double largestSide = 0.0;
        Point prevPoint = s.getLastPoint();
        for (Point currPoint : s.getPoints())
        {
            double currDist = prevPoint.distance(currPoint);
            if (currDist > largestSide)
            {
                largestSide = currDist;
            }
            prevPoint = currPoint;
        }
        return largestSide;
    }

    public double getLargestX(Shape s)
    {
        double largestX = 0.0;
        for (Point currPoint : s.getPoints())
        {
            double currX = currPoint.getX();
            if (currX > largestX)
            {
                largestX = currX;
            }
        }
        return largestX;
    }

    // Returns the shape file with the largest perimeter among the selected files
    private File findFileWithLargestPerimeter(Iterable<File> files)
    {
        File fileWithLargestPerimeter = null;
        double largestPerimeter = 0.0;
        for (File f : files)
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter)
            {
                largestPerimeter = perimeter;
                fileWithLargestPerimeter = f;
            }
        }
        return fileWithLargestPerimeter;
    }

    public double getLargestPerimeterMultipleFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        File largest = findFileWithLargestPerimeter(dr.selectedFiles());
        if (largest == null)
        {
            return 0.0;
        }
        return getPerimeter(new Shape(new FileResource(largest)));
    }

    public String getFileWithLargestPerimeter()
    {
        DirectoryResource dr = new DirectoryResource();
        File largest = findFileWithLargestPerimeter(dr.selectedFiles());
        return largest == null ? null : largest.getName();
    }

    public void testPerimeterMultipleFiles()
    {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter()
    {
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + fileWithLargestPerimeter);
    }

    // Creates a triangle to sanity-check the other methods
    public void triangle()
    {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints())
        {
            System.out.println(p);
        }
        System.out.println("perimeter = " + getPerimeter(triangle));
    }

    public void testPerimeter()
    {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);

        System.out.println("perimeter = " + getPerimeter(s));
        System.out.println("num points = " + getNumPoints(s));
        System.out.println("average length = " + getAverageLength(s));
        System.out.println("largest side = " + getLargestSide(s));
        System.out.println("largest x = " + getLargestX(s));

        testFileWithLargestPerimeter();
        testPerimeterMultipleFiles();
    }

    public void printFileNames()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            System.out.println(f);
        }
    }

    public static void main(String[] args)
    {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}

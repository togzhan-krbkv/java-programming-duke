// Part 1: three-band color filter (red, green, blue by horizontal position)

var image = new SimpleImage("hilton.jpg");
print(image);
var width = image.getWidth();
for (var pixel of image.values())
{
    if (pixel.getX() < (1 / 3) * width)
    {
        pixel.setRed(255);
    }
    else if (pixel.getX() > (2 / 3) * width)
    {
        pixel.setBlue(255);
    }
    else
    {
        pixel.setGreen(255);
    }
}
print("Three-band filter: ", image);

// Part 2: swap the red and green channels

function swapRedGreen(pixel)
{
    var red = pixel.getRed();
    var green = pixel.getGreen();
    pixel.setRed(green);
    pixel.setGreen(red);
}

var image = new SimpleImage("eastereggs.jpg");
print(image);
for (var pixel of image.values())
{
    swapRedGreen(pixel);
}
print("Red/Green swapped: ", image);

// Part 3: recolor every non-white pixel to yellow

function blueDevilYellow(pixel)
{
    // Skip pixels that are already pure white
    if (pixel.getRed() != 255 && pixel.getBlue() != 255 && pixel.getGreen() != 255)
    {
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(0);
    }
}

var image = new SimpleImage("duke_blue_devil.png");
print(image);
for (var pixel of image.values())
{
    blueDevilYellow(pixel);
}
print("Yellow devil: ", image);

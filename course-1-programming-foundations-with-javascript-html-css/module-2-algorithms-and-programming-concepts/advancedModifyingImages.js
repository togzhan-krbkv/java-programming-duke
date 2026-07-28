// Part 1: green screen effect, replacing green pixels with a background image

var fgImage = new SimpleImage("drewRobert.png");
var bgImage = new SimpleImage("Dinos.png");
var output = new SimpleImage(fgImage.getWidth(), fgImage.getHeight());

for (var pixel of fgImage.values())
{
    if (pixel.getGreen() > pixel.getBlue() + pixel.getRed())
    {
        // pixel is green, replace with the background image at the same position
        var x = pixel.getX();
        var y = pixel.getY();
        var bgPixel = bgImage.getPixel(x, y);
        output.setPixel(x, y, bgPixel);
    }
    else
    {
        output.setPixel(pixel.getX(), pixel.getY(), pixel);
    }
}
print("Green screen: ", output);

// Part 2: color each quadrant of a blank image differently

var img = new SimpleImage(200, 200);
for (var px of img.values())
{
    var x = px.getX();
    var y = px.getY();
    if (x < img.getWidth() / 2 && y < img.getHeight() / 2)
    {
        px.setRed(255);
    }
    if (y > img.getHeight() / 2 && x > img.getWidth() / 2)
    {
        px.setBlue(255);
    }
    else if (x > img.getWidth() / 2)
    {
        px.setGreen(255);
    }
    else if (x < img.getWidth() / 2 && y > img.getHeight() / 2)
    {
        px.setRed(255);
        px.setBlue(255);
    }
}
print(img);

// Part 3: add a solid black border around an image

function setBlack(pixel)
{
    pixel.setRed(0);
    pixel.setBlue(0);
    pixel.setGreen(0);
    return pixel;
}

function addBorder(image, thickness)
{
    var width = image.getWidth();
    var height = image.getHeight();
    for (var pixel of image.values())
    {
        var x = pixel.getX();
        var y = pixel.getY();
        if (x < thickness || y < thickness ||
            (x > thickness && x >= width - thickness) ||
            (y > thickness && y >= height - thickness))
        {
            image.setPixel(x, y, setBlack(pixel));
        }
    }
}

var image = new SimpleImage("smallpanda.png");
var thickness = 10;
addBorder(image, thickness);
print(image);

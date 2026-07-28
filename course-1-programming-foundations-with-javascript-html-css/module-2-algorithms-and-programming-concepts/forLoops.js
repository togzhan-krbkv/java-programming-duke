// Using for loops to set pixel colors across an image

var image = new SimpleImage(200, 200);
print(image);

// Yellow
for (var pixel of image.values())
{
    pixel.setRed(255);
    pixel.setGreen(255);
    pixel.setBlue(0);
}
print("Yellow: ", image);

// Magenta
for (var pixel of image.values())
{
    pixel.setRed(255);
    pixel.setGreen(0);
    pixel.setBlue(255);
}
print("Magenta: ", image);

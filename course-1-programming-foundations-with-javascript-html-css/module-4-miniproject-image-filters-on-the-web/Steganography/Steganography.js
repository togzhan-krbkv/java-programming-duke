function clearbits(pixelValue)
{
    var x = Math.floor(pixelValue / 16) * 16;
    return x;
}

// Clears the lower 4 bits of every channel in the cover image, leaving
// room to embed the hidden image's data in those bits
function chop2Hide(start)
{
    for (var px of start.values())
    {
        px.setRed(clearbits(px.getRed()));
        px.setGreen(clearbits(px.getGreen()));
        px.setBlue(clearbits(px.getBlue()));
    }
    return start;
}

// Shifts the hidden image's values down so they fit in the lower 4 bits
function shift(hide)
{
    for (var px of hide.values())
    {
        px.setRed(px.getRed() / 16);
        px.setGreen(px.getGreen() / 16);
        px.setBlue(px.getBlue() / 16);
    }
    return hide;
}

// Combines the cleared cover image and the shifted hidden image into one,
// so the hidden image's data is encoded in the lower 4 bits of each pixel
function combine(start, hide)
{
    var res = new SimpleImage(start.getWidth(), start.getHeight());
    for (var px of res.values())
    {
        var x = px.getX();
        var y = px.getY();
        var startPixel = start.getPixel(x, y);
        var hidePixel = hide.getPixel(x, y);
        px.setRed(startPixel.getRed() + hidePixel.getRed());
        px.setGreen(startPixel.getGreen() + hidePixel.getGreen());
        px.setBlue(startPixel.getBlue() + hidePixel.getBlue());
    }
    return res;
}

var start = new SimpleImage("duke_blue_devil.png");
var hide = new SimpleImage("pixabayhands.jpg");
start = chop2Hide(start);
hide = shift(hide);
var res = combine(start, hide);
print(res);

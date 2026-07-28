var fgImage = null;
var bgImage = null;
var fgCanvas;
var bgCanvas;
var greenThreshold = 230;

function loadForegroundImage()
{
    fgCanvas = document.getElementById("can1");
    var fileInput = document.getElementById("fgfile");
    var fileName = fileInput.value;
    alert("You chose: " + fileName);
    fgImage = new SimpleImage(fileInput);
    fgImage.drawTo(fgCanvas);
}

function loadBackgroundImage()
{
    bgCanvas = document.getElementById("can2");
    var fileInput = document.getElementById("bgfile");
    var fileName = fileInput.value;
    alert("You chose: " + fileName);
    bgImage = new SimpleImage(fileInput);
    bgImage.drawTo(bgCanvas);
}

function GreenScreen()
{
    // Check that both images are loaded before continuing
    if (fgImage == null || !fgImage.complete())
    {
        alert("Foreground image not loaded");
        return;
    }
    if (bgImage == null || !bgImage.complete())
    {
        alert("Background image not loaded");
        return;
    }

    clearCanvas();
    var compositeimage = new SimpleImage(fgImage.getWidth(), fgImage.getHeight());
    for (var pixel of fgImage.values())
    {
        var x = pixel.getX();
        var y = pixel.getY();
        if (pixel.getGreen() > greenThreshold)
        {
            var bgPixel = bgImage.getPixel(x, y);
            compositeimage.setPixel(x, y, bgPixel);
        }
        else
        {
            compositeimage.setPixel(x, y, pixel);
        }
    }
    compositeimage.drawTo(fgCanvas);
}

function clearCanvas()
{
    var ctx1 = fgCanvas.getContext("2d");
    var ctx2 = bgCanvas.getContext("2d");
    ctx1.clearRect(0, 0, fgCanvas.width, fgCanvas.height);
    ctx2.clearRect(0, 0, bgCanvas.width, bgCanvas.height);
}

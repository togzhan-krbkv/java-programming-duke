# Module 3: JavaScript for Web Pages

In this module I built on HTML, CSS, and JavaScript skills to create 
interactive web pages using event-driven programming, connecting user 
actions like clicks, color pickers, and file uploads to dynamic page 
updates, culminating in an interactive green screen image tool.

## Topics covered

- Buttons and divs, responding to click events
- Changing CSS classes and page content interactively via JavaScript
- HTML5 Canvas: drawing shapes, text, and colors
- Handling different input types (color picker, range slider) and their events
- Uploading and displaying an image on a canvas
- Processing image pixel data: grayscale conversion and a green screen effect

## Projects

- [ButtonsWithDivs](./ButtonsWithDivs): a button that triggers a confirm 
  dialog and responds differently depending on the user's choice.

- [Change Pages Interactively](./Change%20Pages%20Interactively): buttons 
  that change the CSS class and the text content of page elements.

- [Canvas](./Canvas): three tasks exploring the HTML5 canvas element, 
  changing canvas background colors, and drawing shapes and text onto a 
  canvas with the 2D drawing context.

- [Inputs and Events](./Inputs%20and%20Events): a color picker and a 
  range slider, using their change/input events to update a canvas's 
  color and draw a resizable square.

- [UploadDisplayImage](./UploadDisplayImage): uploading an image file 
  from the user's computer and displaying it on a canvas.

- [ConvertToGrayscale](./ConvertToGrayscale): uploading an image and 
  converting it to grayscale by averaging each pixel's red, green, and 
  blue values.

- [GreenScreenOnline](./GreenScreenOnline): an interactive green screen 
  tool that composites a foreground and background image, replacing 
  pixels above a green threshold with the background image at the same 
  position.

## Development environment

Most of this module was built and tested in CodePen. The image-processing 
exercises (UploadDisplayImage, ConvertToGrayscale, GreenScreenOnline) use 
Duke's SimpleImage.js library, loaded directly from Duke's course server, 
to read and manipulate image pixel data on an HTML canvas.

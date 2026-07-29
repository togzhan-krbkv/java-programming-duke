# Module 4: MiniProject, Image Filters on the Web

This capstone project for the course brings together HTML, CSS, and 
JavaScript into a working website that lets a user upload an image and 
apply different filters to it, plus a separate exploration of image based 
steganography (hiding one image's data inside another).

## What the project does

- **MiniProject**: a page where the user uploads an image and applies one 
  of several filters, each rendered onto a canvas: grayscale, an RGB 
  three-band filter, a custom rainbow gradient filter based on pixel 
  position and brightness, a red duotone filter, and a random-offset blur 
  filter. A reset button restores the original image.

- **Steganography**: hides one image's data inside another by clearing 
  the lower 4 bits of a cover image and embedding a shifted-down version 
  of a second image in those bits, so the hidden image's data travels 
  inside the cover image's pixel values.

## Development environment

Both projects use Duke's SimpleImage.js library for reading and drawing 
image pixel data. MiniProject runs as a standalone web page (HTML, CSS, 
and JS); Steganography is a script meant to run in the DukeLearnToProgram 
JavaScript environment.

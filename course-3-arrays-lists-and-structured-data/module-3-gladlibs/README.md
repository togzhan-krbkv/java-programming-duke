# Module 3: GladLib. Stories from Templates

In this module I built a randomly generated "mad lib" style story generator, 
along with two supporting exercises (word frequency counting and character 
analysis) that introduced the ArrayList techniques needed for GladLibs, then 
refactored the whole thing to use a HashMap for more flexible, less brittle 
design.

## Topics covered

- Programming a word frequency counter to analyze any input text file
- Selecting and substituting words from a list into a document template, using both ArrayList and HashMap
- Creating and reading in new word lists to extend a template-based program
- Recognizing brittle code (many near-duplicate if-statements per category) and improving it with more flexible, object-oriented design
- Using HashMap to map categories to word lists, and to map words to the files they appear in

## Projects

- [ TellingARandomStory](./TellingARandomStory) — "Telling a Random Story". 
  Includes two warm-up exercises with ArrayList (finding the most frequent 
  word in a file, and finding the characters with the most speaking parts 
  in a Shakespeare play), followed by `GladLib.java`: the actual mad-lib 
  story generator that substitutes template placeholders like 
  `<noun>`/`<adjective>` with random words, never repeating a word already 
  used in the same story.

- [ UsingAndImprovingGladLibs](./UsingAndImprovingGladLibs) — "Using and 
  Improving GladLibs". Includes two warm-up exercises with HashMap 
  (counting DNA codons across reading frames, and mapping words to the 
  files they appear in), followed by `GladLibMap.java`: a refactored 
  version of GladLib that replaces nine separate ArrayList fields (and a 
  long if-statement chain) with a single HashMap mapping category names to 
  word lists — making it easy to add new categories without touching the 
  substitution logic.

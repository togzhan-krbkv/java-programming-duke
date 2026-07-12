# Module 3: Strings in Java

This module opens with a talk from Duke's Center for Genomic and 
Computational Biology on identifying genes in a strand of DNA, a real-world 
problem used throughout the module to teach how to work with strings in Java.

## Topics covered

- Core methods of the Java String class
- Conditionals, for loops, and while loops
- Finding patterns in string data to design an algorithm
- Separation of concerns in program design
- The StorageResource iterable for storing intermediate results
- Using Java documentation to work with unfamiliar packages and classes

Most of this module consisted of videos and practice quizzes, including some 
debugging exercises. The three graded coding assignments are included below.

## Projects

- [→ StringsFirstAssignments](./StringsFirstAssignments) 
  Given a DNA string, finds the first gene by locating a start codon (ATG) 
  and the nearest in-frame stop codon (TAA), then extends the approach to 
  work with arbitrary start/stop codons and to parse links out of a web page.

- [→ StringsSecondAssignments](./StringsSecondAssignments) 
  Extends the single-gene search to find every gene in a DNA string, 
  checking all three stop codons (TAA, TGA, TAG) and counting how many 
  genes and substring matches occur.

- [→ StringsThirdAssignments](./StringsThirdAssignments) 
  Applies the gene-finding logic to a real DNA dataset, storing all genes 
  found in a StorageResource and analyzing them: counting CTG occurrences, 
  computing each gene's C-G ratio, and identifying the longest gene and 
  genes above a length/ratio threshold.

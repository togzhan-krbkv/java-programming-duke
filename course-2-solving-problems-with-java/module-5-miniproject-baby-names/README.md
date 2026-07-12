# Module 5: MiniProject Baby Names

This capstone mini-project for the course ties together file I/O, CSV 
parsing, and algorithm design skills from the earlier modules. Using US 
baby name data by year (1880–2014), the program answers questions about 
name popularity and rank over time.

## What the program does

- **Total births**: counts total births, and births/name-count broken down by gender, for a given year's file
- **getRank**: finds the popularity rank of a given name and gender in a given year
- **getName**: finds the name at a given rank, gender, and year
- **whatIsNameInYear**: finds what name would have the same popularity rank in a different year (e.g., "Isabella born in 2012 would be Sophia if she was born in 2014")
- **yearOfHighestRank**: finds the year, across a selected range of files, in which a given name reached its highest (best) rank
- **getAverageRank**: computes the average rank of a name and gender across a selected range of files
- **getTotalBirthsRankedHigher**: computes the total number of births of all names ranked higher than a given name, for the same year and gender

## Data

The full dataset (US baby names by year, 1880–2014) and a small set of 
test files (`yob2012short.csv`, `yob2013short.csv`, `yob2014short.csv`) 
are available at 
[dukelearntoprogram.com/course2/data](https://www.dukelearntoprogram.com/course2/data/). 
The full dataset is too large to include in this repository.

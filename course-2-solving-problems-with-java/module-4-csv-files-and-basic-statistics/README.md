# Module 4: CSV Files and Basic Statistics in Java

In this module I learned to read, parse, and analyze tabular data stored in 
CSV files using the open-source Apache Commons CSV library, including 
working across multiple CSV files in a directory.

## Topics covered

- Using the Apache Commons CSV library in Java programs
- Accessing data from one or many CSV files
- Converting strings to numbers
- Using `null` in Java to represent "nothing" (e.g., no maximum/minimum found yet)
- Designing algorithms to answer questions about CSV data
- Basic statistics across multiple CSV files: maximums, minimums, averages

## Projects

- [ParsingExportData](./ParsingExportData) — Which Countries Export? 
  Parses a CSV of country export data to look up a specific country's 
  exports, find countries exporting a given pair of products, count how 
  many countries export a given product, and list countries above a value 
  threshold.

- [ParsingWeatherData](./ParsingWeatherData) — Weather Data
  Parses daily weather CSV files (temperature and humidity readings) to 
  find the hottest and coldest recorded hours, the file containing the 
  coldest day, the lowest humidity reading, and average temperature 
  (including average temperature filtered by high humidity) — both within 
  a single file and across an entire directory of files.

## Data

Export data (`exportdata.csv`, `exports_small.csv`) is included in the 
ParsingExportData folder. Weather data is a multi-year daily dataset too 
large to include here; the full dataset is available at 
[dukelearntoprogram.com/course2/data/nc_weather](https://www.dukelearntoprogram.com/course2/data/nc_weather/).

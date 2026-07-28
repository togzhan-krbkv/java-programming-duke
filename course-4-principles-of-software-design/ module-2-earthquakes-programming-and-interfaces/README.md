# Module 2: Earthquakes, Programming and Interfaces

In this module I worked with a real earthquake dataset: parsing it, 
searching through it, and filtering it based on different criteria, using 
both plain methods and a Filter interface.

## Topics covered

- Programs with multiple classes and ArrayLists of class types
- Finding the maximum value in an ArrayList (selection style search)
- Using an interface (Filter) to search through data
- Implementing interfaces with method signatures
- Combining several filters together (MatchAllFilter)

## Projects

- [SearchingEarthquakeData](./SearchingEarthquakeData): parses the 
  earthquake data and searches it directly, finding the closest quakes to 
  a location, the largest quakes by magnitude, and filtering by magnitude, 
  distance, depth, or a phrase in the title.

- [FilteringData](./FilteringData): rewrites the filtering logic using a 
  Filter interface, with separate classes for each filter type 
  (MinMagFilter, MagnitudeFilter, DepthFilter, DistanceFilter, 
  PhraseFilter) and a MatchAllFilter that combines multiple filters 
  together.
eed format. All code was 
written and tested in BlueJ.

# Module 3: Earthquakes, Sorting Algorithms

In this module I continued working with real earthquake data, this time 
focusing on sorting: implementing selection sort and bubble sort from 
scratch, then using Java's built in Collections.sort with both natural 
ordering (Comparable) and custom ordering (Comparator).

## Topics covered

- Implementing several sorting algorithms from scratch, in place
- Using efficient prebuilt sorting classes (Collections.sort)
- Modifying a class's compareTo method to choose how objects are ordered
- Writing classes that implement the Comparator interface for interchangeable sorting criteria

## Projects

- [ ImplementingSelectionSort](./ImplementingSelectionSort): implements 
  selection sort and bubble sort in place, by magnitude and by depth, 
  including versions that stop early once the list is already sorted, and 
  a version that sorts by building a new list with two ArrayLists.

- [ SortingAtScale](./SortingAtScale): sorts earthquake data using 
  Collections.sort, both with QuakeEntry's natural ordering (compareTo) 
  and with several Comparator implementations: by magnitude, by distance 
  from a location, by title with depth as a tiebreaker, and by the last 
  word in the title with magnitude as a tiebreaker.

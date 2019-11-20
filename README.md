# ttc-subway
A simple implementation of TTC Subway system.

Requirements:

Given the following subway map, break it down into individual components and model these components using C# or Java classes:

 

https://www.ttc.ca/Subway/interactive_map/interactive_map.jsp#

 

When building your data model, try to build it with the following in mind:

 

- Some method should exist that can tell a caller when the next train will arrive at a specific station, given a time and direction (north, south, east, west).
- A simple schedule should be implemented, that can be looked up by station, to determine the times at which trains will arrive at that station.
- As noted above, the data model should account for direction (north, south, east, west) and handle stations that have intersecting lines.
- Data can be stored in memory, there is no need for a database.

Please include a simple main method that can demonstrate the correctness of the method described in #1 above.

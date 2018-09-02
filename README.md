# Intersection finder

This app find intersections of rectangles.

# Requirements

* JDK 8 (tested with openJDK)

# Building

To build the app, use the distZip task via the gradle wrapper:

```
./gradlew distZip
```

The build artifact is build/distributions/intersection-finder.zip.

# Run

To run it, unzip and execute intersection-finder/bin/intersection-finder. Provide the path to the input file.

```
cd build/distributions
unzip intersection-finder.zip
cd intersection-finder
./bin/intersection-finder input.json
```

# Input file format

The input file must be a JSON file containing an array named 'rects'. Each item must have the fields x, y, w and h.

```
{
    "rects": [
        {"x": 10, "y": 10, "w": 3, "h": 7 },
        {"x": 5, "y": 5, "w": 6, "h": 10 }
    ]
}
```

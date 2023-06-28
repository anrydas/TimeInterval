## Das Time Intervals
### _Class to measure time intervals during program or class execution_
[![https://img.shields.io/badge/Java-8-white](https://img.shields.io/badge/Java-8-white)](https://www.java.com/)

### 📃 Features
- Lightweight and easy to use
- All functionality in one class

### 📌 Using
Just add [**DasTimeInterval** class](src/das/tools/DasTimeInterval.java) into project.

Follow the example in [**TimeInterval** class](src/das/tools/TimeInterval.java):
- Create an instance of DasTimeInterval class
- use **startInterval** method to start measuring
- use **stopInterval** method to stop it
- to get results use **toString** method


After launch example code the output is:
```
Time Intervals:
[001] First, μs: 2232000 μs
[002] Second, ms: 2001 ms
[003] Third, ns: 2000000000 ns
[004] Fours, s: 2 s
[005] [!WARN] Second, ms: -200 The Label 'Second, ms' have been already stopped
[006] [!ERROR] Wrong Label: -100 The Label 'Wrong Label' was not found
[007] Total, s: 10701 ms
```

###### _Made by -=:dAs:=-_
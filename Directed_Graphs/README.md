# Cycles of Directed Graphs

Cycles of Directed Graphs is a Java program to read the number of nodes in a provided directed graph and its corresponding adjacency matrix, one row at a time. The program uses recursion to find and list all possible non-looping paths between all possible pairs of nodes.

→ `To do so, it reads a user-provided adjacency matrix input file and processes each respective matrices and graph until the end of the file.` 

## Requirements

[Java SE Development Kit 15.0.1](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)

Set of specifically formatted input files: 
* _"PathsGraphInput.txt"_
* _"StudentGeneratedInput.txt"_

## Build Tested

IntelliJ IDEA 2020.3 Beta (Community Edition)
* Build #IC-203.5784.10, built on November 10, 2020
* Runtime version: 11.0.9+11-b1145.10 amd64
* VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
* Windows 10 10.0
* GC: ParNew, ConcurrentMarkSweep
* Memory: 1981M
* Cores: 8

## Usage

1)	Open the project in **IntelliJ IDEA** > _build_ & run the Driver Class.


```java
public class Driver {
    @SuppressWarnings("InstantiationOfUtilityClass")
    // If the user provides faulty input, an IOException takes care of bad I/O
    public static void main(String[] args) throws IOException {

    ...
        ...
            ...

            // Executes the read_input function and subsequent logic
            new read_input(input_file, output_file);
```

2)	Either provide the input & output files as command-line arguments when executing the Driver Class or later when the conditional logic prompts **<!>**

```
    >>  java Driver

        <!> Oops, you didn't provide the required (two) command-line arguments! <!>

        No worries, go ahead and type the input file name: █
```

3)	According to the lab specifications, the program uses recursion to find and list all possible _non-looping paths_ between all possible pairs of nodes and saves the results in the stated output file.

```java
    if (tail != head)
    {
        recursively_find_path(root, head, root[tail], paths);
        paths.append(" → ").append(tail + 1);
    }
```

4)	For added convenience, pre-run output files _"PathsGraphOutput.txt"_ and _"StudentGeneratedOutput.txt"_ are provided based on the original lab, and the student-generated inputs.

```
 •••=================================================••
•••>     Adjacency Matrix for (4) directed nodes     <•••
  ••=================================================•••

	  _1_2_3_4_
	1| 0 1 1 0 
	2| 1 1 1 1 
	3| 1 0 0 0 
	4| 1 1 0 1 
```

## Contributing

Independent academic work, any external contribution, is **prohibited!**

* Some future release may welcome pull requests, and for significant changes, may also allow open discussion.

## License
[MIT](https://choosealicense.com/licenses/mit/)
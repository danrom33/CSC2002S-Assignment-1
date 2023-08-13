# CSC2002S Assignment - Parallel and Concurrent Programming Assignment 1

## Assignment Details

This repository contains my solution for the Parallel Programming assignment as part of the CSC2002S course at UCT. The assignment involves parallelizing a Monte Carlo function minimization algorithm using Java's Fork/Join framework and benchmarking the parallel solution against the serial algorithm.

## Files and Structure

- `TerrainArea.java`: Represents the terrain whose minimum height is to be found.
- `SearchParallel.java`: Parallelizes.
- `MonteCarloMinimizationParallel.java`: Main class initializing and coordinating the parallel execution.
- `Makefile`: Compilation instructions for the Java files.
- `README.md`: This file.
- `report.pdf`: Assignment report containing methods, results, and conclusions.

## Running the Program

1. Clone this repository to your local machine using `git clone https://github.com/danrom33/CSC2002S-Assignment-1/new/main/ParallelAssignment2023`.
2. Navigate to the cloned directory using cd.
3. Compile the Java files using the provided Makefile: `make`.
4. Run the program with follinwg command: make run ARGS="[rows] [columns] [xmin] [xmax] [ymin] [ymax] [search_density]"`.
  Note:
   rows: number of rows in the discrete grid
   columns: number of columns in the discrete grid (int)
   xmin: minimum x coordinate of terrain (int)
   xmax: maximum x coordinate of terrain (double)
   ymin: minimum y coordinate of terrain (double)
   ymax: maximum y coordinate of terrain (double)
   search_density: number of serahces per grid position (double)

## Assignment Report

For detailed information about my approach, implementation, validation, benchmarking, and conclusions, please refer to the `PCP_Assignment_1_Report.pdf` file in this repository.

## Contact Information

If you have any questions or need further clarifications, feel free to contact me at RMXDAN002@myuct.ac.za.


# CSV Comparison Framework

## Overview
This framework compares two CSV files and reports the differences, row by row and column by column. 
If the files are identical, it reports that as well.

## Prerequisites
- Java 8 or higher
- Maven

## Build
```bash
mvn clean package
```
## Usage
```bash
java -jar target/csv-comparator-1.0-SNAPSHOT.jar <file1.csv> <file2.csv>
```
## Example:
```Bash
java -jar target/cvs-comparison-1.0-SNAPSHOT.jar test-data/identical1.csv test-data/identical2.csv
```

## Sample Test Data
Place CSV files under a `test-data/` folder:
- `identical1.csv` and `identical2.csv`: identical content
- `mismatch.csv`: same number of rows but one value differs
- `missing-row.csv`: one file has an extra or missing row

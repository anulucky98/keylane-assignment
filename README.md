# Triangle Classifier

A Java application that determines the type of a triangle based on the lengths of its three sides.

---

## Problem Statement

Given three side lengths, classify the triangle as:
- **Equilateral**:  All three sides are equal
- **Isosceles**:  Exactly two sides are equal
- **Scalene**: All three sides are different

---

## Prerequisites
- Java **11** or higher
- Maven **3.8+** for building the project
- Docker **20+** (optional, for containerized execution)

---

## Assumptions
Triangle is a data object, validation is handled externally by design.

## Build and Run

### 1. Build the Project
```
git clone https://github.com/anulucky98/keylane-assignment
cd keylane-assignment
mvn clean package
````

### 2. Run via Command-Line Arguments
```
java -jar target/keylane-assignment-1.0.jar 3 4 5

Example output:
Result: SCALENE
All three sides are different
```

### 3. Run Interactively
```
java -jar target/keylane-assignment-1.0.jar

Example:
Enter the lengths of the triangle's three sides:
Side A: 5
Side B: 5
Side C: 5
Result: EQUILATERAL
All sides are equal
```

### 4. Run with Docker
```
BUILD IMAGE :
docker build -t keylane-assignment:v0.0.1

RUN THE IMAGE USING ARGS
docker run -it keylane-assignment:v0.0.1 2 2 3

RUN THE IMAGE IN AN INTERACTIVE MODE
docker run -it keylane-assignment:v0.0.1 

```

## Testing

### Run all unit tests using Maven:
```
mvn test 
```


## Usage Examples

```
Enter the lengths of the three sides:
Side A: 5
Side B: 5
Side C: 5
Result: EQUILATERAL
All sides are equal

Enter the lengths of the three sides: 
Side A: 5
Side B: 5
Side C: 7
Result: ISOSCELES
Exactly two sides are equal

Enter the lengths of the three sides: 
Side A: 3
Side B: 4
Side C: 5
Result: SCALENE
All three sides are different

Enter the lengths of the three sides: 
Side A: -1
Side B: 4
Side C: 5
Error: Side A must be greater than zero. Got: -1.00

Enter the lengths of the triangle's three sides:
Side A: 1
Side B: 2
Side C: 3
Error: Triangle inequality violated. 
 SideA(1.00) + SideB(2.00) must be > 3.00

```
Owen Radcliffe
677414365

Section I
Open source code in an IDE and build the program, then run it and enter input in the terminal window.

Section II
Equality-based query is working both with and without an index.
Range-based query is working both with and without an index.
Inequality-based query is working.

Section III
Design decisions:
- Implemented IndexEngine and QueryEngine classes to create indexes and perform queries.
- Implemented RecordLocation object to store the file number and byte offset of a record
- Used LinkedLists in both hash-based and array-based indexes to allow the storage of multiple records having the same random value
- Kept the LinkedLists in the indexes sorted by file number to minimize access I/Os
    - i.e. if three records from files 3, 61, 3 all have the same random value, they would be stored 3, 3, 61 so when that value is queried,
      only two reads are performed instead of three
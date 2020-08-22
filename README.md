# Priority-based-Scheduling

Rate-Monotonic Scheduling and Earliest-Deadline-First in C++ and Java (Naive implementations)

In RMS, the priorities are assigned by rank order of period, the shortest period has the highest rank.

EDF updates the priorities of processes at every period of time (1 time unit in our programs). 
The priorities will be assigned in order of deadline. 

/* Example input */

3       // three processes

1 4     // execution time : 1, period : 4

2 5

4 10

/* The output will depend on our inputs, of course! */

Case 1: The combinations of processes can require more than 100% of the available CPU cycles.
        We will receive an error.

Case 2: With RMS, there are some inputs which lead to missing process's dealine. 
The output will be written in a line.

Case 3: Everything works fine and we get the execution orders by RMS or EDF scheduling.

Email: khang.vt183561@sis.hust.edu.vn

OSCs 20192, HUST

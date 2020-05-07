# Priority-based-Scheduling

Rate-Monotonic Scheduling and Earliest-Deadline-First in C++ and Java 

In contrast to RMS, EDF updates the priorities of processes at every period of time (1 time unit in our programs). 
And the priorities will be assigned in order of deadline. 

Example input :
3
1 4
2 5
4 10

The output will depend on our inputs, of course!
Case 1: The combinations of processes can require more than 100% of the available CPU cycles.
        We will receive an error.
Case 2: With RMS, there are some inputs which lead to missing process's dealine.
Case 3: Everything works fine and we get the execution orders by RMS or EDF scheduling.

Alert: No one has verified my code yet, so mistakes can be inevitable! 
And by the way, I have not written any README before, and I really want to receive any corrections and suggestions!

Email: khang.vt183561@sis.hust.edu.vn

OSCs 20192, HUST

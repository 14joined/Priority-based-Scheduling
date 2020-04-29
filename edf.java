package EDF;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class edf {
    public static final int N_PROCESSES = 10;
    public static final int N = 10003;
    public static int lcm = 1, n;
    public static double CPU_utilization = 0.0;
    public static process[] processes = new process[N_PROCESSES];
    public static void main(String args[]) {
        PriorityQueue<process> q = new PriorityQueue<process>(N, new Comparator<process>() {
            @Override
            public int compare(process p1, process p2) {
                if(p1.deadline > p2.deadline)
                    return 1;
                else if(p1.deadline < p2.deadline)
                    return -1;
                return 0;
            }
        });
        // q.add(99);
        // q.add(), q.remove(), q.peek()
        int [] Executed_processes = new int[N];
        int [] quantity = new int[N_PROCESSES];
        int [] qpet = new int [N_PROCESSES];

        System.out.print("Enter number of processes: ");
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        for(int i = 1; i <= n; ++i) {
            int x = s.nextInt();
            int y = s.nextInt();
            lcm = lcm*y/GCD(lcm, y);
            CPU_utilization += 1.0*x/y;
            processes[i] = new process(i, x, y, 0);
        }

        if(CPU_utilization > 1) {
            System.out.println("Impossible to create valid schedule!");
        } else {
            int count = 0;
            for(int i = 0; i <= lcm; ++i) {
                for(int j = 1; j <= n; ++j) {
                    int remainder = i % processes[j].period;
                    if(remainder == 0) {
                        if(i!=0 && quantity[j] != i/processes[j].period+1
                                && quantity[j] != i/processes[j].period) {
                            for(int k = 1; k <= i; ++k) {
                                System.out.print(Executed_processes[k] + " ");
                            }
                            System.out.println("\nEDF misses process's deadline at " + i + "!");
                        }
                        processes[j].deadline += processes[j].period;
                        for(int k = 1; k <= processes[j].execution_time; ++k) q.add(processes[j]);
                    }
                }

                if(checkQuantities(quantity, n)) {
                    prnt(Executed_processes, i);
                    return;
                }
                Executed_processes[++count] = q.peek().getId();
                ++qpet[q.peek().getId()];
                if(qpet[q.peek().getId()] == processes[q.peek().getId()].getExecution_time()) {
                    ++quantity[q.peek().getId()];
                    qpet[q.peek().getId()] = 0;
                }
                q.remove();
            }
        }
    }

    private static boolean checkQuantities(int[] quantity, int n) {
        for(int i = 1; i <= n; ++i) {
            if(quantity[i] != lcm/processes[i].period) return false;
        }
        return true;
    }

    private static int GCD(int a, int b) {
        if(b == 0) return a;
        return GCD(b, a%b);
    }

    private static void prnt(int A[], int i) {
        System.out.print("Execution orders by RMS: ");
        for(int j = 1; j <= i; ++j) {
            System.out.print(A[j] + " ");
        }
    }

    static class process {
        public int id;
        public int execution_time;
        public int period;
        public int deadline;

        public process() {
            id = 0;
            execution_time = 0;
            period = 0;
            deadline = 0;
        }

        public process(int id, int execution_time, int period, int deadline) {
            this.id = id;
            this.execution_time = execution_time;
            this.period = period;
            this.deadline = deadline;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getExecution_time() {
            return execution_time;
        }

        public void setExecution_time(int execution_time) {
            this.execution_time = execution_time;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public int getDeadline() {
            return deadline;
        }

        public void setDeadline(int deadline) {
            this.deadline = deadline;
        }
    }
}

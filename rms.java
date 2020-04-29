package RMS;

import java.util.PriorityQueue;
import java.util.Scanner;

public class rms {
    public static final int N_PROCESSES = 10;
    public static final int N = 10003;
    public static int lcm = 1, n;
    public static double CPU_utilization = 0.0;
    public static process [] processes = new process[N_PROCESSES];

    public static void main(String args[]) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();

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
            processes[i] = new process(x, y);
        }

        if(CPU_utilization > 1) {
            System.out.println("Impossible to create valid schedule!");
        } else {
            int count = 0;
            for(int i = 0; i <= lcm; ++i) {
                for(int j = 1; j <= n; ++j) {
                    int remainder = i % processes[j].getPeriod();
                    if(remainder == 0) {
                        if(i!=0 && quantity[j] != i/processes[j].getPeriod()+1
                            && quantity[j] != i/processes[j].getPeriod()) {
                            for(int k = 1; k <= i; ++k) {
                                System.out.print(Executed_processes[k] + " ");
                            }
                            System.out.println("\nRMS misses process's deadline at " + i + "!");
                        }
                        for(int k = 1; k <= processes[j].getExecution_time(); ++k) q.add(j);
                    }
                }

                if(checkQuantities(quantity, n)) {
                    prnt(Executed_processes, i);
                    return;
                }
                Executed_processes[++count] = q.peek();
                ++qpet[q.peek()];
                if(qpet[q.peek()] == processes[q.peek()].getExecution_time()) {
                    ++quantity[q.peek()];
                    qpet[q.peek()] = 0;
                }
                q.remove();
            }
        }
    }

    private static boolean checkQuantities(int[] quantity, int n) {
        for(int i = 1; i <= n; ++i) {
            if(quantity[i] != lcm/processes[i].getPeriod()) return false;
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

    public static class process {
        private int execution_time;
        private int period;

        public process() {
            execution_time = 0;
            period = 0;
        }

        public process(int execution_time, int period) {
            this.execution_time = execution_time;
            this.period = period;
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
    }
}

#include <iostream>
#include <algorithm>
#include <queue>
#define NPROCESSES 10
#define N 10003
using namespace std;

struct process {
    int execution_time;
    int period;
};
process processes[NPROCESSES];

priority_queue<int> q;
int Executed_processes[N], lcm = 1;
int quantity[NPROCESSES], qpet[NPROCESSES];

bool check_quantities(int *A, int n); 

int main() {
    freopen("file.in", "r", stdin);
    int n;
    double CPU_utilization = 0;
    //cout << "Enter the number of processes: "; 
    cin >> n; 
    for(int i = 1; i <= n; ++i) {
        int x, y;
        cin >> x >> y;
        lcm = lcm*y/__gcd(lcm, y);
        CPU_utilization += 1.0*x/y;
        processes[i].execution_time = x;
        processes[i].period = y;
    }

    if(CPU_utilization > 1) { cout << "Error! Require more than 100% of the available CPU cycles!"; return 0;}

    int count = 0, flag;
    for(int i = 0; i <= lcm; ++i) {
        for(int j = 1; j <= n; ++j) {
            int remainder = i % processes[j].period;
            if(remainder == 0) {
                if(i && quantity[j] != i/processes[j].period+1 && quantity[j] != i/processes[j].period) { 
                    for(int k = 1; k <= i; ++k) {
                        cout << Executed_processes[k] << " ";
                    }
                    cout << "\nRMS misses process's deadline at " << i << "!"; 
                    return 0; 
                }
                for(int k = 1; k <= processes[j].execution_time; ++k) q.push(-j); 
            }
        }
        Executed_processes[++count] = -q.top();
        ++qpet[-q.top()];
        if(qpet[-q.top()] == processes[-q.top()].execution_time) { 
            ++quantity[-q.top()];
            qpet[-q.top()] = 0;
        }
        q.pop();
        if(check_quantities(quantity, n)) { flag = ++i; break; }
    }
    cout << "Execution orders by RMS ";
    if(flag < lcm) {
        for(int i = 1; i <= flag; ++i) {
            cout << "\nP" << Executed_processes[i];
        }
        cout << "\nIdle at " << flag;
    } else {
        for(int i = 1; i <= flag; ++i) {
            cout << Executed_processes[i] << " ";
        }
    }

    return 0;
}

bool check_quantities(int *A, int n) {
    for(int i = 1; i <= n; ++i) {
        if(quantity[i] != lcm/processes[i].period) return false;
    }
    return true;
}

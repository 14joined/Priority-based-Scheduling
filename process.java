package RMS;

public class process {
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

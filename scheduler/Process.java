package scheduler;

// Store the information of one process.
public class Process {
    private String pid;
    private int burstTime;
    private int arrivalTime;
    private int priority;

    public Process(String pid, int burstTime, int arrivalTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }

    public String getPid() {
        return pid;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPriority() {
        return priority;
    }
}

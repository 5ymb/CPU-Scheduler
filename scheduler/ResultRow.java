package scheduler;

// Store the scheduling results for one process.
public class ResultRow {
    private Process process;
    private int completionTime;
    private int waitingTime;
    private int turnaroundTime;

    public ResultRow(Process process, int completionTime) {
        this.process = process;
        this.completionTime = completionTime;

        // Calculate turnaround time (TAT).
        this.turnaroundTime = completionTime - process.getArrivalTime();

        // Calculate waiting time (WT).
        this.waitingTime = turnaroundTime - process.getBurstTime();
    }

    public Process getProcess() {
        return process;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }
}

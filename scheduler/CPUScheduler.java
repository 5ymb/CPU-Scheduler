package scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

// This class contains the CPU scheduling algorithms.
public class CPUScheduler {

    // First Come, First Served.
    public static ArrayList<ResultRow> fcfs(ArrayList<Process> processes) {
        ArrayList<Process> order = new ArrayList<>(processes);
        order.sort(Comparator.comparingInt(Process::getArrivalTime));

        ArrayList<ResultRow> result = new ArrayList<>();
        int time = 0;

        for (Process p : order) {
            if (time < p.getArrivalTime()) {
                time = p.getArrivalTime();
            }

            time += p.getBurstTime();
            result.add(new ResultRow(p, time));
        }

        return result;
    }

    
    // Shortest Job First (non-preemptive).
    public static ArrayList<ResultRow> sjf(ArrayList<Process> processes) {
        ArrayList<Process> remaining = new ArrayList<>(processes);
        ArrayList<ResultRow> result = new ArrayList<>();
        int time = 0;

        while (!remaining.isEmpty()) {
            Process selected = null;

            for (Process p : remaining) {
                if (p.getArrivalTime() <= time &&
                    (selected == null ||
                     p.getBurstTime() < selected.getBurstTime())) {
                    selected = p;
                }
            }

            if (selected == null) {
                time++;
            } else {
                time += selected.getBurstTime();
                result.add(new ResultRow(selected, time));
                remaining.remove(selected);
            }
        }

        return result;
    }

    // Round Robin scheduling.
    public static ArrayList<ResultRow> roundRobin(
            ArrayList<Process> processes, int quantum) {

        ArrayList<Process> order = new ArrayList<>(processes);
        order.sort(Comparator.comparingInt(Process::getArrivalTime));

        int[] remaining = new int[order.size()];
        for (int i = 0; i < order.size(); i++) {
            remaining[i] = order.get(i).getBurstTime();
        }

        Queue<Integer> ready = new LinkedList<>();
        ArrayList<ResultRow> result = new ArrayList<>();

        int time = 0;
        int next = 0;
        int completed = 0;

        while (completed < order.size()) {
            // Add processes that have arrived.
            while (next < order.size() &&
                   order.get(next).getArrivalTime() <= time) {
                ready.add(next);
                next++;
            }

            if (ready.isEmpty()) {
                time = order.get(next).getArrivalTime();
                continue;
            }

            int current = ready.remove();
            int runTime = Math.min(quantum, remaining[current]);

            time += runTime;
            remaining[current] -= runTime;

            while (next < order.size() &&
                   order.get(next).getArrivalTime() <= time) {
                ready.add(next);
                next++;
            }

            if (remaining[current] == 0) {
                result.add(new ResultRow(order.get(current), time));
                completed++;
            } else {
                ready.add(current);
            }
        }

        return result;
    }

   
    // Priority scheduling (non-preemptive).
    public static ArrayList<ResultRow> priority(ArrayList<Process> processes) {
        ArrayList<Process> remaining = new ArrayList<>(processes);
        ArrayList<ResultRow> result = new ArrayList<>();
        int time = 0;

        while (!remaining.isEmpty()) {
            Process selected = null;

            for (Process p : remaining) {
                if (p.getArrivalTime() <= time &&
                    (selected == null ||
                     p.getPriority() < selected.getPriority())) {
                    selected = p;
                }
            }

            if (selected == null) {
                time++;
            } else {
                time += selected.getBurstTime();
                result.add(new ResultRow(selected, time));
                remaining.remove(selected);
            }
        }

        return result;
    }
}

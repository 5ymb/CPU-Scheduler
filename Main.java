import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import scheduler.CPUScheduler;
import scheduler.Process;
import scheduler.ResultRow;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Process> processes = new ArrayList<>();
    private static ArrayList<ResultRow> lastResults = new ArrayList<>();
    private static String lastAlgorithm = "";

    // Start the program and handle the menu.
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    enterProcesses();
                    break;
                case 2:
                    displayProcesses();
                    break;
                case 3:
                    runAlgorithmMenu();
                    break;
                case 4:
                    compareAlgorithms();
                    break;
                case 5:
                    showResults();
                    break;
                case 6:
                    resetData();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using CPU Scheduler!");
                    break;
                default:
                    System.out.println("Invalid choice. Choose from 1 to 7.");
            }
        }
    }
    // Display the main menu.
    private static void showMenu() {

    System.out.println("\n");
    System.out.println("========================================");
    System.out.println("           CPU SCHEDULER");
    System.out.println("========================================");

    System.out.println("  [1]  Enter Processes Information");
    System.out.println("  [2]  Display Processes");
    System.out.println("----------------------------------------");
    System.out.println("  [3]  Run Scheduling Algorithm");
    System.out.println("  [4]  Compare Two Algorithms");
    System.out.println("----------------------------------------");
    System.out.println("  [5]  Show Last Results");
    System.out.println("  [6]  Reset Data");
    System.out.println("----------------------------------------");
    System.out.println("  [7]  Exit");

    System.out.println("========================================");
    System.out.print("  Enter your choice: ");
}
    // Read a valid integer.
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static int readPositiveInt(String message) {
        int value;
        do {
            value = readInt(message);
            if (value <= 0) {
                System.out.println("Value must be greater than zero.");
            }
        } while (value <= 0);
        return value;
    }

    private static int readNonNegativeInt(String message) {
        int value;
        do {
            value = readInt(message);
            if (value < 0) {
                System.out.println("Value can not be negative.");
            }
        } while (value < 0);
        return value;
    }

    private static boolean checkProcesses() {
        if (processes.isEmpty()) {
            System.out.println("Please enter processes first.");
            return false;
        }
        return true;
    }

    // Get process details from the user.
    private static void enterProcesses() {
        processes.clear();
        lastResults.clear();
        lastAlgorithm = "";

        int count;
        do {
            count = readInt("Number of processes (1-5): ");
            if (count < 1 || count > 5) {
                System.out.println("Enter a number from 1 to 5.");
            }
        } while (count < 1 || count > 5);

        for (int i = 1; i <= count; i++) {
            System.out.println("\nProcess P" + i);
            int burst = readPositiveInt("Burst Time: ");
            int arrival = readNonNegativeInt("Arrival Time: ");
            int priority = readPositiveInt(
                    "Priority (smaller number = higher priority): ");

            processes.add(new Process("P" + i, burst, arrival, priority));
        }

        System.out.println("\nProcesses added successfully.");
    }

    // Display the entered processes.
    private static void displayProcesses() {
        if (!checkProcesses()) return;

        System.out.println("\nPID\tAT\tBT\tPriority");
        for (Process p : processes) {
            System.out.println(p.getPid() + "\t" + p.getArrivalTime()
                    + "\t" + p.getBurstTime() + "\t" + p.getPriority());
        }
    }

    // Run the selected scheduling algorithm.
    private static void runAlgorithmMenu() {
        if (!checkProcesses()) return;

        System.out.println("\n1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. Round Robin");
        System.out.println("4. Priority");

        int choice = readInt("Choose algorithm: ");
        ArrayList<ResultRow> result;
        String name;

        switch (choice) {
            case 1:
                result = CPUScheduler.fcfs(processes);
                name = "FCFS";
                break;
            case 2:
                result = CPUScheduler.sjf(processes);
                name = "SJF";
                break;
            case 3:
                int quantum = readPositiveInt("Enter Time Quantum: ");
                result = CPUScheduler.roundRobin(processes, quantum);
                name = "Round Robin";
                break;
            case 4:
                result = CPUScheduler.priority(processes);
                name = "Priority";
                break;
            default:
                System.out.println("Invalid algorithm choice.");
                return;
        }

        lastResults = result;
        lastAlgorithm = name;
        printResults(name, result);
    }

    // Display scheduling results and averages.
    private static void printResults(String name, ArrayList<ResultRow> result) {
        System.out.println("\n--- " + name + " Scheduling Results ---");
        System.out.println("PID\tAT\tBT\tCT\tWT\tTAT");

        double totalWaiting = 0;
        double totalTurnaround = 0;

        for (ResultRow row : result) {
            Process p = row.getProcess();

            System.out.println(p.getPid() + "\t" + p.getArrivalTime()
                    + "\t" + p.getBurstTime() + "\t"
                    + row.getCompletionTime() + "\t"
                    + row.getWaitingTime() + "\t"
                    + row.getTurnaroundTime());

            totalWaiting += row.getWaitingTime();
            totalTurnaround += row.getTurnaroundTime();
        }

        System.out.printf(Locale.US, "Average Waiting Time (AWT): %.2f ms%n",
                totalWaiting / result.size());
        System.out.printf(Locale.US,
                "Average Turnaround Time (ATAT): %.2f ms%n",
                totalTurnaround / result.size());
    }

    // Compare two scheduling algorithms.
    private static void compareAlgorithms() {
        if (!checkProcesses()) return;

        System.out.println("\nChoose first algorithm:");
        int first = chooseAlgorithm();
        if (first == 0) return;

        System.out.println("\nChoose second algorithm:");
        int second = chooseAlgorithm();
        if (second == 0) return;

        int quantum = 0;
        if (first == 3 || second == 3) {
            quantum = readPositiveInt("Enter Time Quantum for Round Robin: ");
        }

        ArrayList<ResultRow> firstResult = runSelected(first, quantum);
        ArrayList<ResultRow> secondResult = runSelected(second, quantum);

        System.out.println("\n========== ALGORITHM COMPARISON ==========");
        printComparisonRow(algorithmName(first), firstResult);
        printComparisonRow(algorithmName(second), secondResult);
        System.out.println("==========================================");
    }

    private static int chooseAlgorithm() {
        System.out.println("1. FCFS  2. SJF  3. Round Robin  4. Priority");
        int choice = readInt("Choice: ");

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid algorithm choice.");
            return 0;
        }
        return choice;
    }

    private static ArrayList<ResultRow> runSelected(int choice, int quantum) {
        switch (choice) {
            case 1: return CPUScheduler.fcfs(processes);
            case 2: return CPUScheduler.sjf(processes);
            case 3: return CPUScheduler.roundRobin(processes, quantum);
            default: return CPUScheduler.priority(processes);
        }
    }

    private static String algorithmName(int choice) {
        switch (choice) {
            case 1: return "FCFS";
            case 2: return "SJF";
            case 3: return "Round Robin";
            default: return "Priority";
        }
    }

    private static double averageWaiting(ArrayList<ResultRow> result) {
        double total = 0;
        for (ResultRow row : result) total += row.getWaitingTime();
        return total / result.size();
    }

    private static double averageTurnaround(ArrayList<ResultRow> result) {
        double total = 0;
        for (ResultRow row : result) total += row.getTurnaroundTime();
        return total / result.size();
    }

    private static void printComparisonRow(
            String name, ArrayList<ResultRow> result) {
        System.out.printf(Locale.US, "%-15s AWT: %-8.2f ATAT: %.2f%n",
                name, averageWaiting(result), averageTurnaround(result));
    }

    // Show the most recent results.
    private static void showResults() {
        if (lastResults.isEmpty()) {
            System.out.println("No results yet. Run an algorithm first.");
            return;
        }
        printResults(lastAlgorithm, lastResults);
    }

    // Clear all saved data.
    private static void resetData() {
        processes.clear();
        lastResults.clear();
        lastAlgorithm = "";
        System.out.println("All process data and results have been reset.");
    }
}

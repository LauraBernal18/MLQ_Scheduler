package sos.mlq.model;

/**
 * Represents a process in the system for the MLQ (Multilevel Queue) scheduling algorithm.
 * Each process has an ID, burst time, arrival time, queue level, and calculated metrics.
 *
 * <p>This class is the base data structure for the simulation and is used by
 * all queue types (RR, STCF) and the MLQ scheduler itself.</p>
 *
 * @author Laura
 * @version 1.1
 * @since 2025-2
 */
public class Process {
    private String label;   // Process name or ID (e.g., A, B, C)
    private int burstTime;  // Total CPU time required
    private int originalBurstTime; // Stores the original burst time for calculations
    private int arrivalTime; // When the process arrives to the system
    private int queueLevel;  // The queue this process belongs to (1, 2, or 3)

    // Metrics to be calculated
    private int waitingTime;
    private int completionTime;
    private int responseTime;
    private int turnAroundTime;

    /**
     * Constructor that initializes a process with its basic attributes.
     *
     * @param label        Process identifier
     * @param burstTime    CPU burst time required
     * @param arrivalTime  Time when the process arrives
     * @param queueLevel   Queue level (1 = highest priority)
     */
    public Process(String label, int burstTime, int arrivalTime, int queueLevel) {
        this.label = label;
        this.burstTime = burstTime;
        this.originalBurstTime = burstTime; // keeps the original value
        this.arrivalTime = arrivalTime;
        this.queueLevel = queueLevel;
        this.responseTime =-1; //means: not yet responded
    }

    // Getters and setters for each attribute

    public String getLabel() {
        return label;
    }

    public int getBurstTime() {
        return burstTime;
    }
    public int getOriginalBurstTime() {
        return originalBurstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getQueueLevel() {
        return queueLevel;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnaroundTime) {
        this.turnAroundTime = turnaroundTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    /**
     * Returns a string representation of the process for debugging or output.
     *
     * @return formatted string with process data
     */
    @Override
    public String toString() {
        return label + " (BT=" + burstTime + ", AT=" + arrivalTime + ", Q=" + queueLevel + ")";
    }


}

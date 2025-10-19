package sos.mlq.queues;

import sos.mlq.model.Process;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements a simple Round Robin (RR) scheduling queue.
 *
 * Each process is given a fixed time quantum to execute.
 * If the process is not finished after its time slice,
 * it is placed back at the end of the queue.
 *
 * This implementation updates the basic process metrics:
 * Waiting Time (WT), Completion Time (CT), Response Time (RT),
 * and Turnaround Time (TAT).
 *
 * @author Laura
 * @version 1.0
 * @since 2025-10
 */
public class RRQueue extends SchedulerQueue {

    /**
     * Constructor that sets the quantum for this Round Robin queue.
     *
     * @param quantum the time quantum assigned to this queue
     */
    public RRQueue(int quantum) {
        this.processes = new LinkedList<>();
        this.quantum = quantum;
    }

    /**
     * Executes all processes in this Round Robin queue.
     * Each process receives CPU time equal to the quantum.
     * If the process is not completed, it is added again to the queue.
     */
    @Override
    public int execute(int startTime) {
        int currentTime = startTime; // keeps track of total CPU time

        while (!processes.isEmpty()) {
            Process p = processes.poll(); // take first process from the queue

            // if the process is arriving later, wait for it
            if (p.getArrivalTime() > currentTime) {
                currentTime = p.getArrivalTime();
            }

            // if first time execution, record response time
            if (p.getResponseTime() == -1 && currentTime >= p.getArrivalTime()) {
                p.setResponseTime(currentTime - p.getArrivalTime());
            }

            // determine how much time it will run this turn
            int execTime = Math.min(p.getBurstTime(), quantum);
            p.setBurstTime(p.getBurstTime() - execTime);
            currentTime += execTime;

            // if process finished, record its completion metrics
            if (p.getBurstTime() == 0) {
                p.setCompletionTime(currentTime);
                p.setTurnAroundTime(p.getCompletionTime() - p.getArrivalTime());
                p.setWaitingTime(p.getTurnAroundTime() - (p.getOriginalBurstTime()));
            } else {
                // not finished → back to the queue
                processes.add(p);
            }
        }
        return currentTime;
    }
}


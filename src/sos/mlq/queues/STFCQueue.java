package sos.mlq.queues;

import sos.mlq.model.Process;
import java.util.ArrayList;

/**
 * Implements the Shortest Time to Completion First (STCF) scheduling algorithm.
 *
 * This algorithm always selects the process with the shortest remaining burst time.
 * It is a preemptive version of the Shortest Job First (SJF) scheduling algorithm.
 *
 * Each time a new process arrives, the scheduler compares remaining times and
 * decides which process should execute next.
 *
 * @author Laura
 * @version 1.0
 * @since 2025-10
 */
public class STCFQueue extends SchedulerQueue {

    /**
     * Executes the STCF scheduling for all processes in this queue.
     * Calculates completion, turnaround, waiting, and response times.
     */
    @Override
    public void execute() {
        ArrayList<Process> processList = new ArrayList<>(processes);
        int n = processList.size();

        int currentTime = 0;
        int completed = 0;

        while (completed < n) {
            Process shortest = null;
            int minTime = Integer.MAX_VALUE;

            // Find process with the smallest remaining burst time
            for (Process p : processList) {
                if (p.getArrivalTime() <= currentTime && p.getBurstTime() > 0) {
                    if (p.getBurstTime() < minTime) {
                        minTime = p.getBurstTime();
                        shortest = p;
                    }
                }
            }

            if (shortest == null) {
                currentTime++;
                continue;
            }

            // If process is executing for the first time, record response time
            if (shortest.getResponseTime() == 0 && currentTime >= shortest.getArrivalTime()) {
                shortest.setResponseTime(currentTime - shortest.getArrivalTime());
            }

            // Execute process for 1 time unit
            shortest.setBurstTime(shortest.getBurstTime() - 1);
            currentTime++;

            // If process finishes, record all metrics
            if (shortest.getBurstTime() == 0) {
                shortest.setCompletionTime(currentTime);
                shortest.setTurnAroundTime(shortest.getCompletionTime() - shortest.getArrivalTime());
                shortest.setWaitingTime(shortest.getTurnAroundTime() - shortest.getOriginalBurstTime());
                completed++;
            }
        }
    }
}


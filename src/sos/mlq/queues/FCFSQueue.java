package sos.mlq.queues;

import sos.mlq.model.Process;
import java.util.LinkedList;

/**
 * Implements a simple First Come First Served (FCFS) scheduling queue.
 * <p>
 * Processes are executed in the order they arrive in the queue.
 * No preemption or priority is applied.
 * </p>
 *
 * This class updates all process metrics:
 * Waiting Time (WT), Completion Time (CT), Response Time (RT),
 * and Turnaround Time (TAT).
 *
 * @author Laura
 * @version 1.1
 * @since 2025-10
 */
public class FCFSQueue extends SchedulerQueue {

    /**
     * Default constructor.
     * Initializes an empty queue of processes.
     */
    public FCFSQueue() {
        this.processes = new LinkedList<>();
    }

    /**
     * Executes all processes in this FCFS queue, starting from a given global time.
     * Processes are executed one after another in the same order they were added.
     *
     * @param startTime the global time when this queue starts executing
     * @return the global time after this queue finishes all its processes
     */
    @Override
    public int execute(int startTime) {
        int currentTime = startTime;

        while (!processes.isEmpty()) {
            Process p = processes.poll();

            // Record response time if it's the first time running
            if (p.getResponseTime() == -1 && currentTime >= p.getArrivalTime()) {
                p.setResponseTime(currentTime);
            }

            // Execute process completely
            currentTime += p.getBurstTime();
            p.setCompletionTime(currentTime);
            p.setTurnAroundTime(p.getCompletionTime() - p.getArrivalTime());
            p.setWaitingTime(p.getTurnAroundTime() - p.getOriginalBurstTime());
        }

        return currentTime; // Return total time to continue with next queue
    }
}

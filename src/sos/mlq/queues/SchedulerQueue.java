package sos.mlq.queues;

import sos.mlq.model.Process;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Abstract class that defines the general behavior of a queue
 * within the Multilevel Queue (MLQ) scheduling algorithm.
 *
 * Each queue (e.g., Round Robin, STCF, FCFS) will inherit from this class
 * and implement its own scheduling logic in the {@link #execute()} method.
 *
 * @author Laura Bernal
 * @version 1.0
 * @since 2025-10
 */
public abstract class SchedulerQueue {

    /**
     * Queue that stores the processes assigned to this scheduler queue.
     */
    protected Queue<Process> processes;

    /**
     * Time quantum used for Round Robin queues.
     * It can be ignored by queues that do not use quantum.
     */
    protected int quantum;

    /**
     * Default constructor.
     * Initializes an empty queue of processes.
     */
    public SchedulerQueue() {
        this.processes = new LinkedList<>();
    }

    /**
     * Abstract method that must be implemented by each subclass.
     * Defines how the queue executes its processes according to
     * its scheduling policy.
     */
    public abstract void execute();

    /**
     * Adds a process to the queue.
     *
     * @param process the process to be added
     */
    public void addProcess(Process process) {
        processes.add(process);
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return {@code true} if the queue has no processes, otherwise {@code false}
     */
    public boolean isEmpty() {
        return processes.isEmpty();
    }

    /**
     * Returns the process at the front of the queue without removing it.
     *
     * @return the next process in the queue, or {@code null} if the queue is empty
     */
    public Process peekProcess() {
        return processes.peek();
    }

    /**
     * Returns the number of processes currently in the queue.
     *
     * @return the number of processes in the queue
     */
    public int size() {
        return processes.size();
    }

    /**
     * Returns the quantum value associated with this queue.
     *
     * @return the quantum value
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * Sets the quantum value for this queue.
     *
     * @param quantum the quantum value to assign
     */
    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
}

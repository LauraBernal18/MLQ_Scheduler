package sos.mlq.scheduler;

import sos.mlq.model.Process;
import sos.mlq.queues.RRQueue;
import sos.mlq.queues.STCFQueue;
import sos.mlq.queues.SchedulerQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the execution of the Multilevel Queue (MLQ) scheduling algorithm.
 *
 * It manages three queues with different scheduling policies:
 * - Queue 1: Round Robin with quantum = 2
 * - Queue 2: Round Robin with quantum = 3
 * - Queue 3: Shortest Time to Completion First (STCF)
 *
 * Each queue is executed in priority order.
 *
 * @author Laura
 * @version 1.0
 * @since 2025-10
 */
public class MLQScheduler {

    private SchedulerQueue queue1; // Highest priority - RR(2)
    private SchedulerQueue queue2; // Medium priority - RR(3)
    private SchedulerQueue queue3; // Lowest priority - STCF
    private List<Process> allProcesses;

    /**
     * Default constructor that initializes all queues.
     */
    public MLQScheduler() {
        queue1 = new RRQueue(2);
        queue2 = new RRQueue(3);
        queue3 = new STCFQueue();
        allProcesses = new ArrayList<>();
    }

    /**
     * Adds a process to its respective queue according to its queue level.
     *
     * @param p the process to be added
     */
    public void addProcess(Process p) {
        allProcesses.add(p);
        if (p.getQueueLevel() == 1) queue1.addProcess(p);
        else if (p.getQueueLevel() == 2) queue2.addProcess(p);
        else queue3.addProcess(p);
    }

    /**
     * Executes all the queues according to their priority.
     * Queue 1 (RR2) → Queue 2 (RR3) → Queue 3 (STCF)
     */
    public void executeAll() {
        int globalTime = 0; //start from time 0

        System.out.println("Executing Queue 1 (RR - Quantum 2)");
        globalTime = queue1.execute(globalTime);

        System.out.println("Executing Queue 2 (RR - Quantum 3)");
        globalTime = queue2.execute(globalTime);

        System.out.println("Executing Queue 3 (STCF)");
        globalTime = queue3.execute(globalTime);
    }

    /**
     * Returns all processes in the system (for output or metrics).
     *
     * @return list of all processes
     */
    public List<Process> getAllProcesses() {
        return allProcesses;
    }
}

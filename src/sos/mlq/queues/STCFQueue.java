package sos.mlq.queues;

import sos.mlq.model.Process;
import java.util.*;

/**
 * Implements Shortest Time to Completion First (STCF) scheduling.
 */
public class STCFQueue extends SchedulerQueue {

    public STCFQueue() {
        this.processes = new LinkedList<>();
    }

    @Override
    public int execute(int startTime) {
        List<Process> list = new ArrayList<>(processes);
        processes.clear();

        int currentTime = startTime;

        while (!list.isEmpty()) {
            // Find process with smallest remaining burst
            Process shortest = list.stream()
                    .min(Comparator.comparingInt(Process::getBurstTime))
                    .orElse(null);

            if (shortest == null) break;

            // Run process
            if (shortest.getResponseTime() == -1 && currentTime >= shortest.getArrivalTime()) {
                shortest.setResponseTime(currentTime - shortest.getArrivalTime());
            }

            currentTime += shortest.getBurstTime();
            shortest.setCompletionTime(currentTime);
            shortest.setTurnAroundTime(shortest.getCompletionTime() - shortest.getArrivalTime());
            shortest.setWaitingTime(shortest.getTurnAroundTime() - shortest.getOriginalBurstTime());
            list.remove(shortest);
        }

        return currentTime; // pass to next queue
    }
}

package sos.mlq.io;

import sos.mlq.model.Process;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes the results of the MLQ scheduling algorithm
 * into a formatted text file.
 *
 * The file includes process metrics such as:
 * Waiting Time (WT), Completion Time (CT),
 * Response Time (RT), and Turnaround Time (TAT).
 *
 * @author Laura
 * @version 1.0
 * @since 2025-10
 */
public class OutputWriter {

    /**
     * Writes all process results into an output file.
     *
     * @param filePath  path to the output file
     * @param processes list of processes with calculated metrics
     * @throws IOException if writing fails
     */
    public void writeResults(String filePath, List<Process> processes) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("# label; BT; AT; Q; WT; CT; RT; TAT\n");

            double totalWT = 0, totalCT = 0, totalRT = 0, totalTAT = 0;

            for (Process p : processes) {
                writer.write(String.format("%s;%d;%d;%d;%d;%d;%d;%d\n",
                        p.getLabel(),
                        p.getOriginalBurstTime(),
                        p.getArrivalTime(),
                        p.getQueueLevel(),
                        p.getWaitingTime(),
                        p.getCompletionTime(),
                        p.getResponseTime(),
                        p.getTurnAroundTime()));

                totalWT += p.getWaitingTime();
                totalCT += p.getCompletionTime();
                totalRT += p.getResponseTime();
                totalTAT += p.getTurnAroundTime();
            }

            int n = processes.size();
            writer.write(String.format("\nWT=%.2f; CT=%.2f; RT=%.2f; TAT=%.2f;",
                    totalWT / n, totalCT / n, totalRT / n, totalTAT / n));
        }
    }
}

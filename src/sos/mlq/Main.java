package sos.mlq;

import sos.mlq.io.FileParser;
import sos.mlq.io.OutputWriter;
import sos.mlq.model.Process;
import sos.mlq.scheduler.MLQScheduler;
import java.util.List;

/**
 * Main entry point for running the MLQ scheduling algorithm.
 *
 * Reads input from a file, executes the MLQ algorithm,
 * and writes the results to an output file.
 *
 * @author Laura
 * @version 1.1
 * @since 2025-10
 */
public class Main {
    /**
     * Main method that orchestrates the reading, scheduling, and writing process.
     *
     * @param args command-line arguments (not used in this implementation)
     */

    public static void main(String[] args) {
        String inputFile = "src/resources/mlq001.txt";
        String outputFile = "src/resources/output_mlq0012.txt";

        try {
            // Read processes
            FileParser parser = new FileParser();
            List<Process> processes = parser.readProcesses(inputFile);

            // Create scheduler and add all processes
            MLQScheduler scheduler = new MLQScheduler();
            for (Process p : processes) {
                scheduler.addProcess(p);
            }

            //Execute the scheduling algorithm
            scheduler.executeAll();

            // Write results to output file
            OutputWriter writer = new OutputWriter();
            writer.writeResults(outputFile, scheduler.getAllProcesses());

            //Confirmation message
            System.out.println("Results saved to: " + outputFile);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

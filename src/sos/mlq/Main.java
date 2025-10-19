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
 * @version 1.0
 * @since 2025-10
 */
public class Main {

    public static void main(String[] args) {
        String inputFile = "src/resources/mlq001.txt";
        String outputFile = "src/resources/output_mlq0012.txt";

        try {
            // Read processes
            FileParser parser = new FileParser();
            List<Process> processes = parser.readProcesses(inputFile);

            // Create and run scheduler
            MLQScheduler scheduler = new MLQScheduler();
            for (Process p : processes) {
                scheduler.addProcess(p);
            }
            scheduler.executeAll();

            // Write results
            OutputWriter writer = new OutputWriter();
            writer.writeResults(outputFile, scheduler.getAllProcesses());

            System.out.println("Results saved to: " + outputFile);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

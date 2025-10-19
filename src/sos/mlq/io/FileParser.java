package sos.mlq.io;

import sos.mlq.model.Process;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads process data from a text file and converts each line
 * into a {@link Process} object for the MLQ scheduler.
 *
 * Expected format per line:
 * label; burstTime; arrivalTime; queue
 *
 * Example:
 * A;6;0;1
 * B;9;0;1
 * C;10;0;2
 * D;15;0;2
 * E;8;0;3
 *
 * Lines starting with '#' are ignored.
 *
 * @author Laura
 * @version 1.2
 * @since 2025-10
 */
public class FileParser {

    /**
     * Reads a file and returns a list of {@link Process} objects.
     *
     * @param filePath the path to the input text file
     * @return list of processes loaded from the file
     * @throws IOException if the file cannot be read
     */
    public List<Process> readProcesses(String filePath) throws IOException {
        List<Process> processes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Ignore empty or comment lines
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split(";");
                String label = parts[0].trim();
                int bt = Integer.parseInt(parts[1].trim());
                int at = Integer.parseInt(parts[2].trim());
                int q = Integer.parseInt(parts[3].trim());

                processes.add(new Process(label, bt, at, q));
            }
        }

        return processes;
    }
}

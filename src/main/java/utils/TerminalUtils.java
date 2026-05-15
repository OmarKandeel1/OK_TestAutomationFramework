package utils;

import utils.logs.LogsManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class TerminalUtils {
    public static boolean executeTerminalCommand(String... commandParts) {
        try {
            Process process = new ProcessBuilder(commandParts)
                    .redirectErrorStream(true)
                    .start();

            String output;
            try (BufferedReader reader = process.inputReader()) {
                output = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                LogsManager.error("Command failed with exit code: " + exitCode, output);
                return false;
            }

            if (!output.isBlank()) {
                LogsManager.info(output);
            }

            return true;
        } catch (IOException e) {
            LogsManager.error("Failed to execute terminal command: " + String.join(" ", commandParts), e.getMessage());
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogsManager.error("Terminal command was interrupted: " + String.join(" ", commandParts), e.getMessage());
            return false;
        }
    }
}

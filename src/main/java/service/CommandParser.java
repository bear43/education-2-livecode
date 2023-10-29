package service;

import java.util.Arrays;
import java.util.StringTokenizer;
import model.Command;

public class CommandParser {
    public static Command parse(String input) {
        String[] tokens = input.split(" +");
        String command = tokens[0];
        if (command.isBlank()) {
            throw new IllegalArgumentException("Use help for command list");
        }
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
        return new Command(command, args);
    }
}

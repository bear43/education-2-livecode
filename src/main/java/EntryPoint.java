import io.ConsoleReader;
import java.time.LocalDate;
import model.Command;
import model.Status;
import model.Task;
import service.CommandParser;
import service.SimpleTaskTracker;
import service.TaskTracker;

public class EntryPoint {

    private static final TaskTracker taskTracker = new SimpleTaskTracker();

    public static void main(String[] args) {
        do {
            try {
                String input = ConsoleReader.read();
                Command command = CommandParser.parse(input);
                switch (command.command()) {
                    case "help":
                        showHelp();
                        break;
                    case "create":
                        createTask(command);
                        break;
                    default:
                        System.err.println("Unknown command. Use help");
                }
            } catch (IllegalArgumentException exception) {
                System.err.println("Error occurred: " + exception.getMessage());
            }
        } while (true);
    }

    private static void createTask(Command command) {
        String[] args = command.args();
        String summaryText = args[0];
        String priorityText = args[1];
        String deadlineDate = args[2];
        Task task = new Task(null, summaryText,
                Integer.parseInt(priorityText),
                LocalDate.parse(deadlineDate),
                Status.TODO);
        Task persistedTask = taskTracker.create(task);
        System.out.printf("Task created with id %s%n", persistedTask.id());
    }

    private static void showHelp() {

    }
}

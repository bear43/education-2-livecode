package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import model.Order;
import model.Status;
import model.Task;

public class SimpleTaskTracker implements TaskTracker {

    private static final String TASK_CAN_NOT_BE_NULL = "task can not be null";
    private static final String NO_SUCH_TASK = "No such task with given id";
    private final List<Task> tasks;

    public SimpleTaskTracker() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public Task create(Task task) {
        Objects.requireNonNull(task, TASK_CAN_NOT_BE_NULL);
        Task persistedTask = task.withId(UUID.randomUUID());
        tasks.add(persistedTask);
        return persistedTask;
    }

    @Override
    public Task update(Task task) {
        delete(task.id());
        tasks.add(task);
        return task;
    }

    @Override
    public Task get(UUID id) {
        return tasks.stream()
                .filter(task -> task.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(NO_SUCH_TASK));
    }

    @Override
    public Collection<Task> findAll() {
        return tasks;
    }

    @Override
    public void delete(UUID id) {
        Task task = get(id);
        tasks.remove(task);
    }

    @Override
    public Collection<Task> findAllSorted(Order order) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::priority, order.getComparator()))
                .toList();
    }

    @Override
    public Collection<Task> findAllForToday(Order order) {
        return tasks.stream()
                .filter(task -> task.deadlineDate().equals(LocalDate.now()))
                .toList();
    }

    @Override
    public Map<Status, Collection<Task>> findAllByStatusSorted() {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::status,
                        Collectors.toCollection(ArrayList::new)));
    }
}

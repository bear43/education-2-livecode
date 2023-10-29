package service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import model.Order;
import model.Status;
import model.Task;

public interface TaskTracker {
    Task create(Task task);
    Task update(Task task);
    Task get(UUID id);
    Collection<Task> findAll();
    void delete(UUID id);
    Collection<Task> findAllSorted(Order order);
    Collection<Task> findAllForToday(Order order);
    Map<Status, Collection<Task>> findAllByStatusSorted();
}

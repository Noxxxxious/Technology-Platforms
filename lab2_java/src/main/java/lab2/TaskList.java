package lab2;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public synchronized void put(Task task){
        this.tasks.add(task);
        notifyAll();
    }

    public synchronized Task get() throws InterruptedException {
        while(tasks.isEmpty()){
            wait();
        }
        return tasks.remove(0);
    }
}

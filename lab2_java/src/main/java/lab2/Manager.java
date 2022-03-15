package lab2;

import java.io.BufferedWriter;
import java.io.IOException;

public class Manager implements Runnable{
    private TaskList taskList;
    private TaskListResult taskListResult;
    private BufferedWriter file;

    public Manager(TaskList taskList, TaskListResult taskGroupResult, BufferedWriter file){
        this.taskList = taskList;
        this.taskListResult = taskGroupResult;
        this.file = file;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try{
                Task task = taskList.get();
                Result result = task.divisors();
                taskListResult.addResult(result, file, task.getNumber());
            } catch (InterruptedException | IOException ex){
                break;
            }
        }
    }
}

package task;

import repeatability.Repeatability;
import type.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private String title;
    private String body;
    private Type type;
    private Repeatability repeatability;
    private LocalDateTime time;
    public LocalDateTime getTime() {
        return time;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public Task(String title, String body, Type type, Repeatability repeatability, LocalDateTime time) {
        this.title = title;
        this.body = body;
        this.type = type;
        this.repeatability = repeatability;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", type=" + type +
                ", repeatability=" + repeatability +
                ", time=" + time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                '}';
    }
}

package service;

import repeatability.Repeatability;
import task.Task;
import type.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static repeatability.Repeatability.*;
import static type.Type.PERSONAL;
import static type.Type.WORK;

public class Diary {
    private Map<Integer, Task> tasks = new LinkedHashMap<>();

    public void generateTasks() {
        tasks.put(0, new Task("Оплатить комуналку", "Оплатить за свет,за воду и за газ", PERSONAL, MONTHLY, LocalDateTime.of(2017, Month.NOVEMBER, 30, 11, 30)));
        tasks.put(1, new Task("обеденный перерыв", "обеденный перерыв на работе", WORK, DAILY, LocalDateTime.of(2022, 10, 22, 12, 00)));
        tasks.put(2, new Task("обеденный перерыв", "обеденный перерыв на работе", WORK, WEEKLY, LocalDateTime.of(2022, 10, 24, 9, 00)));
        tasks.put(3, new Task("Корпоратив", "День рождение фирмы", WORK, SINGLE, LocalDateTime.of(2022, 10, 05, 18, 00)));
        tasks.put(4, new Task("День нашей свадьбы", "День нашей свадьбы", PERSONAL, YEARLY, LocalDateTime.of(2022, 05, 05, 18, 00)));
    }

    public void addTask() {
        String title = null;
        String body = null;
        Type type = null;
        Repeatability repeatability = null;
        LocalDateTime dateTime = null;
        Scanner scanner = new Scanner(System.in);

        while (title == null) {
            System.out.println("Введите название задачи: ");
            title = scanner.nextLine();
            if (title == null)
                System.out.println("Ошибка. Название задачи не может быть пустым!");
            else break;
        }

        while (body == null) {
            System.out.println("Введите описание задачи: ");
            body = scanner.nextLine();
            if (body == null)
                System.out.println("Ошибка. Описание задачи не может быть пустым!");
            else break;
        }

        while (type == null) {
            System.out.println("""
                    Введите соответствующую цифру типу задачи: 
                    1. Личный; 
                    2. Рабочий; 
                    """);
            switch (scanner.nextLine()) {
                case "1" -> type = PERSONAL;
                case "2" -> type = WORK;
                default -> System.out.println("Вы ввели неправильную команду!");
            }
        }

        while (repeatability == null) {
            System.out.println("""
                    Введите соответствующую цифру повторяемости задачи: 
                    1. однократная,
                    2. ежедневная,
                    3. еженедельная,
                    4. ежемесячная,
                    5. ежегодная; 
                    """);
            switch (scanner.nextLine()) {
                case "1" -> repeatability = SINGLE;
                case "2" -> repeatability = DAILY;
                case "3" -> repeatability = WEEKLY;
                case "4" -> repeatability = MONTHLY;
                case "5" -> repeatability = YEARLY;
                default -> System.out.println("Вы ввели неправильную команду!");
            }
        }

        while (dateTime == null) {
            System.out.println("Введите дату в фомате 2015-08-04 10:11");
            String str = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                dateTime = LocalDateTime.parse(str, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Вы ввели не правильно формат даты!");
            }
        }
        Task task = new Task(title, body, type, repeatability, dateTime);
    }

    public void getTask() {
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        while (date == null) {
            System.out.println("Введите дату в фомате 2015-08-04");
            String str = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                date = LocalDate.parse(str, formatter);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Вы ввели не правильно формат даты!");
            }
        }
        for (Task task : tasks.values()) {
            switch (task.getRepeatability()) {
                case SINGLE -> {
                    if (Period.between(date, task.getTime().toLocalDate()).getDays() == 0)
                        System.out.println(task);
                }
                case DAILY -> System.out.println(task);
                case WEEKLY -> {
                    if (task.getTime().getDayOfWeek() == date.getDayOfWeek())
                        System.out.println(task);
                }
                case MONTHLY -> {
                    if (task.getTime().getDayOfMonth() == date.getDayOfMonth())
                        System.out.println(task);
                }
                case YEARLY -> {
                    if (task.getTime().getDayOfYear() == date.getDayOfYear())
                        System.out.println(task);
                }
            }
        }
    }


    public void deleteTask() {
        if (tasks.size() == 0) {
            System.out.println("задач не существует");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        Integer id = null;
        while (id == null) {
            System.out.println("Введите id удаляемой задачи:");
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ошибка! Вы ввели не число");
            }
        }
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            System.out.println("удаление успешно выполнена");
        } else {
            System.out.println("такой задичи не существует");
            deleteTask();
        }
    }
}

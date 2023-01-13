package menu;

import service.Diary;

import java.util.Scanner;

public class Menu {
    Diary diary = new Diary();

    public void start(){
        diary.generateTasks();
        processMenu();
    }

    private void showMenu() {
        System.out.println(""" 
                Введите соответствующую цифру:
                1. Добавить задачу;
                2. Получить задачу;
                3. Удалить задачу; 
                4. Завершить программу;           
                """);
    }

    private void processMenu() {
        Scanner scanner = new Scanner(System.in);
        showMenu();
        while (true) {
            switch (scanner.nextLine()) {
                case "1" -> diary.addTask();
                case "2" -> diary.getTask();
                case "3" -> diary.deleteTask();
                case "4" -> { return; }
                default -> System.out.println("Вы ввели не правильную команду");
            }
        }
    }
}

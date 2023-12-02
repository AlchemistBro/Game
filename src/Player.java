import java.util.Scanner;

public class Player {
    protected String namePlayer;
    protected Unit[] units;

    Scanner scanner = new Scanner(System.in);

    public Player(String namePlayer) {
        units = new Unit[3];
        this.namePlayer = namePlayer;
    }

    public String getName() {
        return namePlayer;
    }

    public void addUnits() { //добавляем юнитов игроку
        System.out.println(namePlayer + ", выбери своих героев: Knight, Wizard, Ork (введи первую букву названия):");

        for (int i = 0; i < 3; i++) {
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("W")) {
                units[i] = new Wizard();
            } else if (choice.equalsIgnoreCase("O")) {
                units[i] = new Ork();
            } else if (choice.equalsIgnoreCase("K")) {
                units[i] = new Knight();
            }
            else {
                System.out.println("Некорректный ввод!");
                i--;
            }
        }
    }
    public void getBuff(){
        System.out.println("Бафф Knight: повышает шанс парирования до 50%." +
                "\nБафф Wizard: восстанавливает здоровье героя на величину нанесённого врагу урона. " +
                "\nБафф Ork: возвращает 50% полученного урона врагу");
        System.out.println(namePlayer + ", выбери номер героя для усиления");
        int choice = scanner.nextInt();
        units[choice-1].buff();

    }
    public void checkHealth() {
        int aliveCount = 0;

        // Сначала посчитаем количество живых персонажей
        for (int i = 0; i < units.length; i++) {
            if (units[i].getHealth() > 0) {
                aliveCount++;
            }
        }

        // Создадим новый массив только с живыми персонажами
        Unit[] aliveUnits = new Unit[aliveCount];
        int index = 0;
        for (int i = 0; i < units.length; i++) {
            if (units[i].getHealth() > 0) {
                aliveUnits[index] = units[i];
                index++;
            }
        }

        // Присваиваем новый массив переменной units
        units = aliveUnits;
    }

}

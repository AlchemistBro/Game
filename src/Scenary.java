import java.util.Scanner;
import java.util.Random;

public class Scenary {
    private Player firstPlayer, secondPlayer;
    private boolean hasSecondPlayer;
    Scanner scanner = new Scanner(System.in);

    public Scenary() {
        greeting();

        firstPlayer = new Player(askPlayerName());
        hasSecondPlayer = hasSecondPlayer();
        if (hasSecondPlayer) {
            secondPlayer = new Player(askPlayerName());
        }

        whoGoesFirst();
        addUnitsForFirstPlayer();
        if (hasSecondPlayer) {
            addUnitsForSecondPlayer();
        }
    }

    private void greeting() {
        System.out.println("Привет, путник!");
    }

    private String askPlayerName() {
        System.out.println("Представься");
        return scanner.nextLine();
    }

    private boolean hasSecondPlayer() {
        System.out.println("А второй Игрок будет? (Д/Н)");
        return scanner.nextLine().equalsIgnoreCase("Д");
    }

    private void whoGoesFirst() {
        Random random = new Random();
        int result = random.nextInt(2);

        if (result == 0) {
            System.out.println(firstPlayer.getName() + " начинает");
        } else {
            System.out.println(secondPlayer.getName() + " начинает");
            Player temp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = temp;
        }

    }

    public void addUnitsForFirstPlayer() {
        firstPlayer.addUnits();
    }

    public void addUnitsForSecondPlayer() {
        secondPlayer.addUnits();
        secondPlayer.getBuff();
    }

    public void battle() {
        while (true) {
            int attackerIndex = chooseUnitForAttack(firstPlayer);
            if (firstPlayer.units[attackerIndex] instanceof Wizard) {
                System.out.println("Выберите действие для мага:");
                System.out.println("1) Атаковать");
                System.out.println("2) Восстановить ману");

                int action = Integer.parseInt(scanner.nextLine());
                if (action == 1) {
                    int targetIndex = chooseTarget(secondPlayer);
                    firstPlayer.units[attackerIndex].attack(firstPlayer.units[attackerIndex], secondPlayer.units[targetIndex]);
                } else if (action == 2) {
                    ((Wizard) firstPlayer.units[attackerIndex]).restoreMana();
                    System.out.println("10 маны восстановлено.");
                }
            } else {
                int targetIndex = chooseTarget(secondPlayer);
                firstPlayer.units[attackerIndex].attack(firstPlayer.units[attackerIndex], secondPlayer.units[targetIndex]); // Атака первого игрока
            }



            checkHeroesHealth();

            printUnitsState();

            if (checkGameOver()) {
                break;
            }


            attackerIndex = chooseUnitForAttack(secondPlayer);
            if (secondPlayer.units[attackerIndex] instanceof Wizard) {
                System.out.println("Выберите действие для мага:");
                System.out.println("1) Атаковать");
                System.out.println("2) Восстановить ману");

                int action = Integer.parseInt(scanner.nextLine());
                if (action == 1) {
                                        int targetIndex = chooseTarget(firstPlayer);
                    secondPlayer.units[attackerIndex].attack(secondPlayer.units[attackerIndex], firstPlayer.units[targetIndex]);
                } else if (action == 2) {
                    ((Wizard) secondPlayer.units[attackerIndex]).restoreMana();
                    System.out.println("10 маны восстановлено.");
                }
            } else {
                int targetIndex = chooseTarget(firstPlayer);

                secondPlayer.units[attackerIndex].attack(secondPlayer.units[attackerIndex], firstPlayer.units[targetIndex]); // Атака первого игрока
            }

            checkHeroesHealth();
            printUnitsState();

            if (checkGameOver()) {
                break;
            }
        }
    }

    private void checkHeroesHealth() {
        for (int i = 0; i < firstPlayer.units.length; i++) {
            if (firstPlayer.units[i].getHealth() <= 0) {
                firstPlayer.units[i] = null;
            }
        }

        for (int i = 0; i < secondPlayer.units.length; i++) {
            if (secondPlayer.units[i].getHealth() <= 0) {
                secondPlayer.units[i] = null;

            }
        }
    }

    private boolean checkGameOver() {
        if ((firstPlayer.units.length) == 0)
            System.out.println(secondPlayer.namePlayer + " одержал победу!");
        else if ((secondPlayer.units.length) == 0)
            System.out.println(firstPlayer.namePlayer + " одержал победу!");
        return firstPlayer.units.length == 0 || secondPlayer.units.length == 0;
    }

    private void printUnitsState() {
        System.out.println("Состояние юнитов:");
        System.out.println(firstPlayer.getName() + ":");
        for (Unit unit : firstPlayer.units) {
            if (unit != null) {
                System.out.println(unit.toString());
            }
        }

        System.out.println(secondPlayer.getName() + ":");
        for (Unit unit : secondPlayer.units) {
            if (unit != null) {
                System.out.println(unit.toString());
            }
        }
    }
    public int chooseUnitForAttack(Player player) {
        while (true) {
            System.out.println(player.getName() + ", выберите героя, которым будете ходить (1, 2, 3): ");
            int choice = Integer.parseInt(scanner.nextLine()) - 1;

            if (choice >= 0 && choice < player.units.length && player.units[choice] != null) {
                return choice;
            } else {
                System.out.println("Вы ввели некорректный номер юнита. Пожалуйста, выберите ещё раз.");
            }
        }
    }

    public int chooseTarget(Player player) {
        while (true) {
            System.out.println("Выберите героя игрока " + player.getName() + ", которого будете атаковать (1, 2, 3): ");
            int choice = Integer.parseInt(scanner.nextLine()) - 1;

            if (choice >= 0 && choice < player.units.length && player.units[choice] != null) {
                return choice;
            } else {
                System.out.println("Вы ввели некорректный номер юнита. Пожалуйста, выберите ещё раз.");
            }
        }
    }
}





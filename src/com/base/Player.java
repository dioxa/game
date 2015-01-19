package com.base;

import java.util.Scanner;


public class Player {

    private final int id;

    private final World world;

    private static int nextId = 1;

    private final Scanner keyboard = new Scanner(System.in);

    private boolean turnIsOver;

    /**
     * Устанавливает игроку уникальный id и присваевает ему сссылку на мир.
     * @param world Мир игры.
     */
    public Player(World world) {

        id = nextId;
        nextId++;
        this.world = world;

    }

    /**
     * Выбор действия при ходе и вызов соответствующей обработки.
     */
    public void makeTurn() {
        turnIsOver = false;
        int actionNumber = 0;

        do {
            System.out.println("Введите номер ействия: ");
            System.out.println("1. Занять клетку.");
            System.out.println("2. Переместить население.");
            System.out.println("3. Переместить ресурсы.");

            actionNumber = keyboard.nextInt();
            switch (actionNumber) {
                case 1:
                    makeCellTurn();
                    break;
                case 2:
                    makeMovePopulationTurn();
                    break;
                case 3:
                    makeMoveResourcesTurn();
                    break;
                default:
                    System.out.println("Такого действия нет. Попробуйте еще раз.");
                    break;
            }
        } while (!turnIsOver);

    }

    /**
     * Обработка хода захвата клетки.
     */
    private void makeCellTurn() {
        int x, y;

        System.out.println("Введите x, y координаты клетки.");
        do {
            x = keyboard.nextInt() - 1;
            y = keyboard.nextInt() - 1;
        } while (!world.setCellOwnerByTurn(x, y, id));
        turnIsOver = true;
    }

    /**
     * Обработка хода перемещения популяции.
     */
    private void makeMovePopulationTurn() {
        int prevX, prevY, x, y, population;

        do {
            System.out.println("Введите x,y координаты клетки, ИЗ которой хотите переселить людей.");
            prevX = keyboard.nextInt() - 1;
            prevY = keyboard.nextInt() - 1;

            System.out.println("Введите x,y координаты клетки, В которую хотите переселить людей.");
            x = keyboard.nextInt() - 1;
            y = keyboard.nextInt() - 1;

            System.out.println("Введите количество людей для переселения.");
            population = keyboard.nextInt();

        } while (!world.checkMovePopulationAvailability(prevX, prevY, x, y, id, population));
        turnIsOver = true;
    }

    /**
     * Обарботка хода перемещения ресурсов.
     */
    private void makeMoveResourcesTurn() {
        int prevX, prevY, x, y, resources;
        int actionNumber = 0;
        String resourceName = null;

        do {
            System.out.println("Введите номер ресурса: ");
            System.out.println("1. Древесина.");
            System.out.println("2. Еда.");

            actionNumber = keyboard.nextInt();
            switch (actionNumber) {
                case 1:
                    resourceName = "Wood";
                    break;
                case 2:
                    resourceName = "Food";
                    break;
                default:
                    System.out.println("Введен несуществующий ресурс. Попробуйте еще раз.");
                    break;
            }
        } while (!(actionNumber == 1 || actionNumber == 2));
        do {
            System.out.println("Введите x,y координаты клетки, ИЗ которой хотите переместить ресурсы.");
            prevX = keyboard.nextInt() - 1;
            prevY = keyboard.nextInt() - 1;

            System.out.println("Введите x,y координаты клетки, В которую хотите переместить ресурсы.");
            x = keyboard.nextInt() - 1;
            y = keyboard.nextInt() - 1;

            System.out.println("Введите количество ресурсов для перемещения.");
            resources = keyboard.nextInt();

        } while (!world.checkMoveResourcesAvailability(prevX, prevY, x, y, id, resourceName, resources));
        turnIsOver = true;
    }

    public int getId() {
        return id;
    }
}

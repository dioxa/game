package com.base;

import java.util.Scanner;


public class Player {

    private final int id;

    private final World world;

    private static int nextId = 1;

    private final Scanner keyboard = new Scanner(System.in);

    private boolean turnIsOver;

    public Player(World world) {

        id = nextId;
        nextId++;
        this.world = world;

    }

    public void makeTurn() {
        turnIsOver = false;
        int actionNumber = 0;

        do {
            System.out.println("Введите номер ействия: ");
            System.out.println("1. Занять клетку.");
            System.out.println("2. Переместить население.");

            actionNumber = keyboard.nextInt();
            switch (actionNumber) {
                case 1:
                    makeCellTurn();
                    break;
                case 2:
                    makeMovePopulationTurn();
                    break;
                default:
                    System.out.println("Такого действия нет. Попробуйте еще раз.");
                    break;
            }
        } while (!turnIsOver);

    }

    private void makeCellTurn() {
        int x, y;

        System.out.println("Введите x, y координаты клетки.");
        do {
            x = keyboard.nextInt() - 1;
            y = keyboard.nextInt() - 1;
        } while (!world.setCellOwnerByTurn(x, y, id));
        turnIsOver = true;
    }

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

    public int getId() {
        return id;
    }
}

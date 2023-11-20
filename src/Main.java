import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        int choice = menu.displayTreesChoice();
        BinarySearchTree tree = new BinarySearchTree();
        int height;
        switch (choice) {
            case 1:
                tree = menu.createBinaryTree();
                /*height = tree.getHeight();
                System.out.println();
                System.out.println("Высота: " + height);*/
                break;
            case 2:
                tree = menu.createAVLTree();
                /*height = tree.getHeight();
                System.out.println();
                System.out.println("Высота: " + height);*/
                break;
            case 3:
                tree = menu.createRBTree();
                //treeRB = menu.createRBTree();
                /*height = tree.getHeight();
                System.out.println();
                System.out.println("Высота: " + height);*/
                break;
            default:
                throw new IllegalArgumentException("Такого варианта для выбора нет.");
        }

        menu.displayTraverseChoice(tree);
        int actionChoice;
        do {
            actionChoice = menu.chooseAction();
            Scanner keyboard = new Scanner(System.in);
            int element = 0;
            switch(actionChoice) {
                case 1:
                    System.out.print("Введите элемент для добавления: ");
                    element = keyboard.nextInt();
                    /*int counter = 0;
                    long average = 0;
                    Random rand = new Random();
                    element = rand.nextInt();
                    while (counter < menu.size)
                    {
                        long startTime = System.nanoTime();
                        tree.add(element);
                        long endTime = System.nanoTime();
                        long totalTime = endTime - startTime;
                        counter++;
                        tree.delete(element);
                        average += totalTime;
                    }
                    average /= menu.size;
                    System.out.println("Время: " + average);*/
                    tree.add(element);
                    menu.displayTraverseChoice(tree);
                    break;
                case 2:
                    System.out.print("Введите элемент для удаления: ");
                    element = keyboard.nextInt();
                    if (tree.search(element)) {
                        tree.delete(element);
                    }
                    else {
                        throw new IllegalArgumentException("Такого элемента не существует.");
                    }
                    /*int counter1 = 0;
                    long average1 = 0;
                    int element1 = 0;
                    while (counter1 < menu.size) {
                        Random rand1 = new Random();
                        element1 = rand1.nextInt(menu.size*10);
                        if(!tree.search(element1)) {
                            tree.add(element1);
                        }
                        long startTime = System.nanoTime();
                        tree.delete(element1);
                        long endTime = System.nanoTime();
                        long totalTime = endTime - startTime;
                        tree.add(element1);
                        average1 += totalTime;
                        counter1++;
                    }
                    average1 /= menu.size;
                    System.out.println("Время: " + average1);*/
                    menu.displayTraverseChoice(tree);
                    break;
                case 3:
                    break;
                default:
                    throw new IllegalArgumentException("No such option");
            }

        } while (actionChoice != 3);
    }
}
import javax.sound.midi.Soundbank;
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
                /*height = tree.getHeight();
                System.out.println();
                System.out.println("Высота: " + height);*/
                break;
            default:
                throw new IllegalArgumentException("Такого варианта для выбора нет.");
        }

        //menu.displayTraverseChoice(tree);
        int actionChoice;
        do {
            actionChoice = menu.chooseAction();
            Scanner keyboard = new Scanner(System.in);
            int element = 0;
            switch(actionChoice) {
                case 1:
                    System.out.print("Введите элемент для добавления: ");
                    element = keyboard.nextInt();
                   /* Random rand = new Random();
                    element = rand.nextInt();
                    long startTime = System.nanoTime();*/
                    tree.add(element);
                   /* long endTime = System.nanoTime();
                    long totalTime = endTime - startTime;
                    System.out.println("Время: " + totalTime);*/
                    break;
                case 2:
                    /*System.out.print("Введите элемент для удаления: ");
                    element = keyboard.nextInt();*/
                    Random rand = new Random();
                    boolean hasNode = false;
                    while (!hasNode) {
                        element = rand.nextInt();
                        boolean flag = tree.search(element);
                        if (flag) {
                            hasNode = true;
                        }
                    }
                        long startTime = System.nanoTime();
                        tree.delete(element);
                        long endTime = System.nanoTime();
                        long totalTime = endTime - startTime;
                        System.out.println("Время: " + totalTime);

                    /*else {
                        throw new IllegalArgumentException("Такого элемента не существует.");
                    }*/
                break;
                case 3:
                    break;
            }
            //menu.displayTraverseChoice(tree);
        } while (actionChoice != 3);
    }
}
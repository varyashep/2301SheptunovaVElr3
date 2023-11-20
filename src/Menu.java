import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    static int size = 40000;
    int displayTreesChoice() {
        System.out.println("Выберите структуру данных: ");
        System.out.println("1. Бинарное дерево поиска");
        System.out.println("2. AVL дерево");
        System.out.println("3. Красно-черное дерево");
        Scanner keyboard = new Scanner(System.in);
        int choiceType = keyboard.nextInt();
        return choiceType;
    }

    void displayTraverseChoice(BinarySearchTree tree) {
        System.out.println("Выберите обход по дереву: ");
        System.out.println("1. В ширину");
        System.out.println("2. Прямой (Preoder)");
        System.out.println("3. Обратный (Postorder)");
        System.out.println("4. Симметричный (Inorder)");
        Scanner keyboard = new Scanner(System.in);
        int choiceTraverse = keyboard.nextInt();
        switch (choiceTraverse) {
            case 1:
                tree.traverseLevelOrder();
                break;
            case 2:
                tree.traversePreOrder();
                break;
            case 3:
                tree.traversePostOrder();
                break;
            case 4:
                tree.traverseInOrder();
                break;
            default:
                throw new IllegalArgumentException("Такого варианта для выбора нет.");
        }
    }

    BinarySearchTree createBinaryTree() {
        BinarySearchTree bt = new BinarySearchTree();
        System.out.println("Выберите вариант заполнения начального дерева: ");
        System.out.println("1. Вручную");
        System.out.println("2. Рандом");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        //int size = 10000;
        int upperbound = size * 100;
        if (choice == 1) {
            System.out.println("Чтобы закончить заполнение, введите exit");
            int element;
            boolean finishedInput = false;
            do {
                try
                {
                    element = keyboard.nextInt();
                    bt.add(element);
                } catch (Exception e) {
                    finishedInput = true;
                }
            } while (!finishedInput);
        } else if (choice == 2) {
            /*Random rand1 = new Random();
            int element1;
            for (int i = 0; i < size; i++) {
                element1 = rand1.nextInt(upperbound);
                bt.add(element1);
            }*/
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i=1; i<size*100; i++) list.add(i);
            Collections.shuffle(list);
            for (int i = 0; i < size; i++) {
                bt.add(list.get(i));
            }
        }
        return bt;
    }

    BinarySearchTree createAVLTree() {
        BinarySearchTree avlTree = new AVLTree();
        System.out.println("Выберите вариант заполнения начального дерева: ");
        System.out.println("1. Вручную");
        System.out.println("2. Рандом");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        //int size = 10;
        int upperbound = size * 100;
        if (choice == 1) {
            System.out.println("Чтобы закончить заполнение, введите exit");
            int element;
            boolean finishedInput = false;
            do {
                try
                {
                    element = keyboard.nextInt();
                    avlTree.add(element);
                } catch (Exception e) {
                    finishedInput = true;
                }
            } while (!finishedInput);
        } else if (choice == 2) {
            /*Random rand1 = new Random();
            int element1;
            for (int i = 0; i < size; i++) {
                element1 = rand1.nextInt(upperbound);
                avlTree.add(element1);
            }*/
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i=1; i<size*100; i++) list.add(i);
            Collections.shuffle(list);
            for (int i = 0; i < size; i++) {
                avlTree.add(list.get(i));
            }
        }
        return avlTree;
    }

  BinarySearchTree createRBTree() {
        RedBlackTree rbTree = new RedBlackTree();
        System.out.println("Выберите вариант заполнения начального дерева: ");
        System.out.println("1. Вручную");
        System.out.println("2. Рандом");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        //int size = 10;
        int upperbound = size * 100;
        if (choice == 1) {
            System.out.println("Чтобы закончить заполнение, введите exit");
            int element;
            boolean finishedInput = false;
            do {
                try
                {
                    element = keyboard.nextInt();
                    rbTree.add(element);
                } catch (Exception e) {
                    finishedInput = true;
                }
            } while (!finishedInput);
        } else if (choice == 2) {
            /*Random rand1 = new Random();
            int element1;
            for (int i = 0; i < size; i++) {
                element1 = rand1.nextInt(upperbound);
                rbTree.add(element1);
            }*/
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i=1; i<size*100; i++) list.add(i);
            Collections.shuffle(list);
            for (int i = 0; i < size; i++) {
                rbTree.add(list.get(i));
            }
        }
        return rbTree;
    }

    int chooseAction() {
        System.out.println();
        System.out.println("Выберите действие для работы с деревом: ");
        System.out.println("1. Добавить элемент");
        System.out.println("2. Удалить элемент");
        System.out.println("3. ВЫЙТИ");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        return choice;
    }
}

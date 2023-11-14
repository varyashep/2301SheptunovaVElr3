import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    Node root;

    // Insert node
    Node add(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.leftChild = add(current.leftChild, value);
        } else if (value > current.value) {
            current.rightChild = add(current.rightChild, value);
        } else { // if value already exists
            return current;
        }

        return current;
    }

    public void add(int value) {
        root = add(root, value);
    }

    // Search node
    boolean search(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        if (value < current.value) {
            return search(current.leftChild, value);
        } else {
            return search(current.rightChild, value);
        }
    }

    public boolean search(int value) {
        return search(root, value);
    }

    // Find min value
    int findMinValue(Node root) {
        if (root.leftChild == null) {
            return root.value;
        } else {
            return findMinValue(root.leftChild);
        }
    }

    // Delete node
    Node delete(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            }
            if (current.rightChild == null) {
                return current.leftChild;
            }
            if (current.leftChild == null) {
                return current.rightChild;
            }
            else {
                int minValue = findMinValue(current.rightChild);
                current.value = minValue;
                current.rightChild = delete(current.rightChild, minValue);
                return current;
            }
        }
        if (value < current.value) {
            current.leftChild = delete(current.leftChild, value);
            return current;
        }
        current.rightChild = delete(current.rightChild, value);
        return current;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    // Inorder traversal
    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.leftChild);
            System.out.print(" " + node.value);
            traverseInOrder(node.rightChild);
        }
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.leftChild);
            traversePreOrder(node.rightChild);
        }
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.leftChild);
            traversePostOrder(node.rightChild);
            System.out.print(" " + node.value);
        }
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(" " + node.value);
            if (node.leftChild != null) {
                nodes.add(node.leftChild);
            }
            if (node.rightChild != null) {
                nodes.add(node.rightChild);
            }
        }
    }



    public int getHeight(Node node) {
        if (root == null) {
            return 0;
        }
        else {
            int leftHeight = 0;
            int rightHeight = 0;

            if (node.leftChild != null) {
                leftHeight = getHeight(node.leftChild);
            }
            if (node.rightChild != null) {
                rightHeight = getHeight(node.rightChild);
            }
            int maxHeight = Math.max(leftHeight, rightHeight);
            return maxHeight + 1;
        }
    }

    public int getHeight() {
        int result = getHeight(root);
        return result;
    }
}

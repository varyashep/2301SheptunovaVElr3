public class AVLTree extends BinarySearchTree {

    public int getHeight(Node node) {
        if (node != null) {
            return node.height;
        }
        return -1;
    }

    public void updateHeight(Node node) {
        int leftChildHeight = getHeight(node.leftChild);
        int rightChildHeight = getHeight(node.rightChild);
        node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    private int balanceFactor(Node node) {
        return getHeight(node.rightChild) - getHeight(node.leftChild);
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.leftChild;
        node.leftChild = leftChild.rightChild;
        leftChild.rightChild = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.rightChild;
        node.rightChild = rightChild.leftChild;
        rightChild.leftChild = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    private Node rebalance(Node node) {
        int balanceFactor = balanceFactor(node);
        // left-heavy
        if (balanceFactor < - 1) {
            if (balanceFactor(node.leftChild) <= 0) {
                node = rotateRight(node);
            } else {
                node.leftChild = rotateLeft(node.leftChild);
                node = rotateRight(node);
            }
        }
        if (balanceFactor > 1) {
            if (balanceFactor(node.rightChild) >= 0) {
                node = rotateLeft(node);
            } else {
                node.rightChild = rotateRight(node.rightChild);
                node = rotateLeft(node);
            }
        }
        return node;
    }

    @Override
    Node add(Node node, int value) {
        node = super.add(node, value);
        updateHeight(node);
        return rebalance(node);
    }

    @Override
    Node delete(Node node, int value) {
        node = super.delete(node, value);

        if (node == null) {
            return null;
        }
        updateHeight(node);
        return rebalance(node);
    }
}

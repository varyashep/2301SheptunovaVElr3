import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree extends BinarySearchTree{

    private void replaceChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.leftChild == oldChild) {
            parent.leftChild = newChild;
        } else if (parent.rightChild == oldChild) {
            parent.rightChild = newChild;
        } else {
            throw new IllegalStateException("Incorrect relations");
        }
        if (newChild != null) {
            newChild.parent = parent;
        }
    }
    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.leftChild;
        node.leftChild = leftChild.rightChild;
        if (leftChild.rightChild != null) {
            leftChild.rightChild.parent =  node;
        }
        leftChild.rightChild = node;
        node.parent = leftChild;
        replaceChild(parent, node, leftChild);
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.rightChild;

        node.rightChild = rightChild.leftChild;
        if (rightChild.leftChild != null) {
            rightChild.leftChild.parent = node;
        }
        rightChild.leftChild = node;
        node.parent = rightChild;

        replaceChild(parent, node, rightChild);
    }

    private Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.leftChild == parent) {
            return grandparent.rightChild;
        } else if (grandparent.rightChild == parent) {
            return grandparent.leftChild;
        } else {
            throw new IllegalStateException("Incorrect relations");
        }
    }

    private void rebalance(Node node) {
        Node parent = node.parent;

        // Case 1: the root
        if (parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (parent.color == Color.BLACK) {
            return;
        }

        Node grandparent = parent.parent;
        Node uncle = getUncle(parent);

        // Case 2: if uncle is red, color swap
        if (uncle != null && uncle.color == Color.RED) {
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            uncle.color = Color.BLACK;
            rebalance(grandparent);
        }

        // Parent is a left child
        else if (parent == grandparent.leftChild) {
            // Case 3a: Uncle is black and node is left->right
            if (node == parent.rightChild) {
                rotateLeft(parent);
                parent = node;
            }
            // Case 4a: Uncle is black and node is left->left
            rotateRight(grandparent);
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
        }
        // Parent is a right child
        else {
            // Case 3b: Uncle is black and node is right->left
            if (node == parent.leftChild) {
                rotateRight(parent);
                parent = node;
            }
            // Case 4b: Uncle is black and node is right->right
            rotateLeft(grandparent);
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
        }
    }

    public void add(int value) {
        Node node = root;
        Node parent = null;

        while (node != null) {
            parent = node;
            if (value <= node.value) {
                node = node.leftChild;
            } else if (value > node.value) {
                node = node.rightChild;
            } /*else {
                throw new IllegalArgumentException("The tree contains a node with this key");
            }*/
        }

        Node newNode = new Node(value);
        newNode.color = Color.RED;
        if (parent == null) {
            root = newNode;
        } else if (value < parent.value) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;
        rebalance(newNode);
    }

    private Node getSibling(Node node) {
        Node parent = node.parent;
        if (node == parent.leftChild) {
            return parent.rightChild;
        } else if (node == parent.rightChild) {
            return parent.leftChild;
        } else {
            throw new IllegalStateException("Incorrect relations");
        }
    }

    private boolean isBlack(Node node) {
        return node == null || node.color == Color.BLACK;
    }

    private void redSibling(Node node, Node sibling) {
        // Color swap
        sibling.color = Color.BLACK;
        node.parent.color = Color.RED;
        if (node == node.parent.leftChild) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    private void blackSiblingOneRedChild(Node node, Node sibling) {
        boolean nodeIsLeftChild = (node == node.parent.leftChild);

        if (nodeIsLeftChild && isBlack(sibling.rightChild)) {
            sibling.leftChild.color = Color.BLACK;
            sibling.color = Color.RED;
            rotateRight(sibling);
            sibling = node.parent.rightChild;
        } else if (!nodeIsLeftChild && isBlack(sibling.leftChild)) {
            sibling.rightChild.color = Color.BLACK;
            sibling.color = Color.RED;
            rotateLeft(sibling);
            sibling = node.parent.leftChild;
        }

        sibling.color = node.parent.color;
        node.parent.color = Color.BLACK;
        if (nodeIsLeftChild) {
            sibling.rightChild.color = Color.BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.leftChild.color = Color.BLACK;
            rotateRight(node.parent);
        }
        }

    private void rebalanceAfterDeletion(Node node) {
        if (node == root) {
            node.color = Color.BLACK;
            return;
        }
        if (node.parent != null) {

            Node sibling = getSibling(node);

            // Red sibling
            if (sibling.color == Color.RED) {
                redSibling(node, sibling);
                sibling = getSibling(node);
            }

            // Black sibling with two black children
            if (isBlack(sibling.leftChild) && isBlack(sibling.rightChild)) {
                sibling.color = Color.RED;

                // Black sibling with two black children + red parent
                if (node.parent.color == Color.RED) {
                    node.parent.color = Color.BLACK;
                }
                // Black sibling with two black children + black parent
                else {
                    rebalanceAfterDeletion(node.parent);
                }
            }
            // Black sibling with at least one red child
            else {
                blackSiblingOneRedChild(node, sibling);
            }
        }
    }

    private Node deleteWithZeroOrOneChild(Node node) {
        if (node.leftChild != null) {
            replaceChild(node.parent, node, node.leftChild);
            return node.leftChild;
        }
        else if (node.rightChild != null) {
            replaceChild(node.parent, node, node.rightChild);
            return node.rightChild;
        }
        else {
            Node newChild;
            if (node.color == Color.BLACK) {
                newChild = new NilNode();
            } else {
                newChild = null;
            }
            return newChild;
        }
    }

    private Node findMin(Node node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    public void delete(int value) {
        Node node = root;

        while (node != null && node.value != value) {
            if (value < node.value) {
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
        }
        // Node not found
        if (node == null) {
            return;
        }

        Node movedUpNode;
        Color deletionNodeColor;

        if (node.leftChild == null || node.rightChild == null) {
            movedUpNode = deleteWithZeroOrOneChild(node);
            deletionNodeColor = node.color;
        }

        else {
            Node minNode = findMin(node.rightChild);
            node.value = minNode.value;

            movedUpNode = deleteWithZeroOrOneChild(minNode);
            deletionNodeColor = minNode.color;
        }
        if (deletionNodeColor == Color.BLACK) {
            rebalanceAfterDeletion(movedUpNode);
            if (movedUpNode.getClass() == NilNode.class) {
                replaceChild(movedUpNode.parent, movedUpNode, null);
            }
        }
    }

    @Override
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.println();
            System.out.print(" " + node.value + " | " + node.color);
            if (node.leftChild != null) {
                nodes.add(node.leftChild);
            }
            if (node.rightChild != null) {
                nodes.add(node.rightChild);
            }

        }
    }

    // Inorder traversal
    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.leftChild);
            System.out.println(" " + node.value + " | " + node.color);
            traverseInOrder(node.rightChild);
        }
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.println(" " + node.value + " | " + node.color);
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
            System.out.println(" " + node.value + " | " + node.color);
        }
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }
}

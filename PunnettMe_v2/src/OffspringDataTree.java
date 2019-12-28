import java.util.ArrayList;
import java.util.TreeMap;

public class OffspringDataTree {
    /**
     * Punnett Me is an app to use for Punnett Squares for Genetic Trait Calculations.
     * You can calculate up to 5 different traits at once.
     * You can quickly change from Homozygous parents and Heterozygous parents
     * for comparisons.
     *
     * Uses Design by Contract programming.
     * This AVL Tree stores Strings in a Node Object. As to keep from using lexicographical scoring,
     * each Node has a int score attribute. The scores are based on the position of the letter being
     * looked at. If we're evaluating five genes, and there's two letters per gene, that leaves
     * ten letters to each result. To give each variation a unique score, we're using the same
     * idea as binary in position valuation. Meaning the first position in the result aforementioned,
     * the score would be 2^9, or 512. For every character that is upper case, the score is applied.
     * Otherwise the score is not added to the results overall score. That way we can organize the
     * AVL Tree to order the Nodes from all lower case letters to all upper case letters.
     *
     * Scott Smalley, BS Software Engineering student at Utah Valley University
     * Fall 2020 expected graduation
     * scottsmalley90@gmail.com
     *
     * @author Scott Smalley
     */


    private Node root;
    private ArrayList<String> output;
    private TreeMap<String, Integer> outputTreeMap;
    private int countOfUniqueNodes;
    private int countOfDuplicateNodes;


    //Constructor
    public OffspringDataTree()
    {
        root = null;
        countOfUniqueNodes = 0;
        countOfDuplicateNodes = 0;
    }

    /**
     * Takes the input string, sends it to be scored,
     * checks it against the root to see if it's a duplicate.
     * If it's a duplicate it will simply increment the duplicate attribute
     * of Node. If it doesn't match, it will call checkForDuplicates() to
     * verify there isn't a duplicate in the tree. If it isn't a duplicate,
     * this method calls insert() to have it inserted into the tree.
     *
     * @param data String
     */
    public void storeInTree(String data)
    {
//            //Values are on a binary score system based on position in the String.
//            int score = scoreResult(data);

        if (root != null)
        {
            //In case root and score are the same value
            if (data.compareTo(root.data) == 0)
            {
                root.increment();
            }
            else
            {
                /*
                 * If a duplicate is found, it will return true and
                 * increment the counter for that Node in the traverseForDuplicates() method.
                 */
                if (!checkForDuplicates(data, root, false))
                {
                    //Insert in the tree.
                    insert(new Node(data), root);
                }
            }
        }
        else
        {
            //If no root, make this Node the root.
            root = new Node(data);
        }

    }

//        /**
//         * Scores the String using the same idea as binary.
//         * Based on position, if there's an Uppercase letter in
//         * said position, it adds to the overall score. If it's
//         * a lower case letter, it doesn't add to the score.
//         * If there's five Genes, meaning there's ten letters,
//         * value is descending from left to right. Meaning
//         * the first position adds 512 to the score, all the way
//         * down to adding 1 to the score in the last position.
//         *
//         * @param result String
//         * @return score integer
//         */
//        private int scoreResult(String result)
//        {
//            int score = 0;
//            int binary = result.length()-1;
//
//            for (int i = 0; i < result.length() && binary > -1; i++)
//            {
//                if (Character.isUpperCase(result.charAt(i)))
//                {
//                    //2^position adds to the score.
//                    //Meaning if there's 5 genes, the first position
//                    //would be 2^9 = 512.
//                    score += Math.pow(2, binary);
//                }
//                binary--;
//            }
//
//            return score;
//        }

    /**
     * Recursively dives into the AVL Tree to check for any duplicates.
     * Returns False if no duplicates.
     * If it finds a duplicate, it will increment that Node's duplicate attribute,
     * and return true.
     *
//         * @param score integer
     * @param root Node
     * @param hasDuplicate boolean
     * @return duplicate boolean
     */
    private boolean checkForDuplicates(String data, Node root, boolean hasDuplicate)
//        private boolean checkForDuplicates(int score, Node root, boolean hasDuplicate)
    {
        //Left subtree
        if (root.left != null)
        {
            if (root.left.data.compareTo(data) != 0)
            {
                checkForDuplicates(data, root.left, hasDuplicate);
            }
            else
            {
                root.left.increment();
//                hasDuplicate = true;
                return true;
            }
        }
        //Right subtree
        if (root.right != null)
        {
            if (root.right.data.compareTo(data) != 0)
            {
                checkForDuplicates(data, root.right, hasDuplicate);
            }
            else
            {
                root.right.increment();
//                hasDuplicate = true;
                return true;
            }
        }
        return hasDuplicate;
    }

    /**
     *Caller guarantees there are no duplicates and
     *there is a root Node. As it traverses the tree,
     *it recursively calls insert() onto any lower subtrees.
     *When it finds a null position, it adds it to the position.
     *It then updates its parent attribute.  When it's done adding
     *the Node, the method calls checkBalance() to make sure the tree
     *is still in balance.
     *
     * @param newNode Node
     * @param root Node
     */
    private void insert(Node newNode, Node root)
    {

        //Small on the left
        if (newNode.data.compareTo(root.data) < 0)
        {
            if (root.left != null)
            {
                insert(newNode, root.left);
            }
            else
            {
                root.left = newNode;
                newNode.parent = root;
            }
        }
        //Large on the right
        else if (newNode.data.compareTo(root.data) > 0)
        {
            if (root.right != null)
            {
                insert(newNode, root.right);
            }
            else
            {
                root.right = newNode;
                newNode.parent = root;
            }
        }

        checkBalance(newNode);
    }

    /**
     * Traverses the tree, adding one per height level it
     * reaches until it hits null and unwinds the stack.
     *
     * @param root Node
     * @return height integer
     */
    private int getHeight(Node root)
    {
        if (root == null)
        {
            return -1;
        }

        return (Math.max(getHeight(root.left), getHeight(root.right)) + 1);
    }

    /**
     * This method calls getHeight() to get the heights of
     * each subtree. If the difference between them
     * is greater than 1, then the tree is unbalanced at that
     * point and needs rebalancing. After calling rebalance on the
     * current Node. It will check this Node's parent to make sure
     * it's balanced as well.
     *
     * @param node Node
     */
    private void checkBalance(Node node)
    {
        if ((getHeight(node) > 1) || getHeight(node) < -1)
        {
            node = rebalance(node);
        }

        if (node.parent != null)
        {
            checkBalance(node.parent);
        }
    }

    /**
     * This method checks which type of unbalance is present.
     * Left Left Case, Left Right Case, Right Left Case, and
     * Right Right Case. Based on the case found, it will
     * rotate accordingly. After rotations, it will return the Node.
     *
     * @param node Node
     * @return node Node
     */
    private Node rebalance(Node node)
    {
        if (getHeight(node.left) - getHeight(node.right) > 1)
        {
            //Left left case.
            if (getHeight(node.left.left) > getHeight(node.left.right))
            {
                node = rotateRight(node);
            }
            //Left Right case.
            else
            {
                node = rotateLeftRight(node);
            }
        }
        else
        {
            //Right Left case.
            if (getHeight(node.right.left) > getHeight(node.right.right))
            {
                node = rotateRightLeft(node);
            }
            //Right Right case.
            else
            {
                node = rotateLeft(node);
            }
        }

        return node;
    }

    /**
     * Rotates the current Node counterclockwise around
     * the parent. As it rotates, the parents are updated,
     * and reconnected to the original Node's parent.
     *
     * @param node Node
     * @return node Node
     */
    private Node rotateLeft(Node node)
    {
        //To hold the connection to the AVL Tree.
        Node greatGrandParent = node.parent;

//            //Used to find where the original Node fit in the tree.
//            int oldChildScore = node.score;
        String oldChildData = node.data;

        //Rotation
        Node temp = node.right;
        node.right = temp.left;

        //If Node.right isn't null, we need to update
        //that Node's parent as it's being changed.
        if (node.right != null)
        {
            node.right.parent = node;
        }

        //Rotation
        temp.left = node;
        node.parent = temp;

        //Reattach new Node to the old parent.
        if (greatGrandParent != null)
        {
            temp.parent = greatGrandParent;
            //Short circuit incase .left is null so it won't throw an error.
            if (greatGrandParent.left != null && (greatGrandParent.left.data.compareTo(oldChildData) == 0))
            {
                greatGrandParent.left = temp;
            }
            //Short circuit incase .left is null so it won't throw an error.
            else if (greatGrandParent.right != null && (greatGrandParent.right.data.compareTo(oldChildData) == 0))
            {
                greatGrandParent.right = temp;
            }
        }
        //If the greatGrandParent was null, the Node
        //we rotated was the root, so now this new Node
        //is root.
        else
        {
            root = temp;
            temp.parent = null;
        }

        return temp;
    }

    /*
     * Rotates the Grandparent clockwise around the
     * parent.
     * @param root Node
     */
    private Node rotateRight(Node node)
    {
        //To hold the connection to the AVL Tree.
        Node greatGrandParent = node.parent;

//            //Used to find where the original Node fit in the tree.
//            int oldChildScore = node.score;
        String oldChildData = node.data;

        //Rotation
        Node temp = node.left;
        node.left = temp.right;

        //If Node.left isn't null, we need to update
        //that Node's parent as it's being changed.
        if (node.left != null)
        {
            node.left.parent = node;
        }

        //Rotation
        temp.right = node;
        node.parent = temp;

        //Reattach new Node to the old parent.
        if (greatGrandParent != null)
        {
            temp.parent = greatGrandParent;

            //Short circuit incase .left is null so it won't throw an error.
            if (greatGrandParent.left != null && (greatGrandParent.left.data.compareTo(oldChildData) == 0))
            {
                greatGrandParent.left = temp;
            }
            //Short circuit incase .left is null so it won't throw an error.
            else if (greatGrandParent.right != null && (greatGrandParent.right.data.compareTo(oldChildData) == 0))
            {
                greatGrandParent.right = temp;
            }
        }

        //If the greatGrandParent was null, the Node
        //we rotated was the root, so now this new Node
        //is root.
        else
        {
            root = temp;
            temp.parent = null;
        }

        return temp;
    }

    /**
     * Right Left Case requires two rotations, the
     * current Node and it's corresponding unbalanced Node.
     *
     * @param node Node
     * @return node Node
     */
    private Node rotateRightLeft(Node node)
    {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    /**
     * Left Right Case requires two rotations, the
     * current Node and it's corresponding unbalanced Node.
     *
     * @param node Node
     * @return node Node
     */
    private Node rotateLeftRight(Node node)
    {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    /**
     * Outward Facing get method to get
     * the offspring results.
     *
     * @return offspring List
     */
    public ArrayList<String> getOffspringArrayList()
    {
        output = new ArrayList<>();
        getInOrderTraversalArrayList(root);

        return output;
    }

    /**
     * Recursive method to conduct an
     * In Order Traversal, as it finds
     * Nodes, it will check it's duplicate
     * attribute to output multiples of the
     * same output.
     *
     * @param root Node
     */
    private void getInOrderTraversalArrayList(Node root)
    {
        if (root == null)
        {
            return;
        }
        getInOrderTraversalArrayList(root.left);

        //To check for duplicates, and output them
        //if they exist.
        for (int add = 0; add < root.getCount(); add++)
        {
            output.add(root.data);
        }
        getInOrderTraversalArrayList(root.right);
    }
    /**
     * Outward Facing get method to get
     * the offspring results.
     *
     * @return offspring List
     */
    public TreeMap<String, Integer> getOffspringTreeMap()
    {
        outputTreeMap = new TreeMap<>();
        getInOrderTraversalTreeMap(root);

        return outputTreeMap;
    }

    /**
     * Recursive method to conduct an
     * In Order Traversal, as it finds
     * Nodes, it will check it's duplicate
     * attribute to output multiples of the
     * same output.
     *
     * @param root Node
     */
    private void getInOrderTraversalTreeMap(Node root)
    {
        if (root == null)
        {
            return;
        }
        getInOrderTraversalTreeMap(root.left);

//        //To check for duplicates, and output them
//        //if they exist.
//        for (int add = 0; add <= root.getDuplicate(); add++)
//        {
//            output.add(root.data);
//        }
        outputTreeMap.put(root.data, root.getCount());
        getInOrderTraversalTreeMap(root.right);
    }

    /**
     * Returns the total number of nodes
     * in the Tree.
     * @return
     */
    public int getCountOfUniqueNodes(){
        return countOfUniqueNodes;
    }

    /**
     * Returns the total number of
     * Nodes in the Tree plus all
     * counts for each node.
     * @return
     */
    public int getCountOfTotalDataPoints(){
        return countOfUniqueNodes + countOfDuplicateNodes;
    }

    /**
     * Inner Class to be the abstract Object for use
     * in the AVL Tree.
     *
     * @author Scott Smalley
     */
    public class Node
    {
//            //For comparisons in AVL Tree
//            private int score;
        //For multiple duplicates, to keep AVL Tree without duplicates.
        private int count = 0;

        private String data;
        //Parent Node, Left Subtree, Right Subtree
        private Node parent, left, right;

        //Constructor
        private Node(String data)
//            private Node(int score, String data)
        {
//                this.score = score;
            this.data = data;
            count = 1;
            left = null;
            right = null;
            parent = null;
            countOfUniqueNodes++;
        }

        //To increment the duplicate attribute.
        private void increment()
        {
            count++;
            countOfDuplicateNodes++;
        }

        //Get method for duplicate attribute.
        private int getCount()
        {
            return count;
        }
    }
}

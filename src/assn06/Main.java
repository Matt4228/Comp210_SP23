package assn06;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post insertion root that is
        // returned might change because of the insertion

        //for (int i=0; i<50; i++) {
        //    avl_bst = avl_bst.insert((int) (Math.random()*100));
        //}
        avl_bst = avl_bst.insert(20);
        avl_bst = avl_bst.insert(11);
        avl_bst = avl_bst.insert(50);
        avl_bst = avl_bst.insert(4);
        avl_bst = avl_bst.insert(6);
        avl_bst = avl_bst.insert(15);
        avl_bst = avl_bst.insert(3);
        avl_bst = avl_bst.insert(16);
        avl_bst = avl_bst.insert(17);



        System.out.println(avl_bst.size());

        // Now insert 50 integers in increasing order which would
        // cause a simple BST to become very tall but for our
        // self-balancing tree won't be too bad.

        for (int i=0; i<50; i++) {
        //    avl_bst = avl_bst.insert(i);
        }

        System.out.println(avl_bst.size());
    }
}

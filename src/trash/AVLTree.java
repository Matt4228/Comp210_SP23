package trash;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Put your fields here.
    boolean _empty;
    T _value;
    AVLTree<T> _parent;
    AVLTree<T> _left;
    AVLTree<T> _right;
    int _size;
    int _height;

    public AVLTree() {
        // You code for constructor here.
        _empty = true;
        _value = null;
        _parent = null;
        _left = null;
        _right = null;
        _size = 0;
        _height = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
         return null;
     }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */ 
     
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
         return null;
     }

    @Override
    public boolean isEmpty() {
        return _empty;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
         //insert as root if tree is empty
         if(isEmpty()) {
            _value = element;
            _empty = false;
         } else {
            //right
            if(_value.compareTo(element) <= 0) {
                if(_right == null) {
                    System.out.println("bing right");
                    _right = new AVLTree<T>();
                    //balance();
                }
                _right.insert(element);
                _right.setParent(this);
            } else {
                if(_left == null) {
                    System.out.println("bonk left");
                    _left = new AVLTree<T>();
                    //balance();
                }
                _left.insert(element);
                _left.setParent(this);
            }
         }
        _size++;

        return findRootandHeight();
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        return null;
    }

    @Override
    public T findMin() {
        if(_left == null) {
            return _value;
        } else {
            return _left.findMin();
        }
    }

    @Override
    public T findMax() {
        if(_right == null) {
            return _value;
        } else {
            return _right.findMin();
        }
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public T getValue() {
        return null;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        return null;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        return null;
    }

    // Your code for public methods here.
    public void balance() {

    }

    public AVLTree<T> getParent() {
         return _parent;
    }

    public AVLTree<T> findRootandHeight() {
         if (getParent() == null) {
             return this;
         } else {
             return _parent.getParent();
         }
    }

    public void setParent(AVLTree<T> p) {
         _parent = p;
    }

}

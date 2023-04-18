package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateLeft() {
        // You should implement left rotation and then use this
        // method as needed when fixing imbalances.
        // TODO
        AVLTree<T> c1 = _right;
        AVLTree<T> c2 = (AVLTree<T>) _right.getLeft();

        c1.setLeft(this);
        _right = c2;

        return c1;
    }

    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateRight() {
        // You should implement right rotation and then use this
        // method as needed when fixing imbalances.
        // TODO
        AVLTree<T> c1 = _left;
        AVLTree<T> c2 = (AVLTree<T>) _left.getRight();

        c1.setRight(this);
        _left = c2;

        return c1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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
        // TODO


        if(isEmpty()) {
            _value = element;
            _height++;
            _size++;
            return this;
        }

        _size++;

        if(element.compareTo(_value) <= 0) {
            if(_left == null) {
                _left = new AVLTree<T>();
            }
            _left = (AVLTree<T>) _left.insert(element);
        } else {
            if(_right == null) {
                _right = new AVLTree<T>();
            }
            _right = (AVLTree<T>) _right.insert(element);
        }

        if(_left != null && _right != null) {
            _height = Math.max(_left.height(), _right.height()) + 1;
        } else if (_left != null){
            _height = _left.height() + 1;
        } else {
            _height = _right.height() + 1;
        }

        int balance = getBalance();
        System.out.println(_value + " " + balance);

        if (balance > 1 && element.compareTo(_left.getValue()) < 0) {
            return rotateRight();
        }

        if (balance < -1 && element.compareTo(_right.getValue()) > 0) {
            return rotateLeft();
        }

        if (balance > 1 && element.compareTo(_left.getValue()) > 0) {
            _left = _left.rotateLeft();
            return rotateRight();
        }

        if (balance < -1 && element.compareTo(_left.getValue()) < 0) {
            _right = _right.rotateRight();
            return rotateLeft();
        }
        return this;
    }

    public int getBalance() {
        if(_left != null && _right != null) {
            return _left.height() - _right.height();
        } else if (_left != null) {
            return _left.height();
        } else {
            return -1 * _right.height();
        }
    }



    @Override
    public SelfBalancingBST<T> remove(T element) {
        // TODO
        return null;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_left.isEmpty()) {
            return _value;
        } else {
            return _left.findMin();
        }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
        // TODO
        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

        return _right;
    }

    public void setLeft(AVLTree<T> left) {
        _left = left;
    }

    public void setRight(AVLTree<T> right) {
        _right = right;
    }



}


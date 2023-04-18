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
            _size++;
            _height++;
        } else {
            //System.out.println("ping");
            if(_value.compareTo(element) <= 0) {
                //System.out.println("ping right " + element);
                if(_right == null) {
                    _right = new AVLTree<T>();
                }
                _size++;
                _right.insert(element);

            } else {
                //System.out.println("ping left " + element);
                if(_left == null) {
                    //System.out.println("bonk");
                    _left = new AVLTree<T>();
                }
                _size++;
                _left.insert(element);
            }
        }

        return this;
    }

    public int getBalance() {
        if (isEmpty()) {
            return 0;
        } else {
            return _left.height() - _right.height();
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


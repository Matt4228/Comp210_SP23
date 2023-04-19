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
        AVLTree<T> r = _right;
        AVLTree<T> rl = (AVLTree<T>) _right.getLeft();

        r.setLeft(this);
        _right = rl;

        updateHeight();
        updateSize();
        r.updateSize();
        r.updateHeight();

        return r;
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
        AVLTree<T> l = _left;
        AVLTree<T> lr = (AVLTree<T>) _left.getRight();

        l.setRight(this);
        _left = lr;


        updateHeight();
        updateSize();
        l.updateSize();
        l.updateHeight();

        return l;
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
            _height++;
            _size++;
            _value = element;
            return this;
        }


        if(_value.compareTo(element) < 0) {
            if(_right == null) {
                _right = new AVLTree<T>();
            }
            _right = (AVLTree<T>) _right.insert(element);
        } else {
            if(_left == null) {
                _left = new AVLTree<T>();
            }
            _left = (AVLTree<T>) _left.insert(element);
        }
        //_size++;

        updateHeight();
        updateSize();

        int balance = findBalance();

        //imbalance primary right
        if (balance > 1) {
            if (element.compareTo(_right.getValue()) > 0) {
                return rotateLeft();
            } else {
                _right = _right.rotateRight();
                return rotateLeft();
            }
        } //imbalance primary left
        if (balance < -1) {
            if (element.compareTo(_left.getValue()) <= 0) {
                return rotateRight();
            } else {
                //System.out.println("pop");
                _left = _left.rotateLeft();
                return rotateRight();
            }

        }


        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        // TODO
        if (contains(element)) {
            _size--;
            if(_value.compareTo(element) == 0) {
                if (_left != null && _right != null) {
                    T replacement = _right.getValue();
                    _value = replacement;
                    _right = (AVLTree<T>) _right.remove(replacement);
                } else if (_left != null) {
                    return _left;
                } else if (_right != null) {
                    return _right;
                } else {
                    return null;
                }
            } else if(_value.compareTo(element) > 0) {
                _left = (AVLTree<T>) _left.remove(element);
            } else {
                _right = (AVLTree<T>) _right.remove(element);
            }
            updateHeight();

            int balance = findBalance();

            //imbalance primary right
            if (balance > 1) {
                if (element.compareTo(_right.getValue()) > 0) {
                    return rotateLeft();
                } else {
                    _right = _right.rotateRight();
                    return rotateLeft();
                }
            } //imbalance primary left
            if (balance < -1) {
                if (element.compareTo(_left.getValue()) <= 0) {
                    return rotateRight();
                } else {
                    //System.out.println("pop");
                    _left = _left.rotateLeft();
                    return rotateRight();
                }

            }

        }
        return this;
    }



    public AVLTree<T> removeHelper(AVLTree<T> root, T key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.getValue()) < 0) {
            root.setLeft(removeHelper((AVLTree<T>) root.getLeft(), key));
        } else if (key.compareTo(root.getValue()) > 0) {
            root.setRight(removeHelper((AVLTree<T>) root.getRight(), key));
        } else {
            if (root.getLeft() == null || root.getRight() == null) {
                AVLTree<T> temp = null;
                if (temp == root.getLeft()) {
                    temp = (AVLTree<T>) root.getRight();
                } else {
                    temp = (AVLTree<T>) root.getLeft();
                }

                if (temp == null) {
                    temp = root;
                    root = new AVLTree<T>();
                } else {
                    root = temp;
                }
            } else {
                AVLTree<T> temp = minVal((AVLTree<T>) root.getRight());
                root.setValue(temp.getValue());
                root.setRight(removeHelper((AVLTree<T>) root.getRight(), temp.getValue()));
            }
        }
        if (root == null) {
            return null;
        }

        root.updateHeight();
        root.updateSize();

        int balance = root.findBalance();

        if (balance > 1) {
            if (key.compareTo(root.getRight().getValue()) > 0) {
                return root.rotateLeft();
            } else {
                root.setRight(((AVLTree<T>) root.getRight()).rotateRight());
                return root.rotateLeft();
            }
        } //imbalance primary left
        if (balance < -1) {
            if (key.compareTo(root.getLeft().getValue()) <= 0) {
                return root.rotateRight();
            } else {
                //System.out.println("pop");
                root.setLeft(((AVLTree<T>) root.getLeft()).rotateLeft());
                return root.rotateRight();
            }

        }

        return root;
    }

    public AVLTree<T> minVal(AVLTree<T> root) {
        AVLTree<T> curr = root;
        while(curr.getLeft() != null) {
            curr = (AVLTree<T>) curr.getLeft();
        }
        return curr;
    }

    public void setValue(T val) {
        _value = val;
    }



    public AVLTree<T> findReplacementP() {
        if (_left == null) {
            return null;
        }
        AVLTree<T> minP = this;
        while(_left.getLeft() != null) {
            minP = (AVLTree<T>) minP.getLeft();
        }
        return minP;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_left == null) {
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
        if (_right == null) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
        // TODO
        if(_value == null) {
            return false;
        }
        if(_value.compareTo(element) == 0) {
            return true;
        } else if (_value.compareTo(element) > 0) {
            if(_left != null) {
                return _left.contains(element);
            } else {
                return false;
            }
        } else {
            if (_right != null) {
                return _right.contains(element);
            } else {
                return false;
            }
        }
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

    public void updateSize() {
        if (_right != null && _left != null) {
            _size = _left.size() + _right.size() + 1;
        } else if (_right != null) {
            _size = _right.size() + 1;
        } else if (_left != null){
            _size = _left.size() + 1;
        } else {
            _size = 1;
        }
    }

    public void updateHeight() {
        if(_right != null && _left != null) {
            _height = Math.max(_left.height(), _right.height()) + 1;
        } else if (_right != null) {
            _height = _right.height() + 1;
        } else if (_left != null) {
            _height = _left.height() + 1;
        } else {
            _height = 0;
        }
    }

    public int findBalance() {
        if(_right != null && _left != null) {
            //System.out.println(_value + ": " + (_right.height() - _left.height()));
            return _right.height() - _left.height();
        } else if (_left != null) {
            //System.out.println(_value + ": " + (_right.height() + 1));
            return -1 * _left.height() - 1;
        } else if (_right != null) {
            //System.out.println(_value + ":                 " + (-1* _left.height() - 1));
            return _right.height() + 1;
        } else {
            //System.out.println(_value + ": " + 0);
            return 0;
        }
    }


    public void setLeft(AVLTree<T> left) {
        _left = left;
    }

    public void setRight(AVLTree<T> right) {
        _right = right;
    }

}

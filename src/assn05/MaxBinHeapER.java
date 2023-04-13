package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (int i = 0; i < initialEntries.length; i++) {
            _heap.add(initialEntries[i]);
        }
        int lp = calcParent(size()-1);
        for (int i = lp; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    public void bubbleDown(int i) {
        Prioritized parent = _heap.get(i);
        if(calcRight(i) <= size() - 1) {
            Prioritized left = _heap.get(calcLeft(i));
            Prioritized right = _heap.get(calcRight(i));
            //target has a lower priority than right child
            if(parent.getPriority().compareTo(right.getPriority()) < 0) {
                //target has a lower priority than left child
                if(parent.getPriority().compareTo(left.getPriority()) < 0) {
                    //left child has a lower priority than right child
                    if(left.getPriority().compareTo(right.getPriority()) < 0) {
                        swap(i, calcRight(i));
                    } else {
                        swap(i, calcLeft(i));
                    }
                } else {
                    swap(i, calcRight(i));
                }
            } else {
                if(parent.getPriority().compareTo(left.getPriority()) < 0) {
                    swap(i, calcLeft(i));
                }
            }
        } else if (calcLeft(i)  == size() - 1) {
            Prioritized left = _heap.get(calcLeft(i));
            if(parent.getPriority().compareTo(left.getPriority()) < 0) {
                swap(i, calcLeft(i));
            }
        }
    }


    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        int i = size();
        _heap.add(new Patient(value, priority));
        bubbleUp(i);
    }

    // TODO: enqueue
    public void enqueue(V value) {
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (size() == 0) {
            return null;
        } else if (size() == 1) {
            Prioritized target = _heap.get(0);
            _heap.remove(0);
            return (V) target.getValue();
        } else {
            Prioritized target = _heap.get(0);
            swap(0, size()-1);
            _heap.remove(size()-1);
            resolve(0);
            return (V) target.getValue();
        }
    }

    public P testDequeue() {
        if (size() == 0) {
            return null;
        } else if (size() == 1) {
            Prioritized target = _heap.get(0);
            _heap.remove(0);
            return (P) target.getPriority();
        } else {
            Prioritized target = _heap.get(0);
            swap(0, size()-1);
            _heap.remove(size()-1);
            resolve(0);
            return (P) target.getPriority();
        }
    }

    // TODO: getMax
    @Override
    public V getMax() {
       if(size() > 0) {
           return _heap.get(0).getValue();
       } else {
           return null;
       }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    public int calcParent(int index) {
        return (index-1)/2;
    }

    public int calcLeft(int index) {
        return index * 2 + 1;
    }

    public int calcRight(int index) {
        return index * 2 + 2;
    }

    public void bubbleUp(int i) {
        if(i!=0) {
            Prioritized target = _heap.get(i);
            Prioritized parent = _heap.get(calcParent(i));
            if(parent.getPriority().compareTo(target.getPriority()) < 0) {
                swap(i, calcParent(i));
                bubbleUp(calcParent(i));
            }
        }

    }

    public void swap(int i, int j) {
        Prioritized temp = _heap.get(j);
        _heap.add(j, _heap.get(i));
        _heap.remove(j+1);
        _heap.add(i, temp);
        _heap.remove(i+1);
    }

    public void resolve(int i) {

        Prioritized target = _heap.get(i);
        //target has two children
        if(calcRight(i) <= size() - 1) {
            Prioritized left = _heap.get(calcLeft(i));
            Prioritized right = _heap.get(calcRight(i));
            //target has a lower priority than right child
            if(target.getPriority().compareTo(right.getPriority()) < 0) {
                //target has a lower priority than left child
                if(target.getPriority().compareTo(left.getPriority()) < 0) {
                    //left child has a lower priority than right child
                    if(left.getPriority().compareTo(right.getPriority()) < 0) {
                        swap(i, calcRight(i));
                        resolve(calcRight(i));
                    } else {
                        swap(i, calcLeft(i));
                        resolve(calcLeft(i));
                    }
                } else {
                    swap(i, calcRight(i));
                    resolve(calcRight(i));
                }
            } else {
                if(target.getPriority().compareTo(left.getPriority()) < 0) {
                    swap(i, calcLeft(i));
                    resolve(calcLeft(i));
                }
            }
        } else if (calcLeft(i)  == size() - 1) {
            Prioritized left = _heap.get(calcLeft(i));
            if(target.getPriority().compareTo(left.getPriority()) < 0) {
                swap(i, calcLeft(i));
            }
        }

    }











}

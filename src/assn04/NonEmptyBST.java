package assn04;


import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		if(element.compareTo(_element) < 0) {
			if(_left.isEmpty()) {
				_left = new NonEmptyBST<>(element);
			}
			else {
				_left.insert(element);
			}
		}
		else {
			if(_right.isEmpty()) {
				_right = new NonEmptyBST<>(element);
			}
			else {
				_right.insert(element);
			}
		}
		return this;
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (_element.compareTo(element) < 0) {
			_left = _left.remove(element);
		} else if (_element.compareTo(element) > 0) {
			_right = _right.remove(element);
		} else {
			if (_left.isEmpty()) {
				return _right;
			} else if (_right.isEmpty()) {
				return _left;
			}

			_element = _right.getElement();

			_right = _right.remove(_element);
		}
		return this;
	}



	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		//print root, left, right
		System.out.print(_element.toString() + " ");
		if(!_left.isEmpty()) {
			_left.printPreOrderTraversal();
		}
		if(!_right.isEmpty()) {
			_right.printPreOrderTraversal();
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if(!_left.isEmpty()) {
			_left.printPostOrderTraversal();
		}
		if(!_right.isEmpty()) {
			_right.printPostOrderTraversal();
		}
		System.out.print(_element.toString() + " ");
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
		for (int i = 0; i <= getHeight(); i++) {
			BFTHelper(this, i);
		}

	}

	public void BFTHelper(BST<T> node, int depth) {
		if (depth == 0) {
			System.out.print(node.getElement().toString() + " ");
		}
		else {
			if(!node.getLeft().isEmpty()){
				BFTHelper(node.getLeft(), depth - 1);
			}
			if(!node.getRight().isEmpty()){
				BFTHelper(node.getRight(), depth - 1);
			}
		}
	}


	// GetHeight of A Tree

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}

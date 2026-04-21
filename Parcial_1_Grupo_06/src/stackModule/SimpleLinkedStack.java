package stackModule;

// Nodo para la pila enlazada
class StackNode<E> {
	E element;
	StackNode<E> next;

	public StackNode(E element) {
		this.element = element;
		this.next = null;
	}
}

public class SimpleLinkedStack<E> implements SimpleStack<E> {

	private StackNode<E> top;
	private int size;

	public SimpleLinkedStack() {
		this.top = null;
		this.size = 0;
	}

	@Override
	public void push(E element) {
		StackNode<E> newNode = new StackNode<>(element);
		newNode.next = top;
		top = newNode;
		size++;
	}

	@Override
	public E pop() {
		if (isEmpty()) return null;
		E element = top.element;
		top = top.next;
		size--;
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) return null;
		return top.element;
	}

	@Override
	public void clear() {
		top = null;
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.top == null;
	}
}

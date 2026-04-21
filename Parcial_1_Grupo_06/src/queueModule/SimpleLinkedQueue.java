package queueModule;

// Nodo para la cola enlazada
class QueueNode<E> {
	E element;
	QueueNode<E> next;
	QueueNode<E> prev;

	public QueueNode(E element) {
		this.element = element;
		this.next = null;
		this.prev = null;
	}
}

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

	private QueueNode<E> head; // frente de la cola (donde se hace dequeue)
	private QueueNode<E> tail; // final de la cola (donde se hace enqueue)
	private int size;

	public SimpleLinkedQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	@Override
	public void enqueue(E element) {
		QueueNode<E> newNode = new QueueNode<>(element);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) return null;
		E element = head.element;
		head = head.next;
		if (head != null) {
			head.prev = null;
		} else {
			tail = null;
		}
		size--;
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) return null;
		return head.element;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.head == null;
	}
}

package listModule;
class Node<E>{
	E element; 
	Node<E> next;
	Node<E> prev;
	
public Node(E element) {
	this.element = element;
	this.next = null;
	this.prev = null;
}

}
public class SimpleLinkedList<E> implements SimpleList <E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public SimpleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		
	}

	@Override
	public boolean add(E element) {
		Node<E> newNode = new Node<>(element);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;	
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
			
		}
		size ++;
		return true;
		
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (index == size) {
			add(element);
			return;
		}
		
		Node<E> newNode = new Node<>(element);
		
		if(index == 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		} else {
			Node <E> b = getNode(index);
			Node <E> a = b.prev; //insertando entre a y b, luego hacemos la previa entre c y a
			newNode.prev = a;
			newNode.next = b;
			b.prev = newNode;
			a.next = newNode;
			
			
		}
		
		size ++;
		
		
	}

	private Node<E> getNode(int index) {
		if (index < 0 || index >= size || head == null) {
			return null;
		}
		Node<E> current = head;
		for(int i = 0; i < index; i ++) {
			if (current == null) return null;
			current = current.next;
		}
		return current;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		Node<E> target = getNode(index);
		E element = target.element;
		if (target == head) {
			head = target.next;
			if (head != null) head.prev = null;
		} else if (target == tail) {
			tail = target.prev;
			if(tail != null) tail.next = null; 
		} else {
			target.prev.next = target.next;
			target.next.prev = target.prev;
		}
		size --;
		if (size == 0) tail = null;
		return element;
		
		
		
	}

	@Override
	public boolean remove(Object object) {
		Node<E> current = head;
		while (current != null) {
			if (current.element.equals(object)) {
				if (current == head) head = current.next;
				if (current == tail) tail = current.prev;
				if (current.prev != null) current.prev.next = current.next;
				if(current.next != null) current.next.prev = current.prev;
				size --;
				return true;
			}
			current = current.next;
		}
		return false;   //si ninguna de las condiciones se cumple con este sale del while 
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
		
	}

	@Override
	public boolean contains(Object object) {
		Node <E> current = head;
		while(current != null) {
			if (current.element.equals(object)) return true;
			current = current.next;
		}
		return false;
	}

	@Override
	public E get(int index) {
		Node<E> node = getNode(index);
		if (node == null) {
			return null;
		}
		return node.element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> target = getNode(index);
		E oldElement = target.element;
		target.element = element;
		return oldElement;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.head == null;
	}

}

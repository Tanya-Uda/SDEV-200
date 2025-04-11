import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Exercise24_03 {
  public static void main(String[] args) {
    TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
    System.out.print("Enter five numbers: ");
    try (Scanner input = new Scanner(System.in)) {
        double[] v = new double[5];
        for (int i = 0; i < 5; i++) 
          v[i] = input.nextDouble();

        list.add(v[1]);
        list.add(v[2]);
        list.add(v[3]);
        list.add(v[4]);
        list.add(0, v[0]);
    }
    list.add(2, 10.55);
    list.remove(3);

    java.util.ListIterator<Double> iterator1 = list.listIterator();
    System.out.print("The list in forward order: ");
    while (iterator1.hasNext())
      System.out.print(iterator1.next() + " ");

    java.util.ListIterator<Double> iterator2 = list.listIterator(list.size() - 1);
    System.out.print("\nThe list in backward order: ");
    while (iterator2.hasPrevious())
      System.out.print(iterator2.previous() + " ");
  }
}

interface MyList<E> extends java.util.Collection<E> {
  public void add(int index, E e);
  public E get(int index);
  public int indexOf(Object e);
  public int lastIndexOf(E e);
  public E remove(int index);
  public E set(int index, E e);
  
  @Override
  public default boolean add(E e) {
    add(size(), e);
    return true;
  }

  @Override
  public default boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public default boolean remove(Object e) {
    if (indexOf(e) >= 0) {
      remove(indexOf(e));
      return true;
    }
    return false;
  }

  @Override
  public default boolean containsAll(Collection<?> c) {
    return true;
  }

  @Override
  public default boolean addAll(Collection<? extends E> c) {
    return true;
  }

  @Override
  public default boolean removeAll(Collection<?> c) {
    return true;
  }

  @Override
  public default boolean retainAll(Collection<?> c) {
    return true;
  }

  @Override
  public default Object[] toArray() {
    return null;
  }

  @Override
  public default <T> T[] toArray(T[] array) {
    return null;
  }
}

class TwoWayLinkedList<E> implements MyList<E> {
  private Node<E> head, tail;
  private int size;

  public TwoWayLinkedList() {}

  public TwoWayLinkedList(E[] objects) {
      addAll(Arrays.asList(objects));
  }

  public E getFirst() {
    if (size == 0) return null;
    return head.element;
  }

  public E getLast() {
    if (size == 0) return null;
    return tail.element;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");
    Node<E> current = head;
    while (current != null) {
      result.append(current.element);
      current = current.next;
      if (current != null) result.append(", ");
      else result.append("]");
    }
    return result.toString();
  }

  @Override
  public void clear() {
    head = tail = null;
  }

  @Override
  public boolean contains(Object e) {
    Node<E> current = head;
    while (current != null) {
      if (current.element.equals(e)) return true;
      current = current.next;
    }
    return false;
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    Node<E> current = head;
    for (int i = 0; i < index; i++) current = current.next;
    return current.element;
  }

  @Override
  public int indexOf(Object e) {
    Node<E> current = head;
    int index = 0;
    while (current != null) {
      if (current.element.equals(e)) return index;
      current = current.next;
      index++;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object e) {
    Node<E> current = tail;
    int index = size - 1;
    while (current != null) {
      if (current.element.equals(e)) return index;
      current = current.previous;
      index--;
    }
    return -1;
  }

  @Override
  public E set(int index, E e) {
    if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    Node<E> current = head;
    for (int i = 0; i < index; i++) current = current.next;
    E oldElement = current.element;
    current.element = e;
    return oldElement;
  }

  private class LinkedListIterator implements ListIterator<E> {
    private Node<E> current = head;

    public LinkedListIterator() {}

    public LinkedListIterator(int index) {
      if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
      current = head;
      for (int i = 0; i < index; i++) current = current.next;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public boolean hasPrevious() {
      return current != null;
    }

    @Override
    public E previous() {
      E e = current.element;
      current = current.previous;
      return e;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void add(E e) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void set(E e) {
      current.element = e;
    }

    @Override
    public int nextIndex() {
      throw new UnsupportedOperationException();
    }

    @Override
    public int previousIndex() {
      throw new UnsupportedOperationException();
    }
  }

  @SuppressWarnings("hiding")
  private class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E e) {
      element = e;
    }
  }

  @Override
  public int size() {
    return size;
  }

  public ListIterator<E> listIterator() {
    return new LinkedListIterator();
  }

  public ListIterator<E> listIterator(int index) {
    return new LinkedListIterator(index);
  }

  @Override
  public void add(int index, E e) {
    if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    if (index == 0) addFirst(e);
    else if (index == size) addLast(e);
    else {
      Node<E> current = head;
      for (int i = 0; i < index; i++) current = current.next;
      Node<E> newNode = new Node<>(e);
      newNode.previous = current.previous;
      newNode.next = current;
      current.previous.next = newNode;
      current.previous = newNode;
      size++;
    }
  }

  public void addFirst(E e) {
    Node<E> newNode = new Node<>(e);
    newNode.next = head;
    if (head != null) head.previous = newNode;
    head = newNode;
    if (tail == null) tail = head;
    size++;
  }

  public void addLast(E e) {
    Node<E> newNode = new Node<>(e);
    newNode.previous = tail;
    if (tail != null) tail.next = newNode;
    tail = newNode;
    if (head == null) head = tail;
    size++;
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    if (index == 0) return removeFirst();
    if (index == size - 1) return removeLast();

    Node<E> current = head;
    for (int i = 0; i < index; i++) current = current.next;
    current.previous.next = current.next;
    current.next.previous = current.previous;
    size--;
    return current.element;
  }

  public E removeFirst() {
    if (size == 0) return null;
    E element = head.element;
    head = head.next;
    if (head != null) head.previous = null;
    size--;
    return element;
  }

  public E removeLast() {
    if (size == 0) return null;
    E element = tail.element;
    tail = tail.previous;
    if (tail != null) tail.next = null;
    size--;
    return element;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedListIterator();
  }
}
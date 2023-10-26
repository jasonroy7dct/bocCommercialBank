package datastructure;

import java.util.Comparator;
import java.util.function.Predicate;

// Generics for putting different object, put User here
public class LinkedList<E> {

  // first of the list
  Node<E> head;
  // last of the list
  Node<E> tail;
  // initial size of list
  int size;

  // constructor
  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // inner class must be static
  static class Node<E> {

    E value;
    Node<E> next;
    Node<E> prev;

    // constructor
    public Node(E value) {
      this.value = value;
      this.next = null;
      this.prev = null;
    }
  }

  public int size() {
    return size;
  }

  public void add(E e) {
    Node<E> newNode = new Node<>(e);
    // if the list is empty, make new node as the head
    if (head == null) {
      head = newNode;
    } else {
      // node is not empty
      Node<E> current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    // add size
    size++;
  }

  public E get(int index) {
    checkElementIndex(index);

    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.value;
  }

  public E remove(int index) {
    checkElementIndex(index);

    // if delete first data
    if (index == 0) {
      removeFirst();
    }

    Node<E> current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.next;
    }

    // remove data, unlink it
    E removedData = current.next.value;
    // link to the next next node
    current.next = current.next.next;
    size--;
    return removedData;
  }

  // directly remove the first node from link list
  public E removeFirst() {
    if (size == 0) {
      return null;
    }
    Node<E> current = head;
    head = head.next;
    current.next = null;
    size--;
    if (size == 0) {
      tail = null;
    }
    return current.value;
  }

  // directly remove the last node from link list
  public E removeLast() {
    if (size == 0) {
      return null;
    }
    Node<E> current = head;
    Node<E> prev = head;
    while (current.next != null){
      prev = current;
      current = current.next;
    }
    tail = prev;
    tail.next = null;
    size--;
    if (size == 0) {
      head = null;
      tail = null;
    }
    return current.value;
  }

  public boolean removeIf(Predicate<? super E> filter) {
    boolean isRemoved = false;

    while (head != null && filter.test(head.value)) {
      head = head.next;
      size--;
      isRemoved = true;
    }

    Node<E> current = head;
    Node<E> previous = null;

    while (current != null) {
      if (filter.test(current.value)) {
        previous.next = current.next;
        size--;
        isRemoved = true;
      } else {
        previous = current;
      }
      current = current.next;
    }

    return isRemoved;
  }

  public boolean isEmpty() {
    return size == 0;
  }


  public void sort(Comparator<? super E> e) {
    //if size less than 1, no need to sort
    if (size > 1) {
      // initial sorted list to null
      Node<E> sortedList = null;

      Node<E> current = head;
      while (current != null) {
        Node<E> next = current.next;

        // if the sorted list is empty or the current data is less than the first element of the sorted list.
        if (sortedList == null || e.compare(current.value, sortedList.value) <= 0) {
          // add the current node at the beginning.
          current.next = sortedList;
          // update the sorted list head
          sortedList = current;
        } else {
          // find the correct position to insert the current node within the sorted list.
          Node<E> prev = sortedList;
          while (prev.next != null && e.compare(current.value, prev.next.value) > 0) {
            prev = prev.next;
          }
          // add the current node.
          current.next = prev.next;
          prev.next = current;
        }

        // add to the next node in the original list
        current = next;
      }

      // update the head of the LinkedList to the sorted list.
      head = sortedList;
    }
  }

  private void indexOutOfBoundsException(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
  }

  private void checkElementIndex(int index) {
    if (!isElementIndex(index)) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }
}

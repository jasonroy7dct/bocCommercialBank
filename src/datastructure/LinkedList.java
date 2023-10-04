package datastructure;

public interface LinkedList<E> {

  int size();

  void addFront(E value);

  void addBack(E value);

  E get(int index);

  E getFirst();

  E getLast();

  void removeFirst();

  void removeLast();

  void removeValue(E value);

  void clear();


}

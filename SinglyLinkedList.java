package ua.com.juja.jujasqlcmd.testing;

/*В классе SinglyLinkedList реализован односвязный список.
В этом классе реализован интерфейс Iterator.
Необходимо реализовать поиск K-элемента с конца списка при помощи Iterator.
Для этого нужно реализовать метод Integer findElement(SinglyLinkedList singleList, int k)
На вход принимает экземпляр SinglyLinkedList и номер элемента который необходимо найти с конца списка. Отсчет элементов с конца начинается с 0.
На выходе метод возвращает найденный элемент, если элемента не существует - IndexOutOfBoundsException.*/

import java.util.*;

public class SinglyLinkedList <T> {
    private Node<T> first = null;

    public void add(T element) {
        if (first == null) {
            first = new Node<T>(element);

        } else {
            Node<T> current = getLast();
            current.next = new Node<T>(element);
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) throw new IndexOutOfBoundsException();
            if (current.element == null) throw new NoSuchElementException();
            T element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Node<T> getLast() {
        Node<T> last = first;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }

    private class Node<T> {
        T element;
        Node<T> next;

        Node(T element) {
            this.element = element;
            this.next = null;
        }
    }

   }

class FinderElements {
    public static Integer findElement(SinglyLinkedList<Integer> singlyLinkedList, int k) {
         /*BODY*/
    int size = 0;
    int result = 0;
    int count = 0;
    int a = size-k;

        Iterator iterator = singlyLinkedList.iterator();
        while (iterator.hasNext()){
                iterator.next();
                size++;
                }

        Iterator iterator1 = singlyLinkedList.iterator();
        while (count <= a){
            count++;
            int b = (Integer)iterator1.next();
            if(count == a){
                result = b;
                size = result;
            }
        }
            if(result != size){
                throw new ArrayIndexOutOfBoundsException();
            }
        return result;
    }

    //cheking tests
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(2);

        Integer actualElement = null;

        try {
            actualElement = FinderElements.findElement(list, 4);
            if (actualElement == null)
                throw new AssertionError("result findElement()  is not null");
        } catch (IndexOutOfBoundsException e) {
            // expected behaviour
        }

        try {
            actualElement = FinderElements.findElement(list, -4);
            if (actualElement == null)
                throw new AssertionError("result findElement()  is not null");
        } catch (IndexOutOfBoundsException e) {
            // expected behaviour
        }

        if (actualElement != null)
            throw new AssertionError("list is empty and expected ArrayIndexOutOfBoundsException  but found" + actualElement.toString());

        System.out.print("OK");
      /* SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        Integer expectedElement = 7;
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(11);
        list.add(34);
        list.add(18);


        Integer actualElement = FinderElements.findElement(list, 3);

        if (actualElement == null)
            throw new AssertionError("result findElement()  is not null");

        if (expectedElement.compareTo(actualElement) != 0)
            throw new AssertionError("expected element equals " + expectedElement.toString() + " but found " + actualElement.toString());

        System.out.print("OK");*/

    }

}


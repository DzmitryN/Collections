
import java.util.Iterator;
import java.util.NoSuchElementException;


    public class SimpleLinkedList <E> implements SimpleList1<E> {
        private Node<E> first = null; // head
        private Node<E> last = null; // tail
        private int size = 0;

        private static class Node<T> {
            private Node <T> prev;
            private T item;
            private Node <T> next;

            private Node(Node<T> prev, T item, Node<T> next) {
                this.prev = prev;
                this.item = item;
                this.next = next;
            }
        }

        private void checkIndex(int index) {
            if (index < 0 || size < index) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        private Node<E> node(int index) {
            if (index < size / 2) {
                Node<E> tmp = first;
                for (int i = 0; i < index; i++) {
                    tmp = tmp.next;
                }
                return tmp;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--) {
                    x = x.prev;
                }
                return x;
            }
        }
        private E unlink(Node<E> x) {
            final E element = x.item;
            final Node<E> next = x.next;
            final Node<E> prev = x.prev;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }

            x.item = null;
            size--;
            return element;
        }

        @Override
        public boolean add(E newElement) {
            final Node<E> tmp = last;
            final Node<E> newNode = new Node<>(tmp, newElement, null);
            last = newNode;
            if (tmp == null) {
                first = newNode;
            } else {
                tmp.next = newNode;
            }
            size++;
            return true;
        }

        @Override
        public E get(int index) {
            checkIndex(index);
            return node(index).item;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public E remove(int index) {
            checkIndex(index);
            return unlink(node(index));
        }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {
                @Override
                public boolean hasNext() {
                    boolean bln =false;
                    if(first!=null){
                        bln = true;
                    }

                    return bln;

                }

                @Override
                public E next() {
                    if(first == null){
                        throw new NoSuchElementException();
                    }

                    if ( first.next == null ) {
                        E value = first.item;
                        first = first.next;

                        return value;
                    }

                    first = first.next;
                    return first.prev.item;

                   }

                @Override
                public void remove() {
                    if( first.prev == null ) {
                        throw new IllegalStateException();
                    }

                    unlink(first);
                }
            };
        }

        @Override
        public String toString() {
            String data = "[";

            if ( size == 0 ) {
               return "[]";
            }

                Node node = first;
                for (int i = 0; i < size - 1; i++) {
                    data += node.item.toString() + ", ";
                    node = node.next;
                }

                data += last.item + "]";

            return data;
        }

        @Override
        public boolean equals(Object object) {
            if ( object instanceof SimpleLinkedList ) {
                SimpleLinkedList<E> simpleLinkedList = (SimpleLinkedList<E>)object;

                if ( size != simpleLinkedList.size ) {
                    return false;
                }

                for ( int i = 0; i < size; i++ ) {
                    if (!get(i).equals(simpleLinkedList.get(i)) ) {
                        return false;
                    }
                }

                return true;
            }

            return false;
        }

        @Override
        public int hashCode() {
            int result = 0;

            if ( size > 0) {
                for (Node node = first; node.next != null; node = node.next) {
                    result += node.item.hashCode();
                }
            }

            return result;
        }

    }

interface SimpleList1<T> {
    public boolean add(T newElement);
    public T get(int index);
    public Iterator<T> iterator();
    public int size();
    public boolean isEmpty();
    public T remove(int index);
}
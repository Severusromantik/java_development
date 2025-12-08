package lab_6;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.lang.reflect.Array;

/**
 * Типізована колекція (Set) на базі двозв'язного списку.
 * @param <E> тип елементів (має бути Flower або його нащадки)
 */
public class FlowerSet<E> implements Set<E> {

    /**
     * Внутрішній статичний клас, що описує вузол двозв'язного списку.
     * Зберігає елемент та посилання на попередній і наступний вузли.
     * @param <E> тип елемента, що зберігається у вузлі
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> head; // Початок списку
    private Node<E> tail; // Кінець списку
    private int size = 0; // Поточна кількість елементів


    // Цей метод (конструктор) створює порожню колекцію без елементів.
    public FlowerSet() {}

    // Цей метод (конструктор) створює колекцію та одразу додає в неї один переданий об'єкт.
    public FlowerSet(E element) {
        add(element);
    }

    // Цей метод (конструктор) створює колекцію, додаючи в неї всі елементи з іншої стандартної колекції.
    public FlowerSet(Collection<? extends E> collection) {
        addAll(collection);
    }


    @Override
    // Цей метод повертає поточну кількість елементів у колекції.
    public int size() {
        return size;
    }

    @Override
    // Цей метод перевіряє, чи порожня колекція (повертає true, якщо розмір 0).
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    // Цей метод перевіряє, чи міститься заданий об'єкт у колекції, перебираючи список вузлів.
    public boolean contains(Object o) {
        for (Node<E> x = head; x != null; x = x.next) {
            // Використовуємо equals для порівняння вмісту, або перевіряємо null
            if (o == null ? x.item == null : o.equals(x.item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    // Цей метод додає елемент у колекцію тільки якщо такого елемента ще немає (забезпечує унікальність).
    public boolean add(E e) {
        // Головна логіка Set: спочатку перевіряємо наявність
        if (contains(e)) {
            return false;
        }
        linkLast(e); // Додаємо в кінець списку, якщо елемент унікальний
        return true;
    }

    @Override
    // Цей метод шукає заданий об'єкт у списку і, якщо знаходить, видаляє відповідний вузол.
    public boolean remove(Object o) {
        for (Node<E> x = head; x != null; x = x.next) {
            if (o == null ? x.item == null : o.equals(x.item)) {
                unlink(x); // Розриваємо зв'язки вузла
                return true;
            }
        }
        return false;
    }

    @Override
    // Цей метод повністю очищає колекцію, видаляючи посилання на всі вузли (допомагає збірнику сміття).
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }


    @Override
    // Цей метод створює та повертає ітератор для обходу колекції (дозволяє використовувати цикл foreach).
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> lastReturned;
            private Node<E> nextNode = head;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                if (nextNode == null) throw new NoSuchElementException();
                lastReturned = nextNode;
                nextNode = nextNode.next;
                return lastReturned.item;
            }
        };
    }


    // Цей метод (приватний) технічно додає новий вузол з даними в кінець двозв'язного списку.
    private void linkLast(E e) {
        final Node<E> l = tail;
        final Node<E> newNode = new Node<>(l, e, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    // Цей метод (приватний) видаляє конкретний вузол зі списку, "зшиваючи" його сусідів між собою.
    private void unlink(Node<E> x) {
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            head = next; // Якщо видаляємо голову
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev; // Якщо видаляємо хвіст
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
    }


    @Override
    // Цей метод перетворює колекцію в масив об'єктів (Object[]).
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    // Цей метод перетворює колекцію в типізований масив переданого типу.
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = head; x != null; x = x.next)
            result[i++] = x.item;
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    // Цей метод перевіряє, чи містяться всі елементи з переданої колекції в нашій колекції.
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e)) return false;
        return true;
    }

    @Override
    // Цей метод додає всі елементи з переданої колекції в нашу (ігноруючи ті, що вже є).
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e)) modified = true;
        return modified;
    }

    @Override
    // Цей метод залишає в колекції тільки ті елементи, які також присутні в переданій колекції (перетин).
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    // Цей метод видаляє з нашої колекції всі елементи, які є в переданій колекції.
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object e : c)
            if (remove(e)) modified = true;
        return modified;
    }
}
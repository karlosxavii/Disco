package Disco;

// Implementación simple de lista enlazada genérica
public class Listaenlazada<T> {
    private Nodo<T> head;

    public Listaenlazada() {
        head = null;
    }

    public void addFirst(T value) {
        Nodo<T> n = new Nodo<>(value);
        n.next = head;
        head = n;
    }

    public void addLast(T value) {
        Nodo<T> n = new Nodo<>(value);
        if (head == null) {
            head = n;
            return;
        }
        Nodo<T> cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = n;
    }

    public T removeFirst() {
        if (head == null) return null;
        T val = head.data;
        head = head.next;
        return val;
    }

    public T removeLast() {
        if (head == null) return null;
        if (head.next == null) {
            T val = head.data;
            head = null;
            return val;
        }
        Nodo<T> cur = head;
        while (cur.next.next != null) cur = cur.next;
        T val = cur.next.data;
        cur.next = null;
        return val;
    }

    public boolean contains(T value) {
        Nodo<T> cur = head;
        while (cur != null) {
            if (cur.data.equals(value)) return true;
            cur = cur.next;
        }
        return false;
    }

    public void print() {
        if (head == null) {
            System.out.println("Lista vacía.");
            return;
        }
        System.out.print("Lista: ");
        Nodo<T> cur = head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * Retorna el número de elementos dentro de la lista.
     */
    public int size() {
        int count = 0;
        Nodo<T> cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * Permite ejecutar una acción sobre cada elemento de la lista.
     */
    public void forEach(java.util.function.Consumer<T> action) {
        Nodo<T> cur = head;
        while (cur != null) {
            action.accept(cur.data);
            cur = cur.next;
        }
    }
}

package Disco;

// Nodo genérico simple para lista enlazada
public class Nodo<T> {
    public T data;
    public Nodo<T> next;

    public Nodo(T data) {
        this.data = data;
        this.next = null;
    }
}

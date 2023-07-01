//Integrantes
//Gabriela Vanesa Martell Villanueva
//Ariel Dicson Taco Huaman

public class AVL<E extends Comparable<E>> {
    class Node {
        protected E data;
        protected Node left;
        protected Node right;
        protected int fb; 

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.fb = 0;
        }

        public Node(E data) {
            this(data, null, null);
        }
    }

    private Node root;
    private boolean height;
    private E lastData;

    public AVL() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insertRecursive(x, this.root);
    }

    private Node insertRecursive(E x, Node current) throws ItemDuplicated {
        Node res = current;
        if (current == null) {
            res = new Node(x);
            this.height = true;
        } else {
            int resC = current.data.compareTo(x);
            if (resC == 0) throw new ItemDuplicated("El dato " + x + " ya fue insertado antes");
            if (resC < 0) {
                res.right = insertRecursive(x, current.right);
                // Se recalcula el fb de cada nodo por el que se ha transitado después de insertar el nuevo nodo
                if (this.height) {
                    switch (res.fb) {
                        case -1:
                            res.fb = 0;
                            this.height = false;
                            break;
                        case 0:
                            res.fb = 1;
                            this.height = true;
                            break;
                        case 1:
                            res = balanceToLeft(res);
                            this.height = false;
                            break;
                    }
                }
            } else {
                res.left = insertRecursive(x, current.left);
                if (this.height) {
                    switch (res.fb) {
                        case 1:
                            res.fb = 0;
                            this.height = false;
                            break;
                        case 0:
                            res.fb = -1;
                            this.height = true;
                            break;
                        case -1:
                            res = balanceToRight(res);
                            this.height = false;
                            break;
                    }
                }
            }
        }
        return res;
    }

    private Node balanceToLeft(Node father) {
        Node son = father.right;
        switch (son.fb) {
        	// Porción de código 01
            case 0: {
                father.fb = 1;
                son.fb = -1;
                father = rotateSL(father);
            }
            break;
            // Porción de código 02
            case 1:
                father.fb = 0;
                son.fb = 0;
                father = rotateSL(father);
                this.height = false;
                break;
             // Porción de código 03
            case -1:
                Node grandson = son.left;
                switch (grandson.fb) {
                    case 0:
                        father.fb = 0;
                        son.fb = 0;
                        break;
                    case 1:
                        father.fb = -1;
                        son.fb = 0;
                        break;
                    case -1:
                        father.fb = 0;
                        son.fb = 1;
                        break;
                }
                grandson.fb = 0;
                father.right = rotateSR(son);
                father = rotateSL(father);
                break;
        }
        return father;
    }

    private Node balanceToRight(Node father) {
        Node son = father.left;
        switch (son.fb) {
        	// Porción de código 04
            case -1:
                father.fb = -1;
                son.fb = 1;
                father = rotateSR(father);
                break;
             // Porción de código 05
            case 0:
                father.fb = 0;
                son.fb = 0;
                father = rotateSR(father);
                this.height = false;
                break;
             // Porción de código 06
            case 1:
                Node grandson = son.right;
                switch (grandson.fb) {
                    case 0:
                        father.fb = 0;
                        son.fb = 0;
                        break;
                    case -1:
                        father.fb = 1;
                        son.fb = 0;
                        break;
                    case 1:
                        father.fb = 0;
                        son.fb = -1;
                        break;
                }
                grandson.fb = 0;
                father.left = rotateSL(son);
                father = rotateSR(father);
                break;
        }
        return father;
    }

    private Node rotateSL(Node father) {
        Node son = father.right;
        father.right = son.left;
        son.left = father;
        father = son;
        return father;
    }

    private Node rotateSR(Node father) {
        Node son = father.left;
        father.left = son.right;
        son.right = father;
        father = son;
        return father;
    }

    public E search(E x) throws ItemNotFound {
        Node res = searchNode(x, root);
        if (res == null)
            throw new ItemNotFound("El dato " + x + " no está");
        return res.data;
    }

    protected Node searchNode(E x, Node n) {
        if (n == null) return null;
        else {
            int resC = n.data.compareTo(x);
            if (resC < 0) return searchNode(x, n.right);
            else if (resC > 0) return searchNode(x, n.left);
            else return n;
        }
    }

    public void remove(E x) throws ItemNotFound {
        this.height = false;
        this.root = removeNode(x, this.root);
    }

    protected Node removeNode(E x, Node current) throws ItemNotFound {
        Node res = current;
        if (current == null)
            throw new ItemNotFound(x + " no está");
        int resC = current.data.compareTo(x);
        if (resC < 0) {
            res.right = removeNode(x, current.right);
            if (this.height) {
                switch (res.fb) {
                    case 1:
                        res.fb = 0;
                        this.height = true;
                        break;
                    case 0:
                        res.fb = -1;
                        this.height = false;
                        break;
                    case -1:
                        res = balanceToRight(res);
                        this.height = (res.fb != 0) ? false : true;
                        break;
                }
            }
        } else if (resC > 0) {
            res.left = removeNode(x, current.left);
            if (this.height) {
                switch (res.fb) {
                    case -1:
                        res.fb = 0;
                        this.height = true;
                        break;
                    case 0:
                        res.fb = 1;
                        this.height = false;
                        break;
                    case 1:
                        res = balanceToLeft(res);
                        this.height = (res.fb != 0) ? false : true;
                        break;
                }
            }
        } else if (current.left != null && current.right != null) { // Dos hijos
            res.right = minRemove(current.right);
            if (this.height) {
                switch (res.fb) {
                    case 1:
                        res.fb = 0;
                        this.height = true;
                        break;
                    case 0:
                        res.fb = -1;
                        this.height = false;
                        break;
                    case -1:
                        res = balanceToRight(res);
                        this.height = true;
                        break;
                }
            }
            res.data = lastData;
        } else { // 1 hijo o ninguno
            res = (current.left != null) ? current.left : current.right;
            this.height = true;
        }
        return res;
    }

    public E minRemove() {
        this.root = minRemove(this.root);
        E min = lastData;
        return min;
    }

    // Elimina el menor de la izquierda de un nodo
    protected Node minRemove(Node current) {
        if (current.left != null) { // Busca el mínimo
            current.left = minRemove(current.left);
            if (this.height) {
                switch (current.fb) {
                    case -1:
                        current.fb = 0;
                        this.height = true;
                        break;
                    case 0:
                        current.fb = 1;
                        this.height = false;
                        break;
                    case 1:
                        current = balanceToLeft(current);
                        this.height = true;
                        break;
                }
            }
        } else { // Elimina el mínimo
            lastData = current.data;
            current = current.right;
            this.height = true;
        }
        return current;
    }

    public String toString() {
        if (isEmpty())
            return "Árbol vacío... ";
        String str = "";
        str += "Recorrido Post-Orden: " + postOrder(this.root);
        return str;
    }

    public String postOrder(Node current) {
        String str = "";
        if (current.left != null)
            str += postOrder(current.left);
        if (current.right != null)
            str += postOrder(current.right);
        str += current.data.toString() + "[" + current.fb + "],";
        return str;
    }
}

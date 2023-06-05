public class Nodo {
    private int valor;
    private int x;
    private int y;

    private double[] DistTo;

    public Nodo(int valor, int i, int j) {
        this.valor=valor;
        x = i;
        y = j;
    }

    public int getValor() {
        return valor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double[] getDistTo() {
        return DistTo;
    }

    public void setDistTo(double[] distTo) {
        DistTo = distTo;
    }
}
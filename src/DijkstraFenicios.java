import java.util.LinkedList;
import java.util.Queue;

public class DijkstraFenicios {
    private boolean[][] visited;
    private int[][] distTo;
    private Queue<Nodo> queue = new LinkedList<>();

    public DijkstraFenicios() {}

    public int caminhamentoMinimoParaFenicios(int[][] coordenadas, Nodo porto, Nodo portoChegada){
        visited = new boolean[coordenadas.length][coordenadas[0].length];
        distTo = new int[coordenadas.length][coordenadas[0].length];

        for (int i = 0; i < coordenadas.length; i++){
            for (int j = 0; j < coordenadas[0].length; j++){
                distTo[i][j] = Integer.MAX_VALUE;
            }
        }

        distTo[porto.getX()][porto.getY()] = 0;
        Nodo nodo = new Nodo(coordenadas[porto.getX()][porto.getY()], porto.getX(), porto.getY());
        int valorDestino = portoChegada.getValor();
        queue.add(nodo);

        while (!queue.isEmpty()){
            var coordenadaAtual = queue.remove();
            if(coordenadaAtual.getX()!=coordenadas.length-1){
                if(coordenadas[coordenadaAtual.getX()+1][coordenadaAtual.getY()] != 0 && !visited[coordenadaAtual.getX()][coordenadaAtual.getY()]){
                    Nodo coordenadaVisitada = new Nodo(coordenadas[coordenadaAtual.getX()+1][coordenadaAtual.getY()],coordenadaAtual.getX()+1,coordenadaAtual.getY());
                    relax(coordenadaAtual, coordenadaVisitada, valorDestino);
                }
            }
            if(coordenadaAtual.getY()!= coordenadas[0].length-1){
                if(coordenadas[coordenadaAtual.getX()][coordenadaAtual.getY()+1] != 0 && !visited[coordenadaAtual.getX()][coordenadaAtual.getY()]){
                    Nodo coordenadaVisitada = new Nodo(coordenadas[coordenadaAtual.getX()][coordenadaAtual.getY()+1],coordenadaAtual.getX(),coordenadaAtual.getY()+1);
                    relax(coordenadaAtual, coordenadaVisitada, valorDestino);
                }
            }
            if(coordenadaAtual.getX()!=0){
                if(coordenadas[coordenadaAtual.getX()-1][coordenadaAtual.getY()] != 0 && !visited[coordenadaAtual.getX()][coordenadaAtual.getY()]){
                    Nodo coordenadaVisitada = new Nodo(coordenadas[coordenadaAtual.getX()-1][coordenadaAtual.getY()],coordenadaAtual.getX()-1,coordenadaAtual.getY());
                    relax(coordenadaAtual, coordenadaVisitada, valorDestino);
                }
            }
            if(coordenadaAtual.getY()!=0){
                if(coordenadas[coordenadaAtual.getX()][coordenadaAtual.getY()-1] != 0 && !visited[coordenadaAtual.getX()][coordenadaAtual.getY()]){
                    Nodo coordenadaVisitada = new Nodo(coordenadas[coordenadaAtual.getX()][coordenadaAtual.getY()-1],coordenadaAtual.getX(),coordenadaAtual.getY()-1);
                    relax(coordenadaAtual, coordenadaVisitada, valorDestino);
                }
            }
            visited[coordenadaAtual.getX()][coordenadaAtual.getY()] = true;
        }

        var combustivelGasto = distTo[portoChegada.getX()][portoChegada.getY()];

        if(combustivelGasto == Integer.MAX_VALUE){
            return 0;
        }else {
            return combustivelGasto;
        }
    }

    private void relax(Nodo atual, Nodo visitado, int valorDestino) {
        if(visitado.getValor()>9){
            int vX = atual.getX(), vY = atual.getY(), wX = visitado.getX(), wY = visitado.getY();
            if (distTo[wX][wY] > distTo[vX][vY] + 1) {
                distTo[wX][wY] = distTo[vX][vY] + 1;
            }
            queue.add(visitado);
        } else if (visitado.getValor()==valorDestino) {
            queue.clear();
            int vX = atual.getX(), vY = atual.getY(), wX = visitado.getX(), wY = visitado.getY();
            if (distTo[wX][wY] > distTo[vX][vY] + 1) {
                distTo[wX][wY] = distTo[vX][vY] + 1;
            }
        }
    }
}

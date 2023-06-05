import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AppTrabalho2 {
    private int[][] coordenadas;
    private ArrayList<Nodo> portos;

    public AppTrabalho2() {
        portos = new ArrayList<>();
    }

    public void aplicacao(){
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("mapa2000.txt"));
            String line = bufferedReader.readLine();
            Scanner scanner = new Scanner(line).useDelimiter(" ");
            int x = Integer.parseInt(scanner.next());
            int y = Integer.parseInt(scanner.next());
            coordenadas = new int[x][y];
            int valor = 9;
            for(int i=0;i<x;i++){
                line = bufferedReader.readLine();
                scanner = new Scanner(line).useDelimiter("");
                for (int j=0;j<y;j++){
                    var aux=scanner.next();
                    if(!Objects.equals(aux, "*")){
                        if(!Objects.equals(aux, ".")){
                            coordenadas[i][j] = Integer.parseInt(aux);
                            portos.add(new Nodo(Integer.parseInt(aux),i,j));
                        }
                        else{
                            valor++;
                            coordenadas[i][j] = valor;
                        }
                    }
                }
            }
            Queue<Nodo> filaPrioridadePortos = new LinkedList<>();

            portos.sort(Comparator.comparingInt(Nodo::getValor));

            for (Nodo porto: portos) {
                filaPrioridadePortos.add(porto);
            }

            DijkstraFenicios dijkstraFenicios = new DijkstraFenicios();
            Nodo porto1 = filaPrioridadePortos.remove();
            Nodo porto2 = filaPrioridadePortos.remove();
            filaPrioridadePortos.add(porto1);
            int totalCombustivelGasto = 0;
            boolean over=false;
            while (!over){
                int distancia = dijkstraFenicios.caminhamentoMinimoParaFenicios(coordenadas,porto1,porto2);
                while (distancia==0){
                    if(!filaPrioridadePortos.isEmpty()){
                        porto2 = filaPrioridadePortos.remove();
                        distancia = dijkstraFenicios.caminhamentoMinimoParaFenicios(coordenadas,porto1,porto2);
                    }
                    else break;
                }
                totalCombustivelGasto += distancia;
                if(filaPrioridadePortos.isEmpty()){
                    over =true;
                }
                porto1 = porto2;
                if(!filaPrioridadePortos.isEmpty()){
                    porto2 = filaPrioridadePortos.remove();
                }
            }

            System.out.println("o total de combustivel gasto foi: " + totalCombustivelGasto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

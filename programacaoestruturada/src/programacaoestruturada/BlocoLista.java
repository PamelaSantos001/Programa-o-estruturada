
/*

inserir os numeros (pode ser de qualquer valor)
posição de array 
ex. 30, posicao 1
10, posicao 2
5, posicao 3, 
remove de acordo com a posicao, mostra o numero removido, e exibe os valores que ficaram.

*/

package programacaoestruturada;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlocoLista {
   public BlocoLista prox;
   public String elemento;

   public void add(String elemento) {
       BlocoLista novoBloco = new BlocoLista();
       novoBloco.elemento = elemento;
       novoBloco.prox = this.prox;
       this.prox = novoBloco;
   }

   public BlocoLista getElemento(int i) {
       BlocoLista aux = this;
       int c = 0;
       while (aux != null) {
           if (c == i) {
               return aux;
           }
           aux = aux.prox;
           c++;
       }
       return null;
   }

   public BlocoLista removeElemento(int i) {
       if (i < 0) {
           return null;
       }

       if (i == 0) {
           BlocoLista removido = this.prox;
           if (removido != null) {
               this.prox = removido.prox;
               removido.prox = null;
           }
           return removido;
       }

       BlocoLista anterior = getElemento(i - 1);
       if (anterior != null && anterior.prox != null) {
           BlocoLista removido = anterior.prox;
           anterior.prox = removido.prox;
           removido.prox = null;
           return removido;
       }

       return null;
   }

   public void adicionarElemento(String elemento, int posicao) {
       BlocoLista novoBloco = new BlocoLista();
       novoBloco.elemento = elemento;

       if (posicao == 0) {
           novoBloco.prox = this.prox;
           this.prox = novoBloco;
       } else {
           BlocoLista posicaoAnterior = getElemento(posicao - 1);

           if (posicaoAnterior != null) {
               novoBloco.prox = posicaoAnterior.prox;
               posicaoAnterior.prox = novoBloco;
           } else {
               System.out.println("Posição inválida");
           }
       }
   }

   public static void main(String[] args) {
       BlocoLista bl = new BlocoLista();
       Scanner scanner = new Scanner(System.in);
       List<String> sequenciaInsercao = new ArrayList<>();

       int opcao = 0;

       while (opcao != 4) {
           System.out.println("\nMenu:");
           System.out.println("1. Adicionar elemento");
           System.out.println("2. Remover elemento");
           System.out.println("3. Exibir elementos");
           System.out.println("4. Sair");
           System.out.print("Escolha uma opção: ");
           opcao = scanner.nextInt();

           switch (opcao) {
               case 1:
                   System.out.print("Digite o elemento a ser adicionado: ");
                   String elemento = scanner.next();
                   System.out.print("Digite a posição de inserção: ");
                   int posicao = scanner.nextInt();
                   bl.adicionarElemento(elemento, posicao);
                   sequenciaInsercao.add(elemento);
                   System.out.println("Elemento adicionado com sucesso!");
                   break;

               case 2:
                   System.out.print("Digite a posição do elemento a ser removido: ");
                   int posicaoRemocao = scanner.nextInt();
                   BlocoLista removido = bl.removeElemento(posicaoRemocao);
                   if (removido != null) {
                       String elementoRemovido = sequenciaInsercao.remove(posicaoRemocao - 1);
                       System.out.println("Elemento removido: " + elementoRemovido);
                   } else {
                       System.out.println("Posição inválida");
                   }
                   break;

               case 3:
                   System.out.println("Elementos da lista:");
                   BlocoLista elementoAtual = bl.prox;
                   while (elementoAtual != null) {
                       System.out.println(elementoAtual.elemento);
                       elementoAtual = elementoAtual.prox;
                   }
                   break;

               case 4:
                   System.out.println("Encerrando o programa...");
                   break;

               default:
                   System.out.println("Opção inválida. Tente novamente.");
                   break;
           }
       }
   }
}

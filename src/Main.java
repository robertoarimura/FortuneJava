import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int saldo = 100;
        int bet;
        int pagamento;
        String[] row;
        String jogarNovamente;

        System.out.println("------------");
        System.out.println("Fortune Java");
        System.out.println("Símbolos: 🍊 🃏 👝 🍯 💰");
        System.out.println("------------");

        while(saldo > 0){
            System.out.printf("Saldo atual: R$%d\n", saldo);
            System.out.print("Quanto deseja apostar?: ");
            bet = sc.nextInt();
            sc.nextLine();

            if(bet > saldo){
                System.out.println("Saldo insuficiente!");
                continue;
            }else if (bet <= 0 ){
                System.out.println("O valor da aposta deve ser maior que 0!");
                continue;
            }else{
                saldo -= bet;
            }

            System.out.println("Girando...");
            row = spinRow();
            printRow(row);
            pagamento = getPagamento(row, bet);

            if(pagamento > 0){
                System.out.printf("Você ganhou R$%d\n", pagamento);
                saldo += pagamento;
            }else{
                System.out.println("Você perdeu essa rodada!");
            }

            System.out.print("Você deseja jogar novamente? (S/N): ");
            jogarNovamente = sc.nextLine().toUpperCase();

            if(!jogarNovamente.equals("S")){
                break;
            }
        }
        System.out.printf("O jogo acabou! Seu saldo final é de R$%d\n", saldo);
        sc.close();
    }
    static String[] spinRow(){
        String[] simbolos = {"🍊","🃏","👝","🍯","💰"};
        String[] row = new String[3];
        Random random = new Random();

        for(int i = 0; i < 3; i++){
            row[i] = simbolos[random.nextInt(simbolos.length)];
        }

        return  row;
    }
    static void printRow(String[] row){
        System.out.println("----------------");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("----------------");
    }
    static int getPagamento(String[] row, int bet){
        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch(row[0]){
                case "🍊" -> bet * 2;
                case "🃏" -> bet * 4;
                case "👝" -> bet * 6;
                case "🍯" -> bet * 10;
                case "💰" -> bet * 20;
                default -> 0;
            };
        }else if(row[0].equals(row[1])){
            return switch(row[0]){
                case "🍊" -> bet;
                case "🃏" -> bet * 2;
                case "👝" -> bet * 4;
                case "🍯" -> bet * 6;
                case "💰" -> bet * 10;
                default -> 0;
            };
        }else if(row[1].equals(row[2])){
            return switch(row[1]){
                case "🍊" -> bet;
                case "🃏" -> bet * 2;
                case "👝" -> bet * 4;
                case "🍯" -> bet * 6;
                case "💰" -> bet * 10;
                default -> 0;
            };
        }
        return 0;
    }
}

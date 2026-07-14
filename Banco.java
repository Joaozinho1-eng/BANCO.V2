import java.util.ArrayList;
import java.util.Scanner;

public  class Banco {
    static ArrayList<Contaclientes> contas = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    private static class Contaclientes{
        private Integer id;
        private String nome;
        private double saldo;

        public int getId(){
            return id;
        }

        public double getSaldo(){
            return saldo;
        }

        public void setSaldo(double saldo){
            this.saldo = saldo;
        }


    }
    public static void mostrarMenu(){
        System.out.println("---MENU DO BANCO---");
        System.out.println("1- Criar conta");
        System.out.println("2- Listar contas");
        System.out.println("3- Buscar conta");
        System.out.println("4- Depositar");
        System.out.println("5- Transferir");
        System.out.println("6- Sacar");
        System.out.println("7- Consultar saldo");
        System.out.println("8- Sair");
    }

    public static void criarConta(){
     Contaclientes contac = new Contaclientes();
     System.out.println("Digite o seu ID de conta ");
     contac.id = input.nextInt();
     input.nextLine();

     System.out.println("Digite o seu nome : ");
     contac.nome = input.nextLine();

     System.out.println("Digite o seu saldo : ");
     contac.saldo = input.nextDouble();
     contas.add(contac);

     System.out.println("Conta cadastrada com sucesso! ");
    }

    public static void listarContas(){
        if (contas.isEmpty()){
            System.out.println("Nenhum conta foi encontrada!");
            return;
        }
        for (Contaclientes  conta : contas){
            System.out.println("ID: "+ conta.id);
            System.out.println("Nome: "+ conta.nome);
            System.out.println("Saldo: "+ conta.saldo);
        }
    }
    public static void buscarConta(){
        System.out.println("Digite o ID da conta que deseja buscar: ");
        int id = input.nextInt();
        boolean encontrado = false;
        for (Contaclientes  conta : contas){
            if (conta.getId() == id){
                System.out.println("-----CONTA ENCONTRADA-----");
                System.out.println("ID: "+ conta.id);
                System.out.println("Nome: "+ conta.nome);
                System.out.println("Saldo: "+ conta.saldo);
                encontrado=true;
                input.nextLine();
                break;
            }

        }
        if (!encontrado){
            System.out.println("Nenhuma conta foi encontrada!");
        }
    }
    public static void depositar(){
       System.out.println("Digite o ID da conta que deseja depositar: ");
       int idBusca = input.nextInt();
       for  (Contaclientes  conta : contas){
           if (conta.getId() == idBusca){
               System.out.println("Digite o valor que deseja depositar: ");
               double valor = input.nextDouble();
               if (valor >= 0){
                   conta.setSaldo(conta.getSaldo() + valor);
                   System.out.println("Depositado com sucesso!");
                   System.out.println("O novo saldo é de: " + conta.getSaldo());
               } else{
                   System.out.print("Valor inválido!");
               }
               return;
           }
       }
       System.out.println("Nenhuma conta foi encontrada!");
       }

       public static void transferir(){
        System.out.println("Digite o ID da conta que vai transferir: ");
        int idOrigem = input.nextInt();
        System.out.println("Digite o ID da conta que vai receber a transferencia: ");
        int idDestino = input.nextInt();
        Contaclientes origem = null;
        Contaclientes destino = null;
        for  (Contaclientes  conta : contas){
            if(conta.getId()== idOrigem){
                origem = conta;
            }
            if(conta.getId()== idDestino){
                destino = conta;
            }
        }
        if (origem == null || destino == null){
            System.out.println("Uma das contas não foi encontrada!");
            return;
        }
        System.out.println("Digite o valor que deseja transferir: ");
        double valorOrigem = input.nextDouble();

        if (valorOrigem > 0 && valorOrigem <= origem.getSaldo()){
            origem.setSaldo(origem.getSaldo() - valorOrigem);
            destino.setSaldo(destino.getSaldo() + valorOrigem);
            System.out.println("Transferencia realizada com sucesso!");
            System.out.println("Novo saldo de: " + origem.getSaldo());
        } else {
            System.out.println("Valor inválido ou insuficiente!");
        }
       }

       

       public static void sacar(){
           System.out.println("Digite o ID da conta que deseja sacar: ");
           int idBusca = input.nextInt();
           for (Contaclientes  conta : contas){
               if (conta.getId() == idBusca){
                   System.out.println("Digite o valor que deseja sacar: ");
                   double valor = input.nextDouble();
                   if (valor <= conta.getSaldo()){
                       conta.setSaldo(conta.getSaldo() - valor);
                       System.out.println("Sacado com sucesso!");
                       System.out.println("O seu novo saldo é de: " + conta.getSaldo());

                   } else{
                       System.out.println("Valor inválido para saque!");
                   }
                   return;
               }
           }
       }

       public static void saldo(){
        System.out.println("Digite o ID da conta que deseja ver o saldo: ");
        int idBusca = input.nextInt();
        for (Contaclientes  conta : contas){
            if (conta.getId() == idBusca){
                System.out.println("Seu saldo atual é de: " + conta.getSaldo());
            }else{
                System.out.println("ID de conta inválido");
            }
        }
        return;
       }

            public static void main(String[] args){

        char resposta;

        do {
            mostrarMenu();
            resposta = input.next().charAt(0);
            switch (resposta) {

                case '1':
                    criarConta();
                    break;

                case '2':
                    listarContas();
                    break;

                case '3':
                    buscarConta ();
                    break;

                case '4':
                    depositar();
                    break;
                case '5':
                    transferir();
                    break;
                case '6':
                    sacar();
                    break;
                case '7':
                    saldo();
                    break;


            }
        }while(resposta !='8');
    }
}
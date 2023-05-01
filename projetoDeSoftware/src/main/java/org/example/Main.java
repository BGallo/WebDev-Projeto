package org.example;


import org.example.dao.HotelDao;
import org.example.excecao.HotelNaoEncontradoException;
import org.example.modelo.Hotel;
import org.example.util.FabricaDeDAOs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws HotelNaoEncontradoException {
        Logger logger = LoggerFactory.getLogger(Principal.class);
        logger.error("Mensagem de log emitida utilizando o LOG4J");
        HotelDao hotelDao= FabricaDeDAOs.getDAO(HotelDao.class);
       // var umHotel= new Hotel("HotelTrivago","rua nao sei esquina com esquece","000x42069",4,391.2);
        //hotelDao.inclui(umHotel);
        boolean continua=true;
        Scanner scanner= new Scanner(System.in);

        while(continua){
            System.out.println("Digite 1 para incluir um hotel,2 para buscar,3 para alterar,4 para deletar ");
            int n= scanner.nextInt();
            if (n==1){
                System.out.println("Digite o nome do hotel");
                String nome= scanner.next();
                System.out.println("Digite o endereço");
                String endereco = scanner.next();
                System.out.println("Digite o cep");
                String cep = scanner.next();
                System.out.println("digite a quantidade de estrelas");
                int estrelas= scanner.nextInt();
                System.out.println("digite o valor da diaria");
                double diaria = scanner.nextDouble();
                var umHotel=new Hotel(nome,endereco,cep,estrelas,diaria);
                hotelDao.inclui(umHotel);
            } else if (n==2) {
                System.out.println("digite 1 para buscar todos ou 2 para buscar um especifico");
                int m=scanner.nextInt();
                if (m==1){
                    var listaHoteis=hotelDao.recuperaHoteis();
                    for (int i=0;i<listaHoteis.size();i++){
                        System.out.println(listaHoteis.get(0).printHotel());
                    }
                } else if (m==2) {
                    System.out.println("digite o id do hotel que deseja buscar");
                    long idHotel= scanner.nextLong();
                    var umHotel=hotelDao.recuperarUmHotel(idHotel);
                    System.out.println(umHotel.printHotel());
                }
            } else if (n==3) {
                System.out.println("digite o id do hotel que deseja alterar");
                long idHotel= scanner.nextLong();
                var umHotel=hotelDao.recuperarUmHotel(idHotel);
                System.out.println("o nome atual é : "+umHotel.getNome()+" digite o novo nome");
                umHotel.setNome(scanner.nextLine());
                System.out.println("o endereco atual é:"+umHotel.getEndereco()+"digite o novo endereço");
                umHotel.setEndereco(scanner.nextLine());
                System.out.println("o cep atual é:"+umHotel.getCep()+"digite o novo cep");
                umHotel.setCep(scanner.nextLine());
                System.out.println("a quantidade de estrelas atual é"+umHotel.getEstrelas()+"digite a nova quantidade de estrelas");
                umHotel.setEstrelas(scanner.nextInt());
                System.out.println("o valor atual da diaria é:"+umHotel.getDiaria()+"digite o novo valor da diaria");
                umHotel.setDiaria(scanner.nextDouble());
                hotelDao.altera(umHotel);
            } else if (n==4) {
                System.out.println("digite o id do hotel para deletar");
                hotelDao.exclui(scanner.nextLong());
            }
            else{continua=false;}
        }
    }
}
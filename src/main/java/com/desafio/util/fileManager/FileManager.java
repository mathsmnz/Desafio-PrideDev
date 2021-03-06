package com.desafio.util.fileManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileManager {
    //Responsável por criar o arquivo de texto caso necessário
    protected static boolean fileCreator(String fileName){
        try{
            File arquivo = new File(fileName + ".txt");
            if(arquivo.createNewFile()){
                System.out.println("|==>Arquivo criado com sucesso!");
                System.out.println("|==>"+arquivo.getName());
                System.out.println("|==>"+arquivo.getAbsolutePath());
                return true;
            }else{
                System.out.println("|==>Arquivo já existente");
                return false;
            }
        }catch (IOException e){
            System.out.println("Um erro foi encontrado");
            e.printStackTrace();
        }
        return false;
    }
    //Responsável por escrever os números dados pelo usuário em um arquivo de texto
    protected static boolean fileWriter(String fileName, Set<Integer> listNames){
        try{
            FileWriter arquivo = new FileWriter(fileName + ".txt");
            File tempFile =  new File(fileName + ".txt");
            if(!tempFile.exists()){
                fileCreator(fileName);
            }
            for (Integer listName : listNames) {
                //Garante que os números á serem escritos são validos
                if(listName > 0 && listName <= 60){
                    arquivo.write(String.valueOf(listName));
                    arquivo.write("\n");
                }

            }
            arquivo.close();
            return true;
        }catch (IOException e){
            System.out.println("Um erro foi encontrado");
            e.printStackTrace();
        }
        return false;
    }
    //Responsável por ler os arquivos, devolvendo um Set com os números válidos lidos
    protected static Set<Integer> fileReader(String fileName){
        Set<Integer> listNames = new HashSet<>();
        try{
            File arquivo = new File(fileName + ".txt");
            if(arquivo.isFile()){
                Scanner scan = new Scanner(arquivo);
                while (scan.hasNextLine()){
                    int temp = Integer.parseInt(scan.nextLine());
                    //Garante que eles estão entre os números válidos para sorteio de mega sena
                    if(temp > 0  && temp <= 60){
                        listNames.add(temp);
                    }
                }
            }else{
                System.out.println("|===| Arquivo nao encontrado!");
                System.out.println("|===| Se desejar, pode criar um arquivo usando este mesmo programa");
                return null;
            }
        }catch (IOException e){
            System.out.println("Erro de IO");
            e.printStackTrace();
        }
        return listNames;
    }
    //Verifica se o arquivo existe e é de fato um arquivo, depois devolvendo se a operação foi um sucesso
    //Retorna também, uma mensagem ao usuário relevante á operação feita
    protected static boolean fileChecker(String fileName){
        File arquivo = new File(fileName + ".txt");
        if(arquivo.isFile() && arquivo.exists()){
            System.out.println("|===| O arquivo \"" + fileName + "\" foi encontrado");
            return true;
        }else{
            System.out.println("|===| O arquivo \"" + fileName + "\" não foi encontrado");
            return false;
        }
    }
}

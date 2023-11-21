package main.service.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {
    private static Scanner input = new Scanner(System.in);
    private static void timeout(double tempoSeg) {
        try {
            Thread.sleep((long) (tempoSeg * 1000));
        } catch (Exception ignored) {
        }
    }
    public static Integer getInteger(String message) {
        int id;
        try {
            System.out.println(message);
            id = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input = new Scanner(System.in);
            ExceptionUtils.trataErro("Numero inválido");
            timeout(2);
            id = getInteger(message);
        }
        return id;
    }

    public static String getString(String message) {
        System.out.println(message);
        String nome;
        nome = input.nextLine();
        if (nome.length() > 50){
            ExceptionUtils.trataErro("Nome pode ter até 50 caracteres!");
            input = new Scanner(System.in);
            getString(message);
        }
        return nome;
    }
}

package main.service.util;

public class ExceptionUtils {

    private ExceptionUtils(){
    }

    public static void trataErro(String message, Exception e) {
        System.out.println("ERRO!");
        System.out.println(message);
        System.out.println(e.getMessage());
        System.out.println("ERRO!");
    }

    public static void trataErro(String message) {
        System.out.println(ColorUtils.RED_BOLD + "ERRO!");
        System.out.println(message);
        System.out.println("ERRO!" + ColorUtils.RESET);
    }
}

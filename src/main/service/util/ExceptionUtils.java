package main.service.util;

public class ExceptionUtils {
    private static final String ERROR = "ERRO!";

    private ExceptionUtils(){
    }

    public static void trataErro(String message, Exception e) {
        System.out.println(ERROR);
        System.out.println(message);
        System.out.println(e.getMessage());
        System.out.println(ERROR);
    }

    public static void trataErro(String message) {
        System.out.println(ColorUtils.RED_BOLD + ERROR);
        System.out.println(message);
        System.out.println(ERROR + ColorUtils.RESET);
    }
}

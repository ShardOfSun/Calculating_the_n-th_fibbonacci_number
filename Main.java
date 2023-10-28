import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to program Сalculating the n-th Fibbonacci number!\n" +
                "Please, input n (number of number):");

        MatrixFibonacci fibonacciNumber = new MatrixFibonacci();
        for (int n = 0; n <= 70; n++) {
            System.out.println(n + "-th Fibbonacci number is: " + fibonacciNumber.getFibonacciNumber(n));
        }
    }
}
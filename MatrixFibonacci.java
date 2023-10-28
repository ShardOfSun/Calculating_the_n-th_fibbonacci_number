/*  1. Разожить n на сумму степеней 2-ки множ-ва P_n
    2. Возвести матрицы в степени из P_n, используя меморизацию
    3. Перемножить матрицы */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatrixFibonacci {
    private static BigInteger[][] Q;
    private static Map<Integer, BigInteger[][]> cache;

    MatrixFibonacci() {
        Q = new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE},
                               {BigInteger.ONE, BigInteger.ZERO}};
        cache = new HashMap<>();
    }

    private static BigInteger[][] multiplyMatrices2x2(BigInteger[][] M1, BigInteger[][] M2) {
        BigInteger[][] result = new BigInteger[2][2];

        result[0][0] = M1[0][0].multiply(M2[0][0]).add(M1[0][1].multiply(M2[1][0]));
        result[0][1] = M1[0][0].multiply(M2[0][1]).add(M1[0][1].multiply(M2[1][1]));
        result[1][0] = M1[1][0].multiply(M2[0][0]).add(M1[1][1].multiply(M2[1][0]));
        result[1][1] = M1[1][0].multiply(M2[0][1]).add(M1[1][1].multiply(M2[1][1]));

        return result;
    }

    // Пунк 1
    private static ArrayList<Integer> getDecompositionNumberIntoPowersOf2(int number) {
        ArrayList<Integer> powers = new ArrayList<Integer>();

        int power = 0;
        while (number > 0) {
            if ((number & 1) == 1) {
                powers.add((int) Math.pow(2, power));
            }
            number = number >> 1;
            power++;
        }

        return powers;
    }

    // Пункт 2
    private static BigInteger[][] matrixPower(BigInteger[][] matrix, int power) {

        if (power == 1) {
            return matrix;
        }
        if (cache.containsKey(power)) {
            return cache.get(power);
        }

        BigInteger[][] temp = matrixPower(matrix, power / 2);
        BigInteger[][] result = multiplyMatrices2x2(temp, temp);
        cache.put(power, result);
        return result;
    }

    public BigInteger getFibonacciNumber(int number) {
        switch (number) {
            case 0 -> { return BigInteger.ZERO; }
            case 1 -> { return BigInteger.ONE; }
            default -> { }
        }

        ArrayList<Integer> powers = getDecompositionNumberIntoPowersOf2(number - 1);
        ArrayList<BigInteger[][]> matrices = new ArrayList<>();

        for (int power : powers) {
            matrices.add(matrixPower(Q, power));
        }

        // Пункт 3
        while (matrices.size() > 1) {
            matrices.set(0, multiplyMatrices2x2(matrices.get(1), matrices.get(0)));
            matrices.remove(1);
        }

        return matrices.get(0)[0][0];
    }
}

import java.util.Scanner;

enum Coin {
    ONE(1), TWO(2), FIVE(5), TEN(10);

    int value;

    Coin(int value) {
        this.value = value;
    }
}

public class pr1A1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int total = 0;

        while (total < 15) {
            Coin c = Coin.valueOf(sc.next().toUpperCase());
            total += c.value;
            System.out.println("total = " + total);
        }

        System.out.println("Change = " + (15 - total));
        sc.close();
    }
}
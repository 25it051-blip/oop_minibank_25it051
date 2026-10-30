import java.util.Scanner;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return this.x == p.x && this.y == p.y;
    }

    public static void main(String[] args) {
        Point[] p1 = new Point[5];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            p1[i] = new Point(sc.nextInt(), sc.nextInt());
        }

        int count = 0;

        for (int i = 0; i < 5; i++) {
            boolean duplicate = false;

            for (int j = i + 1; j < 5; j++) {
                if (p1[i].equals(p1[j])) {
                    duplicate = true;
                    count++;
                    break;
                }
            }

            if (!duplicate) {
                System.out.println("x = " + p1[i].x + " y = " + p1[i].y);
            }
        }

        System.out.println(count);
        sc.close();
    }
}
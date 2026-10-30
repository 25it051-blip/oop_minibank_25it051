class Booking {
    int seatsLeft = 5;
    int booked = 0;

    void book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }

            seatsLeft--;
            booked++;
            System.out.println("Seat booked");
        }
    }

    synchronized void bookSync() {
        if (seatsLeft > 0) {
            seatsLeft--;
            booked++;
            System.out.println("Seat booked");
        } else {
            System.out.println("No seats available");
        }
    }
}

class MyThread extends Thread {
    Booking b;
    boolean sync;

    MyThread(Booking b, boolean sync) {
        this.b = b;
        this.sync = sync;
    }

    public void run() {
        if (sync) {
            b.bookSync();
        } else {
            b.book();
        }
    }
}

public class Main9_2 {
    public static void main(String[] args) throws Exception {
        Booking b = new Booking();

        MyThread t1 = new MyThread(b, false);
        MyThread t2 = new MyThread(b, false);
        MyThread t3 = new MyThread(b, false);
        MyThread t4 = new MyThread(b, false);
        MyThread t5 = new MyThread(b, false);
        MyThread t6 = new MyThread(b, false);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();

        System.out.println("Seats left: " + b.seatsLeft);
        System.out.println("Seats booked: " + b.booked);

        b.seatsLeft = 5;
        b.booked = 0;

        MyThread t7 = new MyThread(b, true);
        MyThread t8 = new MyThread(b, true);
        MyThread t9 = new MyThread(b, true);
        MyThread t10 = new MyThread(b, true);
        MyThread t11 = new MyThread(b, true);
        MyThread t12 = new MyThread(b, true);

        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();

        t7.join();
        t8.join();
        t9.join();
        t10.join();
        t11.join();
        t12.join();

        System.out.println("Seats left: " + b.seatsLeft);
        System.out.println("Seats booked: " + b.booked);
    }
}
class MyTask implements Runnable {
    Object lock1, lock2;

    MyTask(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName()
                    + " locked first object");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()
                        + " locked second object");
            }
        }
    }
}

class SafeTask implements Runnable {
    Object lock;

    SafeTask(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName()
                    + " locked first object");

            System.out.println(Thread.currentThread().getName()
                    + " locked second object");
        }
    }
}

public class Main10_3 {
    public static void main(String[] args) throws Exception {
        Object A = new Object();
        Object B = new Object();
        Object C = new Object();

        System.out.println("Deadlock demonstration:");

        Thread t1 = new Thread(new MyTask(A, B), "Thread-1");
        Thread t2 = new Thread(new MyTask(B, A), "Thread-2");

        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        Thread.sleep(300);

        System.out.println("\nDeadlock prevention:");

        Thread t3 = new Thread(new SafeTask(C), "Thread-3");
        Thread t4 = new Thread(new SafeTask(C), "Thread-4");

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("Program completed");
    }
}
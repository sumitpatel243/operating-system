// reader- writer problem


import java.util.concurrent.Semaphore;

public class Main {
    static int rc = 0;
    static Semaphore s = new Semaphore(1);
    static Semaphore wrt = new Semaphore(1);

    static class Reader implements Runnable {
        public void run() {
            try {
                s.acquire();
                
                s.release();
                System.out.println(Thread.currentThread().getName() + " is reading");
                Thread.sleep(100);
                s.acquire();
                
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer implements Runnable {
        public void run() {
            try {
                wrt.acquire();
                System.out.println(Thread.currentThread().getName() + " is writing");
                Thread.sleep(100);
                wrt.release();
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Reader());
        t1.setName("reader1");

        Thread t2 = new Thread(new Reader());
        t2.setName("reader2");

        Thread t3 = new Thread(new Writer());
        t3.setName("writer1");

        Thread t4 = new Thread(new Reader());
        t4.setName("reader3");

        t4.start();
        t1.start();
        t3.start();
        t2.start();
    }
}

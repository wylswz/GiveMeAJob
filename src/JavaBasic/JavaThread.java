package JavaBasic;


class MyLock {
    public Object lock = new Object();
    public boolean locked = false;
    public void lock() {
        try{
            synchronized (lock) {
                System.out.println("Locked");
                this.lock.wait();
            }
            System.out.println("UnLocked");

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void unlock() {
        synchronized (lock) {

            this.lock.notifyAll();
        }
    }
}

class MyThread extends Thread {
    public MyLock lock;
    public MyThread(MyLock lock) {
        this.lock = lock;
    }

    @Override
    public void run(){
        this.lock.lock();
    }
}
class MyThread2 extends Thread {
    public MyLock lock;
    public MyThread2(MyLock lock) {
        this.lock = lock;
    }

    @Override
    public void run(){
        this.lock.unlock();
    }
}


public class JavaThread {


    public static void main(String[] args) {
        MyLock lock = new MyLock();
        MyThread t = new MyThread(lock);
        MyThread2 t2 = new MyThread2(lock);
        t.start();
        t2.start();





    }
}

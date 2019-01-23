package org.firstinspires.ftc.teamcode;

public class ThreadedWorker extends Thread {

    private Runnable target;
    private Runnable target2;

    private Boolean runReverse = true;

    ThreadedWorker(Runnable target, Runnable otherTarget) {
        this.target = target;
        this.target2 = otherTarget;
    }
    public void run() {
        if (runReverse) {
            while (Thread.currentThread().isAlive()) {
                target.run();
            }
        }
        else {
            while (Thread.currentThread().isAlive()) {
                target2.run();
            }
        }
    }
    @Override
    public void interrupt() {
        runReverse ^= true;
        super.interrupt();
    }
}

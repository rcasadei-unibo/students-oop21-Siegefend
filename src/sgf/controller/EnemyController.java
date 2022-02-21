package sgf.controller;

/**
 *
 */
public class EnemyController {
    private static final int SLEEP_TIME = 10;
    private volatile boolean threadRun = true;

    /**
     * 
     */
    public EnemyController() {
        this.startEnemyThread();
    }

    private void startEnemyThread() {
        final Thread enemyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int ups = 0;
                long lastTime = System.currentTimeMillis();
                while (threadRun) {
                    // Print how many update has been done in 1 second.
                    if (System.currentTimeMillis() - lastTime >= 1000) {
                        System.out.print("UPS:" + ups + "\n");
                        ups = 0;
                        lastTime = System.currentTimeMillis();
                    }
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        enemyThread.start();
    }

    /**
     * Temporary stops the thread.
     */
    public void pauseMapThread() {
        this.threadRun = false;
    }

    /**
     * Resume the thread.
     */
    public void resumeMapThread() {
        this.threadRun = true;
    }
}

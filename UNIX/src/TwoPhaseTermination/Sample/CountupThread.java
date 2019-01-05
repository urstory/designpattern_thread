public class CountupThread extends Thread {
    // �����󥿤���
    private long counter = 0;

    // ��λ�׵᤬�Ф��줿��true
    private volatile boolean shutdownRequested = false;

    // ��λ�׵�
    public void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }

    // ��λ�׵᤬�Ф��줿���ɤ����Υƥ���
    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    // ư��
    public final void run() {
        try {
            while (!isShutdownRequested()) {
                doWork();
            }
        } catch (InterruptedException e) {
        } finally {
            doShutdown();
        }
    }

    // ���
    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }

    // ��λ����
    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
}

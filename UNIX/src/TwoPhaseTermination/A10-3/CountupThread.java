import java.io.IOException;
import java.io.FileWriter;

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
    public void run() {
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
        System.out.println("doShutdown: Save BEGIN");
        try {
            FileWriter writer = new FileWriter("counter.txt");
            writer.write("counter = " + counter);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("doShutdown: Save END");
    }
}

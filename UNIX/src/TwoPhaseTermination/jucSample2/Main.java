import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;

public class Main {
    private static final int THREADS = 3; // ����åɤθĿ�

    public static void main(String[] args) {
        System.out.println("BEGIN");

        // �Ż���¹Ԥ��륹��åɤ��󶡤���ExecutorService
        ExecutorService service = Executors.newFixedThreadPool(THREADS);

        // �Хꥢ����������Ȥ��Υ��������
        Runnable barrierAction = new Runnable() {
            public void run() {
                System.out.println("Barrier Action!");
            }
        };

        // �ե��������碌��CyclicBarrier
        CyclicBarrier phaseBarrier = new CyclicBarrier(THREADS, barrierAction);

        // �Ż��ν�λ��Ĵ�٤�CountDownLatch
        CountDownLatch doneLatch = new CountDownLatch(THREADS);

        try {
            // �Ż��򳫻Ϥ���
            for (int t = 0; t < THREADS; t++) {
                service.execute(new MyTask(phaseBarrier, doneLatch, t));
            }
            // �Ż��ν�λ���Ԥ�
            System.out.println("AWAIT");
            doneLatch.await();
        } catch (InterruptedException e) {
        } finally {
            service.shutdown();
            System.out.println("END");
        }
    }
}

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {
    private static final int TASKS = 10;
    public static void main(String[] args) {
        // �¹Ԥ��륵���ӥ�
        ExecutorService service = Executors.newFixedThreadPool(3);
        try {
            for (int t = 0; t < TASKS; t++) {
                // ���˽񤭹��ॿ����
                Runnable printTask = new Runnable() {
                    public void run() {
                        Log.println("Hello!");
                        Log.close();
                    }
                };
                // �������μ¹�
                service.execute(printTask);
            }
        } finally {
            service.shutdown();
        }
    }
}

public class Main {
    private static final int TASKS = 10;
    public static void main(String[] args) {
        for (int t = 0; t < TASKS; t++) {
            // ���˽񤭹��ॿ����
            Runnable printTask = new Runnable() {
                public void run() {
                    Log.println("Hello!");
                    Log.close();
                }
            };
            // �������μ¹�
            new Thread(printTask).start();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // ����åɤ���������
        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        if (Thread.interrupted()) {
                            throw new InterruptedException();
                        }
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        System.out.print("*");
                    }
                }
            }
        };

        // ����åɤ�ư����
        thread.start();

        // 5���Ԥ�
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        // ����åɤ˰��٤���interrupt�򤫤���
        thread.interrupt();
    }
}

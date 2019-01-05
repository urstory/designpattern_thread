public class Main {
    public static void main(String[] args) {
        // Host�νŤ�������¹Ԥ��륹��å�
        Thread executor = new Thread() {
            public void run() {
                System.out.println("Host.execute BEGIN");
                try {
                    Host.execute(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Host.execute END");
            }
        };

        // ��ư����
        executor.start();

        // ��15�õ٤�
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
        }

        // ����󥻥�¹�
        System.out.println("***** interrupt *****");
        executor.interrupt();
    }
}

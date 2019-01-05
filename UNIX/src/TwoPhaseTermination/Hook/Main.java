public class Main {
    public static void main(String[] args) {
        System.out.println("main:BEGIN");

        // (1) ����å�����ʤ��㳰�Υϥ�ɥ�����ꤹ��
        Thread.setDefaultUncaughtExceptionHandler(
            new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable exception) {
                    System.out.println("****");
                    System.out.println("UncaughtExceptionHandler:BEGIN");
                    System.out.println("currentThread = " + Thread.currentThread());
                    System.out.println("thread = " + thread);
                    System.out.println("exception = " + exception);
                    System.out.println("UncaughtExceptionHandler:END");
                }
            }
        );

        // (2) ����åȥ����󡦥եå������ꤹ��
        Runtime.getRuntime().addShutdownHook(
            new Thread() {
                public void run() {
                    System.out.println("****");
                    System.out.println("shutdown hook:BEGIN");
                    System.out.println("currentThread = " + Thread.currentThread());
                    System.out.println("shutdown hook:END");
                }
            }
        );

        // (3) ��3�ø�ˡ�0�ˤ�������γ�껻�פ�Ԥ�����åɤ�ư����
        new Thread("MyThread") {
            public void run() {
                System.out.println("MyThread:BEGIN");
                System.out.println("MyThread:SLEEP...");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }

                System.out.println("MyThread:DIVIDE");

                // ��0�ˤ�������γ�껻��
                int x = 1 / 0;

                // �����ˤ���ʤ�
                System.out.println("MyThread:END");
            }
        }.start();

        System.out.println("main:END");
    }
}

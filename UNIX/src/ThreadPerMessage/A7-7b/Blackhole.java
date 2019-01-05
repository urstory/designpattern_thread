public class Blackhole {
    public static void enter(Object obj) {
        System.out.println("Step 1");
        magic(obj);
        System.out.println("Step 2");
        synchronized (obj) {
            System.out.println("Step 3 (never reached here)");  // �����ˤϤ��ʤ�
        }
    }
    public static void magic(final Object obj) {
        // thread�ϡ�obj�Υ�å����äƤ��鼫ʬ���Ȥν�λ��ʱ���Ԥĥ���å�
        Thread thread = new Thread() {
            public void run() {
                synchronized (obj) {        // ������obj�Υ�å�����
                    synchronized (this) {
                        this.notifyAll();   // obj�Υ�å����ä����Ȥ�����
                    }
                    try {
                        this.join(); // �ʱ���ԤĤ��Ȥˤʤ�
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        synchronized (thread) {
            thread.start();     // ����åɤε�ư
            try {
                thread.wait();  // ����������åɤ�obj�Υ�å�����Τ��Ԥ�
            } catch (InterruptedException e) {
            }
        }
    }
}

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
        // thread��obj�Υ�å����ä�̵�¥롼�פ��륹��å�
        // thread��̾���򥬡��ɾ��Ȥ��ƻȤ�
        Thread thread = new Thread() {      // inner class
            public void run() {
                synchronized (obj) {        // ������obj�Υ�å�����
                    synchronized (this) {
                        this.setName("Locked"); // �����ɾ����Ѳ�
                        this.notifyAll();       // obj�Υ�å����ä����Ȥ�����
                    }
                    while (true) {
                        // ̵�¥롼��
                    }
                }
            }
        };
        synchronized (thread) {
            thread.setName("");
            thread.start();         // ����åɤε�ư
            // Guarded Suspension�ѥ�����
            while (thread.getName().equals("")) {
                try {
                    thread.wait();  // ����������åɤ�obj�Υ�å�����Τ��Ԥ�
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

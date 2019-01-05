public class Main {
    public static void main(String[] args) {
        System.out.println("Testing SecurityGate...");
        for (int trial = 0; true; trial++) {
            SecurityGate gate = new SecurityGate();
            CrackerThread[] t = new CrackerThread[5];

            // CrackerThread��ư
            for (int i = 0; i < t.length; i++) {
                t[i] = new CrackerThread(gate);
                t[i].start();
            }

            // CrackerThread��λ�Ԥ�
            for (int i = 0; i < t.length; i++) {
                try {
                    t[i].join();
                } catch (InterruptedException e) {
                }
            }

            // ��ǧ
            if (gate.getCounter() == 0) {
                // ̷�⤷�Ƥ��ʤ�
                System.out.print(".");
            } else {
                // ̷���ȯ������
                System.out.println("SecurityGate is NOT safe!");
                System.out.println("getCounter() == " + gate.getCounter());
                System.out.println("trial = " + trial);
                break;
            }
        }
    }
}

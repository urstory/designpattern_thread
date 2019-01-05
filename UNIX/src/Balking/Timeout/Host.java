import java.util.concurrent.TimeoutException;

public class Host {
    private final long timeout; // �����ॢ������
    private boolean ready = false; // �᥽�åɼ¹Ԥ��Ƥ褤�ʤ�true

    public Host(long timeout) {
        this.timeout = timeout;
    }

    // ���֤��ѹ��򤹤�
    public synchronized void setExecutable(boolean on) {
        ready = on;
        notifyAll();
    }

    // ���֤�ͤ�����Ǽ¹Ԥ���
    public synchronized void execute() throws InterruptedException, TimeoutException {
        long start = System.currentTimeMillis(); // ���ϻ���
        while (!ready) {
            long now = System.currentTimeMillis(); // ���߻���
            long rest = timeout - (now - start); // �Ĥ���Ԥ�����
            if (rest <= 0) {
                throw new TimeoutException("now - start = " + (now - start) + ", timeout = " + timeout);
            }
            wait(rest);
        }
        doExecute();
    }

    // �ºݤν���
    private void doExecute() {
        System.out.println(Thread.currentThread().getName() + " calls doExecute");
    }
}

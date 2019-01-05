public class Log {
    private static final ThreadLocal<TSLog> tsLogCollection = new ThreadLocal<TSLog>();

    // �����
    public static void println(String s) {
        getTSLog().println(s);
    }

    // �����Ĥ���
    public static void close() {
        getTSLog().close();
    }

    // ����åɸ�ͭ�Υ�������
    private static TSLog getTSLog() {
        TSLog tsLog = tsLogCollection.get();

        // ���Υ���åɤ���θƤӽФ����Ϥ���Ƥʤ顢��������������Ͽ����
        if (tsLog == null) {
            tsLog = new TSLog(Thread.currentThread().getName() + "-log.txt");
            tsLogCollection.set(tsLog);
            startWatcher(tsLog);
        }

        return tsLog;
    }

    // ����åɤν�λ���Ԥĥ���åɤ�ư����
    private static void startWatcher(final TSLog tsLog) {
        // ��λ��ƻ뤵������Υ���å�
        final Thread target = Thread.currentThread();
        // target��ƻ뤹�륹��å�
        final Thread watcher = new Thread() {
            public void run() {
                System.out.println("startWatcher for " + target.getName() + " BEGIN");
                try {
                    target.join();
                } catch (InterruptedException e) {
                }
                tsLog.close();
                System.out.println("startWatcher for " + target.getName() + " END");
            }
        };
        // �ƻ�γ���
        watcher.start();
    }
}

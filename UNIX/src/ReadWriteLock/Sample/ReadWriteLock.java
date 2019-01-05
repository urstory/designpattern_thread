public final class ReadWriteLock {
    private int readingReaders = 0; // (A) �ºݤ��ɤ�Ǥ������Υ���åɤο�
    private int waitingWriters = 0; // (B) �񤯤Τ��ԤäƤ��륹��åɤο�
    private int writingWriters = 0; // (C) �ºݤ˽񤤤Ƥ������Υ���åɤο�
    private boolean preferWriter = true; // �񤯤Τ�ͥ�褹��ʤ�true

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders++;                       // (A) �ºݤ��ɤ�Ǥ��륹��åɤο���1���䤹
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // (A) �ºݤ��ɤ�Ǥ��륹��åɤο���1���餹
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;                       // (B) �񤯤Τ��ԤäƤ��륹��åɤο���1���䤹
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;                   // (B) �񤯤Τ��ԤäƤ��륹��åɤο���1���餹
        }
        writingWriters++;                       // (C) �ºݤ˽񤤤Ƥ��륹��åɤο���1���䤹
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // (C) �ºݤ˽񤤤Ƥ��륹��åɤο���1���餹
        preferWriter = false;
        notifyAll();
    }
}

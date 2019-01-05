public final class ReadWriteLock {
    private int readingReaders = 0; // (a) �ºݤ��ɤ�Ǥ��륹��åɤο�
    private int writingWriters = 0; // (b) �ºݤ˽񤤤Ƥ��륹��åɤο�

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0) {
            wait();
        }
        readingReaders++;                       // (a) �ºݤ��ɤ�Ǥ��륹��åɤο���1���䤹
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // (a) �ºݤ��ɤ�Ǥ��륹��åɤο���1���餹
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        while (readingReaders > 0 || writingWriters > 0) {
            wait();
        }
        writingWriters++;                       // (b) �ºݤ˽񤤤Ƥ��륹��åɤο���1���䤹
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // (b) �ºݤ˽񤤤Ƥ��륹��åɤο���1���餹
        notifyAll();
    }
}

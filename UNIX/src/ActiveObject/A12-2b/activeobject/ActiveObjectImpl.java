package activeobject;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import java.math.BigInteger;

// ActiveObject���󥿥ե������μ������饹
class ActiveObjectImpl implements ActiveObject {
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    // �����ӥ��ν�λ
    public void shutdown() {
        service.shutdown();
    }

    // ����ͤΤ���ƤӽФ�
    public Future<String> makeString(final int count, final char fillchar) {
        // �ꥯ������
        class MakeStringRequest implements Callable<String> {
            public String call() {
                char[] buffer = new char[count];
                for (int i = 0; i < count; i++) {
                    buffer[i] = fillchar;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                return new String(buffer);
            }
        }
        // �ꥯ�����Ȥ�ȯ��
        return service.submit(new MakeStringRequest());
    }

    // ����ͤΤʤ��ƤӽФ�
    public void displayString(final String string) {
        // �ꥯ������
        class DisplayStringRequest implements Runnable {
            public void run() {
                try {
                    System.out.println("displayString: " + string);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
        // �ꥯ�����Ȥ�ȯ��
        service.execute(new DisplayStringRequest());
    }

    // ����ͤΤ���ƤӽФ�
    public Future<String> add(final String x, final String y) {
        // �ꥯ������
        class AddRequest implements Callable<String> {
            public String call() throws NumberFormatException {
                BigInteger bigX = new BigInteger(x);
                BigInteger bigY = new BigInteger(y);
                BigInteger bigZ = bigX.add(bigY);
                return bigZ.toString();
            }
        }
        // �ꥯ�����Ȥ�ȯ��
        return service.submit(new AddRequest());
    }
}

class Something {
    // final�ʥ��󥹥��󥹥ե������
    private final int x;
    // ���饹�ե������
    private static Something last = null;

    // ���󥹥ȥ饯��
    private Something() {
        // final�ե�����ɤ�����Ū�˽��������
        x = 123;
    }

    // new������줿���󥹥��󥹤�last����������
    public static Something create() {
        last = new Something();
        return last;
    }

    // last��ͳ��final�ե�����ɤ��ͤ�ɽ������
    public static void print() {
        if (last != null) {
            System.out.println(last.x);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // ����å�A
        new Thread() {
            public void run() {
                Something.create();
            }
        }.start();

        // ����å�B
        new Thread() {
            public void run() {
                Something.print();
            }
        }.start();
    }
}

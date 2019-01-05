public class Host {
    public Data request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");

        // (1) FutureData�Υ��󥹥��󥹤���
        final FutureData future = new FutureData();

        // (2) RealData���뤿��ο���������åɤ�ư����
        new Thread() {
            public void run() {
                try {
                    RealData realdata = new RealData(count, c);
                    future.setRealData(realdata);
                } catch (Exception e) {
                    future.setException(e);
                }
            }
        }.start();

        System.out.println("    request(" + count + ", " + c + ") END");

        // (3) FutureData�Υ��󥹥��󥹤�����ͤȤ���
        return future;
    }
}

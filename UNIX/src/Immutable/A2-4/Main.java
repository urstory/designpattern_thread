public class Main {
    public static void main(String[] args) {
        // ���󥹥��󥹺���
        UserInfo userinfo = new UserInfo("Alice", "Alaska");

        // ɽ��
        System.out.println("userinfo = " + userinfo);

        // ���֤��ѹ�
        StringBuffer info = userinfo.getInfo();
        info.replace(12, 17, "Bobby");  // 12�ʾ�17̤����"Alice"�ΰ���

        // ����ɽ��
        System.out.println("userinfo = " + userinfo);
    }
}

public class Main {
    public static void main(String[] args) {
        String s = "BAT";
        String t = s.replace('B', 'C'); // 'B'��'C'���ִ�
        System.out.println("s = " + s); // replace��¹Ԥ������s
        System.out.println("t = " + t); // replace�������t
        if (s == t) {
            System.out.println("s == t");
        } else {
            System.out.println("s != t");
        }
    }
}

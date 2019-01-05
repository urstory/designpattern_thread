public class Bank {
    private int money;
    private String name;

    public Bank(String name, int money) {
        this.name = name;
        this.money = money;
    }

    // �¶⤹��
    public synchronized void deposit(int m) {
        money += m;
    }

    // �����Ф�
    public synchronized boolean withdraw(int m) {
        if (money >= m) {
            money -= m;
            return true;    // �����Ф���
        } else {
            return false;   // �Ĺ���­
        }
    }

    public String getName() {
        return name;
    }
}

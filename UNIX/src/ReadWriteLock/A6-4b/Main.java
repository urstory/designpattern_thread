public class Main {
    public static void main(String[] args) {
        Database<String,String> database  = new Database<String,String>();

        // AssignThread����åɤε�ư
        new AssignThread(database, "Alice", "Alaska").start();
        new AssignThread(database, "Alice", "Australia").start();
        new AssignThread(database, "Bobby", "Brazil").start();
        new AssignThread(database, "Bobby", "Bulgaria").start();

        // RetrieveThread����åɤε�ư
        for (int i = 0; i < 100; i++) {
            new RetrieveThread(database, "Alice").start();
            new RetrieveThread(database, "Bobby").start();
        }

        // ��10�ô����
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }

        // ������λ
        System.exit(0);
    }
}

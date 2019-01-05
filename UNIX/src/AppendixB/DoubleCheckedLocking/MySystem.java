// ��������ư��뤳�Ȥ��ݾڤ���ʤ�
import java.util.Date;

public class MySystem {
    private static MySystem instance = null;
    private Date date = new Date();
    private MySystem() {
    }
    public Date getDate() {
        return date;
    }
    public static MySystem getInstance() {
        if (instance == null) {                 // (a) 1���ܤ�test
            synchronized (MySystem.class) {     // (b) synchronized�֥�å�������
                if (instance == null) {         // (c) 2���ܤ�test
                    instance = new MySystem();  // (d) set
                }
            }                                   // (e) synchronized�֥�å�����Ф�
        }
        return instance;                        // (f)
    }
}

import java.util.Map;
import java.util.HashMap;

public class Database<K,V> {
    private final Map<K,V> map = new HashMap<K,V>();

    // ���٤ƥ��ꥢ����
    public synchronized void clear() {
        verySlowly();
        map.clear();
    }

    // key��value�������Ƥ�
    public synchronized void assign(K key, V value) {
        verySlowly();
        map.put(key, value);
    }

    // key�˳�����Ƥ��ͤ��������
    public synchronized V retrieve(K key) {
        slowly();
        return map.get(key);
    }

    // �����˻��֤������뤳�Ȥ򥷥ߥ�졼�Ȥ���
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }

    // �����ˤȤƤ���֤������뤳�Ȥ򥷥ߥ�졼�Ȥ���
    private void verySlowly() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }
}

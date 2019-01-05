import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;

public class Data {
    private final String filename;  // ��¸����ե������̾��
    private String content;         // �ǡ���������
    private boolean changed;        // �ѹ��������Ƥ���¸����Ƥ��ʤ��ʤ�true

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    // �ǡ��������Ƥ�񤭴�����
    public synchronized void change(String newContent) {
        content = newContent;
        changed = true;
    }

    // �ǡ��������Ƥ��ѹ�����Ƥ�����ե��������¸����
    public void save() throws IOException {   // not synchronized
        if (!changed) {
            System.out.println(Thread.currentThread().getName() + " balks");
            return;
        }
        doSave();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        changed = false;
    }

    // �ǡ��������Ƥ�ºݤ˥ե��������¸����
    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}

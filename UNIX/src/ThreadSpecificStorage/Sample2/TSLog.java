import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TSLog {
    private PrintWriter writer = null;

    // writer�ե�����ɤν����
    public TSLog(String filename) {
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // �����
    public void println(String s) {
        writer.println(s);
    }

    // �����Ĥ���
    public void close() {
        writer.println("==== End of log ====");
        writer.close();
    }
}

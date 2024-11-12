import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class KeyRenderTool extends JFrame implements KeyListener {

    private final List<String> keyLog; // 用于存储按键记录

    public KeyRenderTool() {
        setTitle("Keyboard Renderer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        keyLog = new ArrayList<>();
        addKeyListener(this);
        setVisible(true);

        // 使用定时器每 17ms 渲染输出一次记录
        Timer timer = new Timer(170, e -> renderKeys());
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 记录按键字符
        String key = String.valueOf(transChar(e.getKeyChar()));
        synchronized (keyLog) {
            keyLog.add(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 键释放事件，暂不处理
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 键类型事件，暂不处理
    }

    // 每17ms调用一次，将这段时间内的按键输出并清空列表
    private void renderKeys() {
        synchronized (keyLog) {
            if (!keyLog.isEmpty()) {
                System.out.println(System.currentTimeMillis()+": " + String.join(", ", keyLog));
                keyLog.clear();
            }
        }
    }
    private String transChar(char keybortChar){
        String res;
        switch (keybortChar){
            case 'a': res = "\u2190";break;
            case 's': res = "\u2193";break;
            case 'w': res = "\u2191";break;
            case 'd': res = "\u2192";break;
            case 'u':
                case 'i':
                case 'o':
                    res = "\uD83E\uDD1C";break;
            case 'j':
                case 'k':
                case 'l':
                    res = "\uD83E\uDDB6";break;

            default:res = String.valueOf(keybortChar);
        }
        return res;
    }

    public static void main(String[] args) {
        new KeyRenderTool();
    }
}

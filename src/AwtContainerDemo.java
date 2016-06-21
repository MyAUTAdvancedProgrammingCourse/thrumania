import java.awt.*;
import java.awt.event.*;

public class AwtContainerDemo {
    private Frame mainFrame;
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;
    private Label msglabel;

    public AwtContainerDemo(){
        prepareGUI();
    }

    public static void main(String[] args){
        AwtContainerDemo  awtContainerDemo = new AwtContainerDemo();
        awtContainerDemo.showWindowDemo();
    }

    private void prepareGUI(){
        mainFrame = new Frame("Java AWT Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);
        statusLabel = new Label();
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(350,100);

        msglabel = new Label();
        msglabel.setAlignment(Label.CENTER);
        msglabel.setText("Welcome to TutorialsPoint AWT Tutorial.");

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showWindowDemo(){
        headerLabel.setText("Container in action: Window");
        final MessageWindow window =
                new MessageWindow(mainFrame,
                        "Welcome to TutorialsPoint AWT Tutorial.");

        Button okButton = new Button("Open a Window");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.setVisible(true);
                statusLabel.setText("A Window shown to the user.");
            }
        });
        controlPanel.add(okButton);
        mainFrame.setVisible(true);
    }

    class MessageWindow extends Window{
        private String message;

        public MessageWindow(Frame parent, String message) {
            super(parent);
            this.message = message;
            setSize(300, 300);
            setLocationRelativeTo(parent);
            setBackground(Color.gray);
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.drawRect(0,0,getSize().width - 1,getSize().height - 1);
            g.drawString(message,50,150);
        }
    }
}
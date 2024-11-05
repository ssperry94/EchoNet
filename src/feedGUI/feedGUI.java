import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class feedGUI{

    public feedGUI(){

        //**WINDOW
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Feed");
 
        //*Button
        //create button
        JButton button = new JButton("Click Me");

        //listens(waits) for action
        button.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                System.out.println("Button Clicked!");
            }

        });

        panel.add(button);

        
        frame.pack();
        frame.setVisible(true);

        
        JLabel post = new JLabel("Test");
        post.setText("Label Text");
    }

    public static void main(String[] args){
        new GUI();
    }
}
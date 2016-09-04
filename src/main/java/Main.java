import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;

/**
 * Created by oji on 16/0904.
 */
public class Main {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("税込み計算機");
        frame.setVisible(true);
    }
}

class MyFrame extends JFrame implements ActionListener{

    private Container container;
    private JLabel lable, view;
    private JTextField getTax;
    private JButton button;
    private JPanel panelUp, panelCenter, panelButton;
    private boolean isClick = false;


    public MyFrame(String title) {
        setTitle(title);
        setPreferredSize(new Dimension(250,100));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        container = getContentPane();

        lable = new JLabel("税込み計算");
        view  = new JLabel();
        getTax= new JTextField(10);
        button= new JButton("算出");
        button.addActionListener(this);

        panelUp     = new JPanel();
        panelCenter = new JPanel();
        panelButton = new JPanel();

        panelUp.add(lable);
        panelCenter.add(getTax);
        panelCenter.add(button);
        panelButton.add(view);

        container.add(panelUp,     BorderLayout.NORTH);
        container.add(panelCenter, BorderLayout.CENTER);
        container.add(panelButton, BorderLayout.SOUTH);


    }

     public void actionPerformed(ActionEvent actionEvent) {

         if (!isClick && getTax.getText().length() != 0) {
             String tax_Str = getTax.getText().toString();
             tax_Str = tax_Str.replaceAll("[^0-9]", "");
             BigDecimal tax_big = new BigDecimal(tax_Str);
             tax_big = tax_big.multiply(new BigDecimal("1.08"));
             tax_big = tax_big.setScale(0,BigDecimal.ROUND_DOWN);

             StringBuffer tax_SB = new StringBuffer();
             tax_SB.append(tax_big.toString());
             int size = tax_SB.length();

             if (size >= 4) {
                 for (int i = 0;i < (size / 3); i++) {
                     if ((size % 3) == 0 && (size - (i +1) * 3) < 3) {
                         break;
                     }
                     tax_SB.insert(size - (i + 1) * 3, ",");
                 }
             }

             view.setText(tax_SB.toString() + "円");
             button.setText("もう一回");
             isClick = true;
        } else {
            getTax.setText("");
            view.setText("");
             button.setText("算出");
             isClick = false;

             getTax.setFocusable(true);
         }
    }
}

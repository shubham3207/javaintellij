import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.*;

public class product {
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtQty;
    private JButton saveButton1;
    private JButton deleteButton1;
    private JButton updateButton;
    private JTextField txtpid;
    private JButton searchButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("product");
        frame.setContentPane(new product().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public product() {
        connect();

        saveButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name,price,qty;

                name = txtName.getText();
                price = txtPrice.getText();
                qty = txtQty.getText();
                try {
                    pst = con.prepareStatement("insert into products(pname,price,qty)values(?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, price);
                    pst.setString(3, qty);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Addedddddd!!!!");

                    txtName.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String pid = txtpid.getText();
                    pst = con.prepareStatement("select pname,price,qty from products where pid = ?");
                    pst.setString(1, pid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String name = rs.getString(1);
                        String price = rs.getString(2);
                        String qty = rs.getString(3);

                        txtName.setText(name);
                        txtPrice.setText(price);
                        txtQty.setText(qty);
                    }
                    else
                    {
                        txtName.setText("");
                        txtPrice.setText("");
                        txtQty.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Product ID");

                    }
                }

                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });



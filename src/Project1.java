/*  Name:  Kevin Galeano
     Course: CNT 4714 – Fall 2021
     Assignment title: Project 1 – Event-driven Enterprise Simulation
     Date: Sunday September 12, 2021
*/
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Project1 extends JFrame{
    ArrayList<String> inputFile = new ArrayList<String>(); // Create an ArrayList object

    private JPanel mainPanel;
    private JButton processItemButton;
    private JButton confirmItemButton;
    private JButton viewOrderButton;
    private JButton finishOrderButton;
    private JButton newOrderButton;
    private JButton exitButton;
    private JLabel enterItemIDfor1;
    private JLabel enterNumberOfItems;
    private JLabel PreviousItemInfo;
    private JLabel orderSubtotal;


    private JTextField numberOfDifItemsField;
    private JTextField enterItemID;
    private JTextField enterQuantity;

    public int itemsInOrder;
    public String curItem;
    public int curItemQuantity;
    String[] searchResult;

    public Project1(String title) throws FileNotFoundException {
        super(title);
        Scanner in = new Scanner(new FileReader("inventory.txt"));
        while(in.hasNext()){

            inputFile.add(in.nextLine());
        }
        in.close();

        System.out.println(inputFile.get(0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        confirmItemButton.setEnabled(false);
        viewOrderButton.setEnabled(false);
        finishOrderButton.setEnabled(false);
        this.pack();
        ;
        processItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Don't do anything unless the fields are are accounted for.
                if (!numberOfDifItemsField.getText().isEmpty() && !enterItemID.getText().isEmpty() && !enterQuantity.getText().isEmpty()) {
                    itemsInOrder = (Integer.parseInt(numberOfDifItemsField.getText()));
                    curItem = enterItemID.getText();
                    curItemQuantity = (Integer.parseInt(enterQuantity.getText()));
                    searchResult = searchInventory(curItem);
                    if (searchResult.length != 0) {
                        System.out.println(curItem);
                        System.out.println(Arrays.toString(searchResult));
                        //todo: check if the item is even in stock search result 2
                        //todo calculate price for search result 3
                        PreviousItemInfo.setText(searchResult[0] + " " + searchResult[1] + " " + searchResult[3]);
                    } else {
                        System.out.println("item doesnt exist");
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws FileNotFoundException {

        JFrame frame = new Project1("Project 1");
        frame.setVisible(true);
    }

    //todo confirm good return
    public String[] searchInventory(String itemId){
        String[] arrOfStr;
        String[] info = new String[0];
        for(int i = 0; i < inputFile.size(); i++){
            arrOfStr = inputFile.get(i).split(",");
            if(itemId.equals(arrOfStr[0])){
                System.out.println(arrOfStr[0]);
                System.out.println(Arrays.toString(arrOfStr));
                info = arrOfStr;
                return info;
            }
        }
        return info;

    }
}

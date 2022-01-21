package ePortfolio;

/**
 * Portfolio.java is a class file that represents the entire investment portfolio
 * where multiple stock and mutual fund investments are managed according to
 * the requirements of the investor
 * @author  Khushi Rajput
 * @version 3.0
 * @since   2021-10-18
 */

import java.io.PrintStream;
import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Portfolio {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ePortfolio");
        String message = "Welcome to ePortfolio. \n\n\n Choose a command from the \"Commands\" menu to buy or sell an investment \n update prices for all investments, get gain for the portfolio, \n search for relevant investments, or quit the program.";
        JTextArea jt = new JTextArea(message);

        ArrayList<Investment> mainList = new ArrayList<Investment>();
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

//interface for buy
        class Buy extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                JPanel panelFirst = new JPanel();
                JPanel panelSecond = new JPanel();

                panelFirst.setLayout(new FlowLayout());

                JPanel panelOne = new JPanel();
                JPanel buttonPanel = new JPanel();
                JLabel label = new JLabel("Buy an Investment: ");

                JPanel typePanel = new JPanel();
                typePanel.setLayout(new FlowLayout());
                JLabel typeLabel = new JLabel("Type:  ");
                String[] investments = {"Stock", "MutualFund"};
                JComboBox list = new JComboBox(investments);
                list.setSelectedIndex(0);   //default selection - Stock
                typePanel.add(typeLabel);
                typePanel.add(list);


                JPanel symbol = new JPanel();
                symbol.setLayout(new FlowLayout());
                JLabel symLabel = new JLabel("Symbol:  ");
                JTextField text = new JTextField(20);
                symbol.add(symLabel);
                symbol.add(text);


                JPanel name = new JPanel();
                name.setLayout(new FlowLayout());
                JLabel nameLabel = new JLabel("Name:  ");
                JTextField nameText = new JTextField(35);
                name.add(nameLabel);
                name.add(nameText);


                JPanel quantity = new JPanel();
                quantity.setLayout(new FlowLayout());
                JLabel quanLabel = new JLabel("Quantity:  ");
                JTextField quanText = new JTextField(20);
                quantity.add(quanLabel);
                quantity.add(quanText);

                JPanel price = new JPanel();
                price.setLayout(new FlowLayout());
                JLabel priceLabel = new JLabel("Price:  ");
                JTextField priceText = new JTextField(20);
                price.add(priceLabel);
                price.add(priceText);

                panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS));
                panelOne.add(label);
                panelOne.add(typePanel);
                panelOne.add(symbol);
                panelOne.add(name);
                panelOne.add(quantity);
                panelOne.add(price);

                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                JButton reset = new JButton("Reset");
                JButton buy = new JButton("Buy");
                buttonPanel.add(reset);
                buttonPanel.add(buy);

                panelFirst.add(panelOne);
                panelFirst.add(buttonPanel);

                panelSecond.setLayout(new BoxLayout(panelSecond, BoxLayout.Y_AXIS));
                JLabel title = new JLabel("Messages - ");
                JTextArea messages = new JTextArea();
                messages.setBounds(50, 45, 500, 500);
                messages.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(messages);
                panelSecond.add(title);
                panelSecond.add(messages);
                panelSecond.add(scrollPane);

                mainPanel.add(panelFirst);
                mainPanel.add(panelSecond);
                frame.add(mainPanel, BorderLayout.NORTH);
                frame.setContentPane(mainPanel);
                jt.setVisible(false);
                frame.setVisible(true);

                buy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String investType = "";
                        if (list.getSelectedIndex() == 0) {
                            investType = "Stock";
                        } else if (list.getSelectedIndex() == 1) {
                            investType = "MutualFund";
                        }
                        String investSymbol = text.getText();
                        String investName = nameText.getText();
                        String investQ = quanText.getText();
                        String investP = priceText.getText();
                        int q = Integer.parseInt(investQ);
                        double p = Double.parseDouble(investP);

                        if(investSymbol.isEmpty()||investName.isEmpty()||investQ.isEmpty()||investP.isEmpty()) {
                            messages.setText("User must enter values for all fields. Try Again!\nPress the Reset button to re-enter values.");
                        }else if(q == 0 || q < 0 || p == 0.0 || p < 0.0) {
                            messages.setText("Quantity and Price cannot be zero or negative. Incorrect Input. Try Again! \nPress the Reset button to re-enter values.");
                        }else {
                            Stock stockObj = new Stock();
                            MutualFund fundObj = new MutualFund();
                            String statement = "";
                            int index = 0;
                            int k = 0;
                            for (int i = 0; i < mainList.size(); i++) {
                                //if an investment with the given symbol already exists
                                if (investSymbol.equalsIgnoreCase(mainList.get(i).getSymbol())) {
                                    index = i;
                                    k = 1;
                                }
                            }
                            if (k == 1) {
                                //if an investment with the given symbol already exists
                                int q1 = mainList.get(index).getQuantity();
                                double p1 = mainList.get(index).getPrice();
                                int newQ = q + q1;
                                mainList.get(index).setQuantity(newQ);
                                mainList.get(index).setPrice(p);
                                double value = 0.0;
                                double v = 0.0;
                                if (investType.equalsIgnoreCase("Stock")) {
                                    value = stockObj.computeBookValue(q, p);    //book value with new values
                                    v = stockObj.computeBookValue(q1, p1);      //book value of currently present investment before adding quantity
                                } else if (investType.equalsIgnoreCase("mutualfund")) {
                                    value = fundObj.computeBookValue(q, p);
                                    v = fundObj.computeBookValue(q1, p1);
                                }
                                double newValue = v + value;        //updated book value for the new quantity
                                mainList.get(index).setBookValue(newValue);
                            } else if (k == 0) {
                                //if this is a fresh investment, add it to the list
                                double value = 0.0;
                                if (investType.equalsIgnoreCase("stock")) {
                                    value = stockObj.computeBookValue(q, p);
                                    stockObj = new Stock(investType, investSymbol, investName, q, p, value);
                                    mainList.add(stockObj);
                                    statement = stockObj.toString();
                                } else if (investType.equalsIgnoreCase("mutualfund")) {
                                    value = fundObj.computeBookValue(q, p);
                                    fundObj = new MutualFund(investType, investSymbol, investName, q, p, value);
                                    mainList.add(fundObj);
                                    statement = fundObj.toString();
                                }
                            }
                            int position = mainList.size(); //getting the size of the list after adding new Investment - last position
                            String[] tokens = investName.split("\\s+");
                            for (int j = 0; j < tokens.length; j++) {
                                //creating mapping entries for each of the name tokens of the newly bought investment
                                if (map.containsKey(tokens[j])) {
                                    map.get(tokens[j]).add(position - 1);
                                } else {
                                    ArrayList<Integer> array = new ArrayList<Integer>();
                                    array.add(position - 1);
                                    map.put(tokens[j], array);
                                }
                            }
                            messages.setText(statement);
                            messages.append("\nThe purchase was successful!\n");
                        }
                        reset.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                text.setText("");
                                nameText.setText("");
                                quanText.setText("");
                                priceText.setText("");
                            }
                        });
                    }
                });
            }
        }

        //interface for sell
        class Sell extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                JPanel panelFirst = new JPanel();
                JPanel panelSecond = new JPanel();

                panelFirst.setLayout(new FlowLayout());

                JPanel panelOne = new JPanel();
                JPanel buttonPanel = new JPanel();

                JLabel label = new JLabel("Sell an Investment: ");

                JPanel typePanel = new JPanel();
                typePanel.setLayout(new FlowLayout());
                JLabel typeLabel = new JLabel("Type:  ");
                String[] investments = {"Stock", "MutualFund"};
                JComboBox list = new JComboBox(investments);
                list.setSelectedIndex(0);   //default selection - Stock
                typePanel.add(typeLabel);
                typePanel.add(list);

                JPanel symbol = new JPanel();
                symbol.setLayout(new FlowLayout());
                JLabel symLabel = new JLabel("Symbol:  ");
                JTextField text = new JTextField(20);
                symbol.add(symLabel);
                symbol.add(text);

                JPanel quantity = new JPanel();
                quantity.setLayout(new FlowLayout());
                JLabel quanLabel = new JLabel("Quantity:  ");
                JTextField quanText = new JTextField(20);
                quantity.add(quanLabel);
                quantity.add(quanText);

                JPanel price = new JPanel();
                price.setLayout(new FlowLayout());
                JLabel priceLabel = new JLabel("Price:  ");
                JTextField priceText = new JTextField(20);
                price.add(priceLabel);
                price.add(priceText);

                panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS));
                panelOne.add(label);
                panelOne.add(typePanel);
                panelOne.add(symbol);
                panelOne.add(quantity);
                panelOne.add(price);

                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                JButton reset = new JButton("Reset");
                JButton sell = new JButton("Sell");
                buttonPanel.add(reset);
                buttonPanel.add(sell);

                panelFirst.add(panelOne);
                panelFirst.add(buttonPanel);

                panelSecond.setLayout(new BoxLayout(panelSecond, BoxLayout.Y_AXIS));
                JLabel title = new JLabel("Messages - ");
                JTextArea messages = new JTextArea();
                messages.setBounds(50, 45, 500, 500);
                messages.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(messages);
                panelSecond.add(title);
                panelSecond.add(scrollPane);
                panelSecond.setPreferredSize(new Dimension(getWidth(), 600));

                mainPanel.add(panelFirst);
                mainPanel.add(panelSecond);
                frame.add(mainPanel, BorderLayout.NORTH);
                frame.setContentPane(mainPanel);
                jt.setVisible(false);
                frame.setVisible(true);

                sell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String investType = "";
                        if (list.getSelectedIndex() == 0) {
                            investType = "Stock";
                        } else if (list.getSelectedIndex() == 1) {
                            investType = "MutualFund";
                        }
                        String investSymbol = text.getText();
                        String investQ = quanText.getText();
                        String investP = priceText.getText();
                        int q = Integer.parseInt(investQ);  //the quantity specified to be sold
                        double p = Double.parseDouble(investP);
                        if (investSymbol.isEmpty() || investQ.isEmpty() || investP.isEmpty()) {
                            messages.setText("User must enter values for all fields. Try Again!\nPress the Reset button to re-enter values.");
                        } else if (q == 0 || q < 0 || p == 0.0 || p < 0.0) {
                            messages.setText("Quantity and Price cannot be zero or negative. Incorrect Input. Try Again! \nPress the Reset button to re-enter values.");
                        } else {
                            Stock stockObj = new Stock();
                            MutualFund fundObj = new MutualFund();

                            int index = 0;
                            int k = 0;
                            for (int i = 0; i < mainList.size(); i++) {
                                String symb = mainList.get(i).getSymbol();
                                if (investSymbol.equalsIgnoreCase(symb)) {
                                    index = i;
                                    k = 1;
                                }
                            }

                            if (k == 1) {
                                int q1 = mainList.get(index).getQuantity();    //original quantity before selling
                                if (q > q1) {
                                    messages.setText("Insufficient quantity to sell.\n");
                                    System.exit(0);
                                }

                                if (q == q1) {
                                    double p1 = mainList.get(index).getPrice(); //get original price of investment
                                    double payment = 0.0;
                                    if (investType.equalsIgnoreCase("stock")) {
                                        payment = stockObj.payment(q, p);
                                    } else if (investType.equalsIgnoreCase("mutualfund")) {
                                        payment = fundObj.payment(q, p);
                                    }
                                    messages.setText("The payment received for this sell is: $" + payment);
                                    messages.append("The sell was successful!\n");
                                    mainList.remove(index);

                                    //updating mapping entry after deleting an investment
                                    for (int i = 0; i < map.size(); i++) {
                                        Set<String> keys = map.keySet();
                                        for (String key : keys) {
                                            ArrayList<Integer> values = map.get(key);
                                            ListIterator<Integer> itr = values.listIterator();
                                            while (itr.hasNext()) {
                                                int x = (Integer) itr.next();
                                                if (x == (index)) {
                                                    itr.remove();   //remove the deleted position
                                                }
                                                if (x > (index)) {
                                                    itr.set(index + 1); //decrement the positions greater than the deleted position
                                                }
                                            }
                                        }
                                    }
                                }
                                if (q1 > q) {
                                    int newQ = q1 - q;
                                    mainList.get(index).setQuantity(newQ);

                                    double p1 = mainList.get(index).getPrice(); //get original price of investment
                                    double payment = 0.0;
                                    double orgValue = 0.0;
                                    double newBookValue = 0.0;
                                    if (investType.equalsIgnoreCase("stock")) {
                                        payment = stockObj.payment(q, p);
                                        orgValue = stockObj.computeBookValue(q1, p1);
                                        newBookValue = orgValue * (q1 - q) / q1;
                                    } else if (investType.equalsIgnoreCase("mutualfund")) {
                                        payment = fundObj.payment(q, p);
                                        orgValue = fundObj.computeBookValue(q1, p1);
                                        newBookValue = orgValue * (q1 - q) / q1;
                                    }
                                    mainList.get(index).setPrice(p);    //set new price for investment
                                    mainList.get(index).setBookValue(newBookValue);    //updating the book value after selling
                                    messages.setText("The payment received for this sell is: $" + payment);
                                    messages.append("The sell was successful!\n");
                                }
                            }

                            if (k == 0) {
                                messages.setText("Investment doesn't exist.");
                            }
                        }
                        reset.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                text.setText("");
                                quanText.setText("");
                                priceText.setText("");
                            }
                        });
                    }
                });
            }
        }

        //interface for update
        class Update extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                JPanel panelFirst = new JPanel();
                JPanel panelSecond = new JPanel();

                panelFirst.setLayout(new FlowLayout());

                JPanel panelOne = new JPanel();
                JPanel buttonPanel = new JPanel();

                JLabel label = new JLabel("Updating Price of Investments: ");
                JPanel symbol = new JPanel();
                symbol.setLayout(new FlowLayout());
                JLabel symLabel = new JLabel("Symbol:  ");
                String mySymbol = mainList.get(0).getSymbol();
                JTextField text = new JTextField(mySymbol, 30);
                text.setEditable(false);
                symbol.add(symLabel);
                symbol.add(text);

                JPanel name = new JPanel();
                name.setLayout(new FlowLayout());
                JLabel nameLabel = new JLabel("Name:  ");
                String myName = mainList.get(0).getName();
                JTextField nameText = new JTextField(myName, 30);
                nameText.setEditable(false);
                name.add(nameLabel);
                name.add(nameText);

                JPanel price = new JPanel();
                price.setLayout(new FlowLayout());
                JLabel priceLabel = new JLabel("Price:  ");
                JTextField priceText = new JTextField(20);
                price.add(priceLabel);
                price.add(priceText);

                panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS));
                panelOne.add(label);
                panelOne.add(symbol);
                panelOne.add(name);
                panelOne.add(price);

                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                JButton prev = new JButton("Prev");
                JButton next = new JButton("Next");
                JButton save = new JButton("Save");
                buttonPanel.add(prev);
                buttonPanel.add(next);
                buttonPanel.add(save);

                panelFirst.add(panelOne);
                panelFirst.add(buttonPanel);

                panelSecond.setLayout(new BoxLayout(panelSecond, BoxLayout.Y_AXIS));
                JLabel title = new JLabel("Messages - ");
                JTextArea messages = new JTextArea();
                messages.setBounds(50, 45, 500, 500);
                messages.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(messages);
                panelSecond.add(title);
                panelSecond.add(scrollPane);
                panelSecond.setPreferredSize(new Dimension(getWidth(), 600));

                mainPanel.add(panelFirst);
                mainPanel.add(panelSecond);
                frame.add(mainPanel, BorderLayout.NORTH);
                frame.setContentPane(mainPanel);
                jt.setVisible(false);
                frame.setVisible(true);

                //if its the start of the list previous button disabled
                String s = mainList.get(0).getSymbol();
                String s1 = text.getText();
                if(s.equalsIgnoreCase(s1)) {
                    prev.setEnabled(false);
                }else{
                    prev.setEnabled(true);
                }

                //if last investment, set next button as disabled
                int size = mainList.size();
                String s3 = mainList.get(size - 1).getSymbol();
                if(s3.equalsIgnoreCase(s)) {
                    next.setEnabled(false);
                }else{
                    next.setEnabled(true);
                }

                prev.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String symOne = text.getText();
                        int index = 0;
                        for(int i = 0; i < mainList.size(); i ++) {
                            String check = mainList.get(i).getSymbol();
                            if (check.equals(symOne)) {
                                index = i;
                            }
                        }
                        String symTwo = mainList.get(index - 1).getSymbol();
                        text.setText(symTwo);
                        text.setEditable(false);
                        String nameTwo = mainList.get(index - 1).getName();
                        nameText.setText(nameTwo);
                        nameText.setEditable(false);
                    }
                });

                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String symOne = text.getText();
                        int index = 0;
                        for(int i = 0; i < mainList.size(); i ++) {
                            String check = mainList.get(i).getSymbol();
                            if (check.equals(symOne)) {
                                index = i;
                            }
                        }
                        String symTwo = mainList.get(index + 1).getSymbol();
                        text.setText(symTwo);
                        text.setEditable(false);
                        String nameTwo = mainList.get(index + 1).getName();
                        nameText.setText(nameTwo);
                        nameText.setEditable(false);
                    }
                });

                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String symOne = text.getText();
                        int index = 0;
                        for(int i = 0; i < mainList.size(); i ++) {
                            String check = mainList.get(i).getSymbol();
                            if (check.equals(symOne)) {
                                index = i;
                            }
                        }
                        String investP = priceText.getText();
                        double p = Double.parseDouble(investP);
                        if(p == 0.0 || p < 0.0) {
                            messages.setText("Invalid value entered for price. Try Again.");
                        }else {
                            mainList.get(index).setPrice(p);
                            messages.setText("Price updated succesfully.\n");
                        }
                    }
                });
            }
        }

        //interface for gain
        class Gain extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                JPanel panelFirst = new JPanel();
                JPanel panelSecond = new JPanel();
                JPanel gainPanel = new JPanel();

                gainPanel.setLayout(new FlowLayout());
                panelFirst.setLayout(new BoxLayout(panelFirst, BoxLayout.Y_AXIS));

                JLabel title = new JLabel("Getting Total Gain - ");
                JLabel label = new JLabel("Total Gain: ");

                double totalGain = 0.0;
                double p = 0.0;
                int q = 0;
                double gain = 0.0;
                String type = "";
                Stock stockObj = new Stock();
                MutualFund fundObj = new MutualFund();
                for(int i = 0; i < mainList.size(); i++) {
                    p = mainList.get(i).getPrice();
                    q = mainList.get(i).getQuantity();
                    type = mainList.get(i).getType();
                    if(type.equalsIgnoreCase("stock")) {
                        gain = stockObj.computeGain(q, p);
                    }else if(type.equalsIgnoreCase("mutualfund")) {
                        gain = fundObj.computeGain(q, p);
                    }
                    totalGain += gain;
                }

                String gainString = String.valueOf(totalGain);
                JTextField displayGain = new JTextField(gainString);
                displayGain.setEditable(false);        //display purposes only
                gainPanel.add(label);
                gainPanel.add(displayGain);
                panelFirst.add(title);
                panelFirst.add(gainPanel);


                panelSecond.setLayout(new BoxLayout(panelSecond, BoxLayout.Y_AXIS));

                JLabel titleOne = new JLabel("Individual Gains - ");
                JTextArea messages = new JTextArea();
                messages.setBounds(50, 45, 500, 500);
                messages.setEditable(false);

                for(int i = 0; i < mainList.size(); i ++) {
                    String statement = mainList.get(i).toString();
                    int investQuantity = mainList.get(i).getQuantity();
                    double investPrice = mainList.get(i).getPrice();
                    double gainforthis = 0.0;
                    if(mainList.get(i).getType().equalsIgnoreCase("stock")) {
                        gainforthis = stockObj.computeGain(investQuantity, investPrice);
                    }else if(mainList.get(i).getType().equalsIgnoreCase("mutualfund")) {
                        gainforthis = fundObj.computeGain(investQuantity, investPrice);
                    }
                    String finalStatement = statement + "-Gain- : " + gainforthis;
                    messages.append(finalStatement + "\n\n");
                }
                JScrollPane scrollPane = new JScrollPane(messages);
                panelSecond.add(titleOne);
                panelSecond.add(scrollPane);
                panelSecond.setPreferredSize(new Dimension(getWidth(), 600));

                mainPanel.add(panelFirst);
                mainPanel.add(panelSecond);
                frame.add(mainPanel, BorderLayout.NORTH);
                frame.setContentPane(mainPanel);
                jt.setVisible(false);
                frame.setVisible(true);
            }
        }

        //interface for search
        class Search extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                JPanel panelFirst = new JPanel();
                JPanel panelSecond = new JPanel();

                panelFirst.setLayout(new FlowLayout());

                JPanel panelOne = new JPanel();
                JPanel buttonPanel = new JPanel();

                JLabel label = new JLabel("Searching Investments: ");
                JPanel symbol = new JPanel();
                symbol.setLayout(new FlowLayout());
                JLabel symLabel = new JLabel("Symbol:  ");
                JTextField text = new JTextField(20);
                symbol.add(symLabel);
                symbol.add(text);

                JPanel name = new JPanel();
                name.setLayout(new FlowLayout());
                JLabel nameLabel = new JLabel("Name Keyword:  ");
                JTextField nameText = new JTextField(20);
                name.add(nameLabel);
                name.add(nameText);


                panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS));
                panelOne.add(label);
                panelOne.add(symbol);
                panelOne.add(name);

                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                JButton reset = new JButton("Reset");
                JButton search = new JButton("Search");
                buttonPanel.add(reset);
                buttonPanel.add(search);

                panelFirst.add(panelOne);
                panelFirst.add(buttonPanel);

                panelSecond.setLayout(new BoxLayout(panelSecond, BoxLayout.Y_AXIS));
                JLabel title = new JLabel("Search Results - ");
                JTextArea messages = new JTextArea();
                messages.setBounds(50, 45, 500, 500);
                messages.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(messages);
                panelSecond.add(title);
                panelSecond.add(scrollPane);
                panelSecond.setPreferredSize(new Dimension(getWidth(), 600));

                mainPanel.add(panelFirst);
                mainPanel.add(panelSecond);
                frame.add(mainPanel, BorderLayout.NORTH);
                frame.setContentPane(mainPanel);
                jt.setVisible(false);
                frame.setVisible(true);

                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String investSymbol = text.getText();
                        String keywords = nameText.getText();

                        if (investSymbol.isEmpty() || keywords.isEmpty()) {
                            messages.setText("User needs to enter all field values. Try again.\n Press Reset to re-enter values.");
                        } else {
                            int k = 0;
                            Set<String> keys = map.keySet();
                            for (String key : keys) {
                                if (keywords.equalsIgnoreCase(key)) {
                                    k = 1;
                                    ArrayList<Integer> values = map.get(key);
                                    messages.setText("The investments matching your search request are: \n");
                                    for (Integer i : values) {
                                        String statement = mainList.get(i).toString();
                                        messages.append(statement);
                                        messages.append("\n");
                                    }
                                }
                            }
                            if (k != 1) {
                                messages.setText("Sorry - no investment with the given keyword exists!\n");
                            }
                        }

                        reset.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                text.setText("");
                                nameText.setText("");
                            }
                        });
                    }
                });
            }
        }


        //creating the GUI frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        //creating the menu bar and adding menu components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Commands");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Buy");
        JMenuItem m12 = new JMenuItem("Sell");
        JMenuItem m13 = new JMenuItem("Update");
        JMenuItem m14 = new JMenuItem("GetGain");
        JMenuItem m15 = new JMenuItem("Search");
        JMenuItem m16 = new JMenuItem("Quit");
        m16.addActionListener(new EndProgram());
        m11.addActionListener(new Buy());
        m12.addActionListener(new Sell());
        m13.addActionListener(new Update());
        m14.addActionListener(new Gain());
        m15.addActionListener(new Search());
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m1.add(m15);
        m1.add(m16);
        frame.setJMenuBar(mb);

        //initial interface welcome message
        frame.setLayout(new BorderLayout());
        jt.setPreferredSize(new Dimension(500, jt.getHeight()));
        jt.setEditable(false);
        jt.setVisible(true);
        frame.add(jt, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}



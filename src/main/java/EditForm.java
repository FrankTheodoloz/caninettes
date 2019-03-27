import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditForm extends JFrame implements ActionListener {
//public class EditForm extends JDialog implements ActionListener {

    public static final Integer WINDOW_WIDTH = 600;
    public static final Integer WINDOW_HEIGHT = 300;
    public static final Integer LABEL_HEIGHT = 20;
    public static final Integer LABEL_WIDTH = 80;
    public static final Integer TEXTFIELD_WIDTH = 40;
    public static final Integer BUTTON_HEIGHT = 20;
    public static final Integer BUTTON_WIDTH = 100;

    private static final String ADDRESS_LABEL = "Adresse";
    private static final String STATUS_LABEL = "Etat";
    private static final String NOTE_LABEL = "Remarques";
    private static final String NUMBER_LABEL = "Numéro";
    private static final String CLOSE_LABEL = "Fermer";
    private static final String SAVE_LABEL = "Enregistrer";

    private boolean isConnected;

//    AdminCani
//    adminMDP

    public static final String[] STATUSLIST = {"Posée", "En travaux", "Hors service"};

    // Buttons
    private JButton saveButton, exitButton;

    // Titles
    private JLabel addressTitleLabel, numberTitleLabel, statusTitleLabel, noteTitleLabel;
    private JLabel longitudeTitleLabel, latitudeTitleLabel;
    private static final Dimension dimension = new Dimension(LABEL_WIDTH, LABEL_HEIGHT);

    // Edit
    private JTextField addressEditTextField, numberEditTextField, noteEditTextField;
    private JTextField longitudeEditTextField, latitudeEditTextField;
    private JComboBox statusComboBox;

    // View
    private JLabel addressViewLabel, numberViewLabel, noteViewLabel;

    //panels
    private JPanel mainPanel, addressPanel, numberPanel, statusPanel, notePanel, buttonsPanel;

    // Values
    private Integer id;
    private String address;
    private String status;
    private String note;
    private String number;
    public double longitude;
    public double latitude;

    EditForm(String windowTitle, boolean isConnected) {

        this.isConnected = isConnected;

        //FRAME PROPERTIES
        setTitle(windowTitle);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        //FRAME CONTENT
        mainPanel = new JPanel(new GridLayout(0, 1));
        add(mainPanel);

        //address
        addressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(addressPanel);
        addressTitleLabel = new JLabel(ADDRESS_LABEL + " :");
        addressTitleLabel.setPreferredSize(dimension);
        addressPanel.add(addressTitleLabel);

        //number
        numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(numberPanel);
        numberTitleLabel = new JLabel(NUMBER_LABEL + " :");
        numberTitleLabel.setPreferredSize(dimension);
        numberPanel.add(numberTitleLabel);

        //status
        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(statusPanel);
        statusTitleLabel = new JLabel(STATUS_LABEL + " :");
        statusTitleLabel.setPreferredSize(dimension);
        statusPanel.add(statusTitleLabel);
        statusComboBox = new JComboBox(STATUSLIST);
        statusComboBox.setMinimumSize(dimension);
        statusComboBox.addActionListener(this);
        statusPanel.add(statusComboBox);

        //note
        notePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(notePanel);
        noteTitleLabel = new JLabel(NOTE_LABEL + " :");
        noteTitleLabel.setPreferredSize(dimension);

        notePanel.add(noteTitleLabel);

        //buttons
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(buttonsPanel);
        exitButton = new JButton(CLOSE_LABEL);
        exitButton.addActionListener(this);
        exitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonsPanel.add(exitButton);

        // Checks whether the user is logged and adds the items
        if (isConnected) {
            //address
            addressEditTextField = new JTextField();
            addressEditTextField.setColumns(TEXTFIELD_WIDTH);
            addressPanel.add(addressEditTextField);

            //number
            numberEditTextField = new JTextField();
            numberEditTextField.setColumns(TEXTFIELD_WIDTH);
            numberPanel.add(numberEditTextField);

            //status
            statusComboBox.setEditable(true);

            //note
            noteEditTextField = new JTextField();
            noteEditTextField.setColumns(TEXTFIELD_WIDTH);
            notePanel.add(noteEditTextField);

            //buttons
            saveButton = new JButton(SAVE_LABEL);
            saveButton.addActionListener(this);
            buttonsPanel.add(saveButton);

        } else {
            //address
            addressViewLabel = new JLabel();
            addressPanel.add(addressViewLabel);

            //number
            numberViewLabel = new JLabel();
            numberPanel.add(numberViewLabel);

            //status
            statusComboBox.setEditable(false);
            statusComboBox.setEnabled(false);

            //note
            noteViewLabel = new JLabel();
            notePanel.add(noteViewLabel);
        }
    }

//    public Integer getId() {
//        return id;
//    }

    void setId(Integer id) {
        this.id = id;
    }

//    public String getAddress() {
//        return address;
//    }

    void setAddress(String address) {
        if (isConnected) {
            this.addressEditTextField.setText(address);
        } else {
            this.addressViewLabel.setText(address);
        }
    }

    public String getNote() {
        return note;
    }

    void setNote(String address) {
        if (isConnected) {
            this.noteEditTextField.setText(address);
        } else {
            this.noteViewLabel.setText(address);
        }
    }

//    public String getStatus() {
//        return status;
//    }

    void setStatus(String status) {
        if (isConnected) {
            this.statusComboBox.setSelectedItem(status);
        } else {
            this.statusComboBox.setSelectedItem(status);
        }
    }

//    public Integer getNumber() {
//        return Integer.parseInt(numberEditTextField.getText());
//    }

    void setNumber(String number) {
        this.number = number;
        if (isConnected) {
            this.numberEditTextField.setText(number);
        } else {
            this.numberViewLabel.setText(number);
        }
    }

    private boolean validateFrom() {
        if (addressEditTextField.getText().equals("")) {
            return false;
        }
        if (numberEditTextField.getText().equals("")) {
            //TODO check number entry
            //TODO check positive
            return false;
        }
        return true;
    }

    private void sendForm() {
        if (validateFrom()) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exitButton)) {
            dispose();

        } else if (e.getSource().equals(saveButton)) {
            //TODO action save
            if (validateFrom()) {

            }
        }
    }
}

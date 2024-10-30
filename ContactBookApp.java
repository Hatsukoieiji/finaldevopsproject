import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ContactBookApp {

    // Contact class to represent a single contact
    static class Contact {
        String name;
        String phone;
        String email;
        String location;

        Contact(String name, String phone, String email, String location) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.location = location;
        }
    }

    private ArrayList<Contact> contacts = new ArrayList<>();
    private Panel contactsPanel;

    // Method to refresh the contact list
    private void refreshContacts() {
        contactsPanel.removeAll();
        contactsPanel.setBackground(Color.LIGHT_GRAY);

        for (Contact contact : contacts) {
            Panel contactPanel = new Panel(new GridLayout(5, 1));
            contactPanel.setBackground(new Color(224, 255, 255)); // Light blue background
            contactPanel.setPreferredSize(new Dimension(300, 150));

            // Adding padding to simulate a border
            contactPanel.setLayout(new BorderLayout());
            Panel innerPanel = new Panel(new GridLayout(5, 1));
            innerPanel.setBackground(new Color(224, 255, 255)); // Same background color as panel

            Label nameLabel = new Label("Name: " + contact.name);
            Label phoneLabel = new Label("Phone: " + contact.phone);
            Label emailLabel = new Label("Email: " + contact.email);
            Label locationLabel = new Label("Location: " + contact.location);

            Button deleteButton = new Button("Delete");
            deleteButton.setBackground(Color.RED);
            deleteButton.setForeground(Color.WHITE);
            deleteButton.addActionListener(e -> {
                contacts.remove(contact);
                refreshContacts(); // Refresh the list after deletion
            });

            innerPanel.add(nameLabel);
            innerPanel.add(phoneLabel);
            innerPanel.add(emailLabel);
            innerPanel.add(locationLabel);
            innerPanel.add(deleteButton);
            
            contactPanel.add(innerPanel, BorderLayout.CENTER);
            contactPanel.setPreferredSize(new Dimension(300, 150));
            contactsPanel.add(contactPanel);
        }
        contactsPanel.revalidate();
        contactsPanel.repaint();
    }

    // Main class for the AWT application
    public ContactBookApp() {
        // Create frame
        Frame frame = new Frame("Phone Contact Book");
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(240, 248, 255)); // AliceBlue background

        // Panel for adding new contacts
        Panel addContactPanel = new Panel(new GridLayout(5, 2, 10, 10));
        addContactPanel.setBackground(new Color(176, 196, 222)); // Light steel blue background
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();
        Button addButton = new Button("Add Contact");

        addContactPanel.add(nameLabel);
        addContactPanel.add(nameField);
        addContactPanel.add(phoneLabel);
        addContactPanel.add(phoneField);
        addContactPanel.add(emailLabel);
        addContactPanel.add(emailField);
        addContactPanel.add(locationLabel);
        addContactPanel.add(locationField);
        addContactPanel.add(new Label()); // Empty cell for layout
        addContactPanel.add(addButton);

        // Panel to display contacts
        contactsPanel = new Panel();
        contactsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        // Scroll pane for displaying contacts
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(contactsPanel);

        // Add button action
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String location = locationField.getText().trim();
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !location.isEmpty()) {
                contacts.add(new Contact(name, phone, email, location));
                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
                locationField.setText("");
                refreshContacts();
            }
        });

        // Window close handler
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Adding components to frame
        frame.add(addContactPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ContactBookApp();
    }
}

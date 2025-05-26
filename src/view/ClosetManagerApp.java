package view;


import model.*;
import model.Color;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClosetManagerApp extends JFrame {
    private final ClosetManager closet = new ClosetManager();
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final DefaultListModel<ClothingItem> closetListModel = new DefaultListModel<>();
    private final DefaultListModel<Outfit> outfitListModel = new DefaultListModel<>();

    public ClosetManagerApp() {
        setTitle("Closet Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // Screens
        mainPanel.add(createHomeScreen(), "HOME");
        mainPanel.add(createViewClosetScreen(), "VIEW_CLOSET");
        mainPanel.add(createViewItemsScreen(), "VIEW_ITEMS");
        mainPanel.add(createViewOutfitsScreen(), "VIEW_OUTFITS");
        mainPanel.add(createAddItemScreen(), "ADD_ITEM");
        mainPanel.add(createAddOutfitScreen(), "ADD_OUTFIT");

        add(mainPanel);
        cardLayout.show(mainPanel, "HOME");

        setVisible(true);
    }

    // 🏠 Home screen with three buttons
    private JPanel createHomeScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        JButton viewClosetButton = new JButton("View Closet");
        JButton addItemButton = new JButton("Add Clothing Item");
        JButton addOutfitButton = new JButton("Add Outfit");

        viewClosetButton.addActionListener(e -> cardLayout.show(mainPanel, "VIEW_CLOSET"));
        addItemButton.addActionListener(e -> cardLayout.show(mainPanel, "ADD_ITEM"));
        addOutfitButton.addActionListener(e -> cardLayout.show(mainPanel, "ADD_OUTFIT"));

        panel.add(viewClosetButton);
        panel.add(addItemButton);
        panel.add(addOutfitButton);

        return panel;
    }

    // 👕 View Closet: has buttons for viewing items or outfits
    private JPanel createViewClosetScreen() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("View Closet", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel buttons = new JPanel();
        JButton viewItems = new JButton("View Items");
        JButton viewOutfits = new JButton("View Outfits");
        JButton back = new JButton("Back");

        viewItems.addActionListener(e -> cardLayout.show(mainPanel, "VIEW_ITEMS"));
        viewOutfits.addActionListener(e -> cardLayout.show(mainPanel, "VIEW_OUTFITS"));
        back.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        buttons.add(viewItems);
        buttons.add(viewOutfits);
        buttons.add(back);

        panel.add(label, BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.CENTER);

        return panel;
    }

    // 📋 View Items
    private JPanel createViewItemsScreen() {
        JPanel panel = new JPanel(new BorderLayout());

        JList<ClothingItem> list = new JList<>(closetListModel);
        list.setCellRenderer(new ClothingItemRenderer());
        list.setFixedCellHeight(100); // Ensure enough room for image

        JScrollPane scroll = new JScrollPane(list);

        JButton back = new JButton("Back");
        back.addActionListener(e -> cardLayout.show(mainPanel, "VIEW_CLOSET"));

        panel.add(new JLabel("Your Clothing Items", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        return panel;
    }

    // 👗 View Outfits
    private JPanel createViewOutfitsScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        JList<Outfit> list = new JList<>(outfitListModel);
        JScrollPane scroll = new JScrollPane(list);

        JButton back = new JButton("Back");
        back.addActionListener(e -> cardLayout.show(mainPanel, "VIEW_CLOSET"));

        panel.add(new JLabel("Your Outfits", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        return panel;
    }

    // ➕ Add Item
    private JPanel createAddItemScreen() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField brandField = new JTextField();
        JComboBox<Season> seasonBox = new JComboBox<>(Season.values());
        JComboBox<Category> categoryBox = new JComboBox<>(Category.values());
        JComboBox<Color> colorBox = new JComboBox<>(Color.values());

        JLabel imageLabel = new JLabel("No image selected", SwingConstants.CENTER);
        JButton selectImageButton = new JButton("Choose Image");
        String[] imagePath = {null}; // use array to modify inside lambda

        selectImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                imagePath[0] = fileChooser.getSelectedFile().getAbsolutePath();
                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath[0])
                        .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                imageLabel.setIcon(icon);
                imageLabel.setText("");
            }
        });

        JButton addButton = new JButton("Add Item");
        JButton backButton = new JButton("Back");

        panel.add(new JLabel("Brand:"));
        panel.add(brandField);
        panel.add(new JLabel("Season:"));
        panel.add(seasonBox);
        panel.add(new JLabel("Category:"));
        panel.add(categoryBox);
        panel.add(new JLabel("Color:"));
        panel.add(colorBox);
        panel.add(selectImageButton);
        panel.add(imageLabel);
        panel.add(new JLabel());
        panel.add(addButton);
        panel.add(new JLabel());
        panel.add(backButton);

        addButton.addActionListener(e -> {
            String brand = brandField.getText().trim();
            if (brand.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Brand cannot be empty");
                return;
            }
            if (imagePath[0] == null) {
                JOptionPane.showMessageDialog(this, "Please select an image.");
                return;
            }

            ClothingItem item = new ClothingItem(
                    imagePath[0],
                    (Season) seasonBox.getSelectedItem(),
                    (Category) categoryBox.getSelectedItem(),
                    (Color) colorBox.getSelectedItem(),
                    brand
            );

            closet.addItem(imagePath[0],
                    (Season) seasonBox.getSelectedItem(),
                    (Category) categoryBox.getSelectedItem(),
                    (Color) colorBox.getSelectedItem(),
                    brand);
            closetListModel.addElement(item);
            JOptionPane.showMessageDialog(this, "Item added.");

            // Reset fields
            brandField.setText("");
            imageLabel.setIcon(null);
            imageLabel.setText("No image selected");
            imagePath[0] = null;
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        return panel;
    }

    // 🎽 Add Outfit
    private JPanel createAddOutfitScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout(5, 5));
        JPanel bottom = new JPanel();

        JTextField nameField = new JTextField();
        JList<ClothingItem> itemList = new JList<>(closetListModel);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton createButton = new JButton("Create Outfit");
        JButton backButton = new JButton("Back");

        top.add(new JLabel("Outfit Name:"), BorderLayout.WEST);
        top.add(nameField, BorderLayout.CENTER);

        JScrollPane itemScroll = new JScrollPane(itemList);

        createButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter an outfit name.");
                return;
            }

            List<ClothingItem> selectedItems = itemList.getSelectedValuesList();
            if (selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select at least one item.");
                return;
            }

            Outfit outfit = closet.createOutfit(name);
            selectedItems.forEach(item -> closet.addPieceToOutfit(outfit, item));
            outfitListModel.addElement(outfit);

            JOptionPane.showMessageDialog(this, "Outfit created.");
            nameField.setText("");
            itemList.clearSelection();
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        bottom.add(createButton);
        bottom.add(backButton);

        panel.add(top, BorderLayout.NORTH);
        panel.add(itemScroll, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }
    
    private static class ClothingItemRenderer extends JPanel implements ListCellRenderer<ClothingItem> {
        private final JLabel imageLabel = new JLabel();
        private final JLabel textLabel = new JLabel();

        public ClothingItemRenderer() {
            setLayout(new BorderLayout(10, 10));
            add(imageLabel, BorderLayout.WEST);
            add(textLabel, BorderLayout.CENTER);
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ClothingItem> list, ClothingItem item,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            ImageIcon icon = item.getImageIcon();
            if (icon != null) {
                Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                imageLabel.setIcon(null);
            }

            textLabel.setText(item.toString());
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClosetManagerApp::new);
    }
    
}

package Disco;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


class Cliente {
    String nombre;
    int edad;
    int anioNacimiento;
    boolean vip;

    public Cliente(String nombre, int edad, int anioNacimiento, boolean vip) {
        this.nombre = nombre;
        this.edad = edad;
        this.anioNacimiento = anioNacimiento;
        this.vip = vip;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)" + (vip ? " [VIP]" : "");
    }
}

// Colores neón personalizados
class NeonColors {
    static final Color DARK_BG = new Color(20, 20, 30);
    static final Color NEON_PINK = new Color(255, 16, 240);
    static final Color NEON_CYAN = new Color(0, 255, 255);
    static final Color NEON_GREEN = new Color(57, 255, 20);
    static final Color NEON_PURPLE = new Color(138, 43, 226);
    static final Color NEON_ORANGE = new Color(255, 165, 0);
    static final Color TEXT_COLOR = new Color(240, 240, 255);
}

public class Discoteca extends JFrame {
    private static final int CAPACITY_GENERAL = 50;
    private static final int CAPACITY_VIP = 10;
    private static final int COVER_PRICE = 150_000;
    private static final int MIN_CONSUMO = 100_000;

    private Listaenlazada<Cliente> general = new Listaenlazada<>();
    private Listaenlazada<Cliente> vipZone = new Listaenlazada<>();

    private JTextArea generalArea;
    private JTextArea vipArea;
    private JLabel availGeneralLabel;
    private JLabel availVIPLabel;

    public Discoteca() {
        super("🎉 DISCOTECA XYZ 🎉");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(NeonColors.DARK_BG);

        // Encabezado fijo con diseño neón
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Panel con pestañas
        JTabbedPane tabs = createTabbedPane();
        add(tabs, BorderLayout.CENTER);

        // Pie de página
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(NeonColors.DARK_BG);
        panel.setBorder(new LineBorder(NeonColors.NEON_PINK, 3));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(NeonColors.NEON_PINK, 3),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel title = new JLabel("🎉 DISCOTECA XYZ 🎉");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(NeonColors.NEON_PINK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contact = new JLabel("Contacto: 📞 123-456 | Email: contacto@discotecaxyz.com");
        contact.setFont(new Font("Arial", Font.PLAIN, 12));
        contact.setForeground(NeonColors.NEON_CYAN);
        contact.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel socials = new JLabel("🌐 Síguenos: @discotecaxyz | 📘 facebook.com/discotecaxyz | 📷 instagram.com/discotecaxyz");
        socials.setFont(new Font("Arial", Font.PLAIN, 12));
        socials.setForeground(NeonColors.NEON_GREEN);
        socials.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(contact);
        panel.add(socials);

        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(NeonColors.DARK_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(NeonColors.NEON_CYAN, 3),
            new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel thanks = new JLabel("✨ Gracias por visitar Discoteca XYZ ✨");
        thanks.setFont(new Font("Arial", Font.BOLD, 14));
        thanks.setForeground(NeonColors.NEON_ORANGE);
        thanks.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel links = new JLabel("Links rápidos: Inicio | Reservas | Menú | Contacto | Galería");
        links.setFont(new Font("Arial", Font.PLAIN, 11));
        links.setForeground(NeonColors.NEON_GREEN);
        links.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel copyright = new JLabel("© 2026 Discoteca XYZ - Todos los derechos reservados");
        copyright.setFont(new Font("Arial", Font.PLAIN, 10));
        copyright.setForeground(NeonColors.NEON_CYAN);
        copyright.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(thanks);
        panel.add(links);
        panel.add(copyright);

        return panel;
    }

    private JTabbedPane createTabbedPane() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBackground(NeonColors.DARK_BG);
        tabs.setForeground(NeonColors.NEON_PINK);
        tabs.setFont(new Font("Arial", Font.BOLD, 13));
        
        // Pestaña Registrar
        tabs.addTab("📝 Registrar Entrada", createRegistroPanel());
        
        // Pestaña Asistentes
        tabs.addTab("👥 Asistentes", createAsistentesPanel());
        
        // Pestaña Disponibilidad
        tabs.addTab("📊 Disponibilidad", createDisponibilidadPanel());
        
        // Pestaña Menú
        tabs.addTab("🍹 Menú de Bebidas", createMenuPanel());

        return tabs;
    }

    private JPanel createRegistroPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(NeonColors.DARK_BG);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 13);

        // Título
        JLabel titleLabel = new JLabel("VERIFICACIÓN DE IDENTIDAD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(NeonColors.NEON_PINK);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        panel.add(titleLabel, c);

        // Nombre
        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(NeonColors.NEON_CYAN);
        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        panel.add(nameLabel, c);
        
        JTextField nameField = new JTextField(25);
        styleTextField(nameField);
        c.gridx = 1; c.gridy = 1;
        panel.add(nameField, c);

        // Año de nacimiento
        JLabel yearLabel = new JLabel("Año de nacimiento:");
        yearLabel.setFont(labelFont);
        yearLabel.setForeground(NeonColors.NEON_GREEN);
        c.gridx = 0; c.gridy = 2;
        panel.add(yearLabel, c);
        
        JTextField yearField = new JTextField(10);
        styleTextField(yearField);
        c.gridx = 1; c.gridy = 2; c.anchor = GridBagConstraints.WEST;
        panel.add(yearField, c);

        // Zona VIP
        JLabel vipLabel = new JLabel("Zona VIP (Premium):");
        vipLabel.setFont(labelFont);
        vipLabel.setForeground(NeonColors.NEON_ORANGE);
        c.gridx = 0; c.gridy = 3; c.anchor = GridBagConstraints.EAST;
        panel.add(vipLabel, c);
        
        JCheckBox vipCheck = new JCheckBox("Sí, quiero acceso VIP");
        vipCheck.setBackground(NeonColors.DARK_BG);
        vipCheck.setForeground(NeonColors.NEON_ORANGE);
        vipCheck.setFont(labelFont);
        vipCheck.setFocusPainted(false);
        c.gridx = 1; c.gridy = 3; c.anchor = GridBagConstraints.WEST;
        panel.add(vipCheck, c);

        // Artículos
        JLabel itemsLabel = new JLabel("Artículos que trae:");
        itemsLabel.setFont(labelFont);
        itemsLabel.setForeground(NeonColors.NEON_CYAN);
        c.gridx = 0; c.gridy = 4; c.anchor = GridBagConstraints.EAST;
        panel.add(itemsLabel, c);
        
        JTextField itemsField = new JTextField(25);
        styleTextField(itemsField);
        c.gridx = 1; c.gridy = 4; c.anchor = GridBagConstraints.WEST;
        panel.add(itemsField, c);

        // Botón ingresar
        JButton submitBtn = createNeonButton("✅ INGRESAR", NeonColors.NEON_GREEN);
        c.gridx = 0; c.gridy = 5; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        c.ipady = 15;
        panel.add(submitBtn, c);

        // Info de precios
        JLabel priceInfo = new JLabel("<html><center>💰 PRECIOS | Cover General: $150.000 (consumo mínimo $100.000) | VIP: $300.000 (sin consumo mínimo)</center></html>");
        priceInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        priceInfo.setForeground(NeonColors.NEON_PURPLE);
        c.gridx = 0; c.gridy = 6; c.gridwidth = 2;
        panel.add(priceInfo, c);

        submitBtn.addActionListener(e -> register(nameField.getText(), yearField.getText(), vipCheck.isSelected(), itemsField.getText()));

        return panel;
    }

    private JPanel createAsistentesPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBackground(NeonColors.DARK_BG);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel General
        JPanel genPanel = new JPanel(new BorderLayout());
        genPanel.setBackground(NeonColors.DARK_BG);
        JLabel genTitle = new JLabel("👥 ZONA GENERAL");
        genTitle.setFont(new Font("Arial", Font.BOLD, 14));
        genTitle.setForeground(NeonColors.NEON_CYAN);
        genTitle.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        generalArea = new JTextArea();
        styleTextArea(generalArea);
        JScrollPane genScroll = new JScrollPane(generalArea);
        genScroll.setBorder(new LineBorder(NeonColors.NEON_CYAN, 2));
        
        genPanel.add(genTitle, BorderLayout.NORTH);
        genPanel.add(genScroll, BorderLayout.CENTER);

        // Panel VIP
        JPanel vipPanel = new JPanel(new BorderLayout());
        vipPanel.setBackground(NeonColors.DARK_BG);
        JLabel vipTitle = new JLabel("⭐ ZONA VIP");
        vipTitle.setFont(new Font("Arial", Font.BOLD, 14));
        vipTitle.setForeground(NeonColors.NEON_ORANGE);
        vipTitle.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        vipArea = new JTextArea();
        styleTextArea(vipArea);
        JScrollPane vipScroll = new JScrollPane(vipArea);
        vipScroll.setBorder(new LineBorder(NeonColors.NEON_ORANGE, 2));
        
        vipPanel.add(vipTitle, BorderLayout.NORTH);
        vipPanel.add(vipScroll, BorderLayout.CENTER);

        panel.add(genPanel);
        panel.add(vipPanel);

        updateListAreas();
        return panel;
    }

    private JPanel createDisponibilidadPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(NeonColors.DARK_BG);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(15, 15, 15, 15);

        // Disponibilidad General
        availGeneralLabel = new JLabel();
        availGeneralLabel.setFont(new Font("Arial", Font.BOLD, 18));
        availGeneralLabel.setForeground(NeonColors.NEON_CYAN);
        c.gridx = 0; c.gridy = 0;
        panel.add(availGeneralLabel, c);

        // Disponibilidad VIP
        availVIPLabel = new JLabel();
        availVIPLabel.setFont(new Font("Arial", Font.BOLD, 18));
        availVIPLabel.setForeground(NeonColors.NEON_ORANGE);
        c.gridx = 0; c.gridy = 1;
        panel.add(availVIPLabel, c);

        // Botón menú
        JButton menuBtn = createNeonButton("🍹 VER MENÚ DE BEBIDAS", NeonColors.NEON_PINK);
        c.gridx = 0; c.gridy = 2;
        c.ipady = 15;
        panel.add(menuBtn, c);

        menuBtn.addActionListener(e -> showMenu());

        updateAvailabilityLabels();
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(NeonColors.DARK_BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel menuTitle = new JLabel("🍹 MENÚ DE BEBIDAS PREMIUM 🍹");
        menuTitle.setFont(new Font("Arial", Font.BOLD, 20));
        menuTitle.setForeground(NeonColors.NEON_PINK);
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(menuTitle);
        panel.add(Box.createVerticalStrut(15));

        String[] drinks = {
            "🍺 Cerveza Premium - $20.000",
            "🥃 Refresco - $8.000",
            "🍸 Martini - $45.000",
            "🍹 Piña Colada - $50.000",
            "🍸 Mojito - $40.000",
            "🍷 Vino Tinto - $35.000",
            "🥂 Champagne - $80.000",
            "💧 Agua - $5.000",
            "☕ Café - $10.000"
        };

        for (String drink : drinks) {
            JLabel drinkLabel = new JLabel(drink);
            drinkLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            drinkLabel.setForeground(NeonColors.NEON_GREEN);
            drinkLabel.setBorder(new EmptyBorder(8, 20, 8, 20));
            drinkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(drinkLabel);
        }

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JButton createNeonButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(NeonColors.DARK_BG);
        button.setBackground(color);
        button.setBorder(BorderFactory.createLineBorder(color, 2));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    private void styleTextField(JTextField field) {
        field.setBackground(new Color(40, 40, 50));
        field.setForeground(NeonColors.TEXT_COLOR);
        field.setCaretColor(NeonColors.NEON_PINK);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBorder(new LineBorder(NeonColors.NEON_CYAN, 2));
    }

    private void styleTextArea(JTextArea area) {
        area.setBackground(new Color(40, 40, 50));
        area.setForeground(NeonColors.NEON_GREEN);
        area.setFont(new Font("Courier New", Font.PLAIN, 12));
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }

    private void register(String nombre, String yearStr, boolean esVip, String items) {
        if (nombre.trim().isEmpty()) {
            showErrorDialog("Ingresa tu nombre");
            return;
        }
        int anio;
        try {
            anio = Integer.parseInt(yearStr);
        } catch (NumberFormatException ex) {
            showErrorDialog("Año inválido");
            return;
        }
        int edad = 2026 - anio;
        if (edad < 18) {
            showErrorDialog("🚫 Nos reservamos el derecho de admisión.\n❌ Menores de edad no entran.");
            return;
        }
        if (esVip) {
            if (vipZone.size() >= CAPACITY_VIP) {
                showErrorDialog("⭐ Zona VIP llena.\n❌ No se permite ingreso.");
                return;
            }
        } else {
            if (general.size() >= CAPACITY_GENERAL) {
                showErrorDialog("👥 Capacidad general llena.\n❌ No se permite ingreso.");
                return;
            }
        }
        if (tieneProhibido(items.toUpperCase())) {
            showErrorDialog("🚫 Nos reservamos el derecho de admisión.\n❌ Ropa o artículos prohibidos no permitidos:\nCHOMPAS, SUDADERAS, PANTALONETAS, BERMUDAS,\nCHANCLAS, VISERAS, CAMISETAS SIN MANGAS,\nALUCINÓGENOS, ARMAS, OBJETOS CORTANTES");
            return;
        }
        Cliente c = new Cliente(nombre, edad, anio, esVip);
        if (esVip) vipZone.addLast(c);
        else general.addLast(c);
        
        String zone = esVip ? "⭐ ZONA VIP" : "👥 ZONA GENERAL";
        String extra = esVip ? "" : "\n💰 Consumo mínimo: $" + MIN_CONSUMO;
        int price = esVip ? 300_000 : COVER_PRICE;
        showSuccessDialog("✅ INGRESO AUTORIZADO\n\n" + zone + "\n\n💰 Cover: $" + price + extra + 
                         "\n\n🎉 ¡Bienvenido a Discoteca XYZ!");
        updateAvailabilityLabels();
        updateListAreas();
    }


    
    private void showErrorDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg, "❌ ACCESO DENEGADO", 
            JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg, "✅ BIENVENIDO", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean tieneProhibido(String items) {
        String[] prohibidos = {"CHOMPAS", "SUDADERAS", "PANTALONETAS", "BERMUDAS",
                "CHANCLAS", "VISERAS", "CAMISETAS SIN MANGAS", "ALUCINÓGENOS",
                "ARMAS", "CORTANTE"};
        for (String p : prohibidos) {
            if (items.contains(p)) return true;
        }
        return false;
    }

    private void updateAvailabilityLabels() {
        int genAvail = CAPACITY_GENERAL - general.size();
        int vipAvail = CAPACITY_VIP - vipZone.size();
        availGeneralLabel.setText("👥 ZONA GENERAL: " + genAvail + " cupos disponibles de " + CAPACITY_GENERAL);
        availVIPLabel.setText("⭐ ZONA VIP: " + vipAvail + " cupos disponibles de " + CAPACITY_VIP);
    }

    private void updateListAreas() {
        StringBuilder genText = new StringBuilder();
        if (general.size() == 0) {
            genText.append("[Sin asistentes aún]");
        } else {
            general.forEach(c -> genText.append("👤 ").append(c).append("\n"));
        }
        generalArea.setText(genText.toString());
        
        StringBuilder vipText = new StringBuilder();
        if (vipZone.size() == 0) {
            vipText.append("[Sin asistentes aún]");
        } else {
            vipZone.forEach(c -> vipText.append("⭐ ").append(c).append("\n"));
        }
        vipArea.setText(vipText.toString());
    }

    private void showMenu() {
        JOptionPane.showMessageDialog(this, 
            "Ve a la pestaña '🍹 Menú de Bebidas' para ver la lista completa.\n\n" +
            "💰 Precios aproximados:\n🍺 Cerveza - $20.000\n🍹 Cócteles - $40-50.000\n🍷 Vinos - $35-80.000",
            "🍹 MENÚ", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Discoteca::new);
    }
}

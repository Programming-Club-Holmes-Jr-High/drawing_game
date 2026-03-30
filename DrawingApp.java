import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

class Canvas extends JPanel {
    private BufferedImage image; // This stores our drawing
    private Graphics2D g2;       // The "pen" we use to draw
    private Color currentColor = Color.BLACK;
    private int brushSize = 5;

    public Canvas() {
        setDoubleBuffered(false);
        // Listen for mouse clicks and drags
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                draw(e.getX(), e.getY());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                draw(e.getX(), e.getY());
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            // Create a blank white canvas if none exists
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    private void draw(int x, int y) {
        if (g2 != null) {
            g2.setPaint(currentColor);
            g2.fillOval(x - brushSize/2, y - brushSize/2, brushSize, brushSize);
            repaint(); // Refresh the screen
        }
    }

    public void clear() {
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        repaint();
    }

    public void setTool(Color color) { this.currentColor = color; }

    public void uploadImage(File file) {
        try {
            BufferedImage uploaded = ImageIO.read(file);
            g2.drawImage(uploaded, 0, 0, getWidth(), getHeight(), null);
            repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading image.");

          public class DrawingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Draw & Upload");
        Canvas canvas = new Canvas();

        // Toolbar setup
        JPanel toolbar = new JPanel();
        JButton brushBtn = new JButton("Brush");
        JButton eraserBtn = new JButton("Eraser");
        JButton clearBtn = new JButton("Clear");
        JButton uploadBtn = new JButton("Upload Image");

        // Button Actions
        brushBtn.addActionListener(e -> canvas.setTool(Color.BLACK));
        eraserBtn.addActionListener(e -> canvas.setTool(Color.WHITE));
        clearBtn.addActionListener(e -> canvas.clear());
        
        uploadBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                canvas.uploadImage(chooser.getSelectedFile());
            }
        });

        toolbar.add(brushBtn);
        toolbar.add(eraserBtn);
        toolbar.add(uploadBtn);
        toolbar.add(clearBtn);

        // Layout
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_CLOSE);
        frame.setVisible(true);
    }
}

        }
    }
}

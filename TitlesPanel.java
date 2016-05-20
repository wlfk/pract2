/*
 * Decompiled with CFR 0_114.
 */
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TitlesPanel
extends JPanel
implements ActionListener {
    private Graphics2D g2d;
    private Timer animation;
    private boolean is_done = true;
    private int start_angle = 0;
    private int shape;

    public TitlesPanel(int n) {
        this.shape = n;
        this.animation = new Timer(50, this);
        this.animation.setInitialDelay(50);
        this.animation.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (this.is_done) {
            this.repaint();
        }
    }

    private void doDrawing(Graphics graphics) {
        this.is_done = false;
        this.g2d = (Graphics2D)graphics;
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension dimension = this.getSize();
        Insets insets = this.getInsets();
        int n = dimension.width - insets.left - insets.right;
        int n2 = dimension.height - insets.top - insets.bottom;
        ShapeFactory shapeFactory = new ShapeFactory(this.shape);
        this.g2d.setStroke(shapeFactory.stroke);
        this.g2d.setPaint(shapeFactory.paint);
        double d = this.start_angle++;
        if (this.start_angle > 360) {
            this.start_angle = 0;
        }
        double d2 = 90.0 / ((double)n / ((double)shapeFactory.width * 1.5));
        int n3 = shapeFactory.height;
        while (n3 < n2) {
            int n4 = shapeFactory.width;
            while (n4 < n) {
                d = d > 360.0 ? 0.0 : d + d2;
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.translate(n4, n3);
                affineTransform.rotate(Math.toRadians(d));
                this.g2d.draw(affineTransform.createTransformedShape(shapeFactory.shape));
                n4 = (int)((double)n4 + (double)shapeFactory.width * 1.5);
            }
            n3 = (int)((double)n3 + (double)shapeFactory.height * 1.5);
        }
        this.is_done = true;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.doDrawing(graphics);
    }
}


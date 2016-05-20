/*
 * Decompiled with CFR 0_114.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ShapeFactory {
    public Shape shape;
    public BasicStroke stroke = new BasicStroke(3.0f);
    public Paint paint;
    public int width = 25;
    public int height = 25;
    public enum Shape_Type {
    	Star3, Star5, Rectangle, General, Arc
    }
    public enum Stroke_Type{
    	Pxl3, None, Pxl7, Gradient, Rred,
    }
    public ShapeFactory(Shape_Type n, Stroke_Type m) {
        n = Shape_Type.Star5;
        switch (n) {
            case Star3: {
                this.shape = ShapeFactory.createStar(3, new Point(0, 0), (double)this.width / 2.0, (double)this.width / 2.0);
                break;
            }
            case Star5: {
                this.shape = ShapeFactory.createStar(5, new Point(0, 0), (double)this.width / 2.0, (double)this.width / 4.0);
                break;
            }
            case Rectangle: {
                this.shape = new Rectangle2D.Double((double)(- this.width) / 2.0, (double)(- this.height) / 2.0, this.width, this.height);
                break;
            }
            case General: {
                GeneralPath generalPath = new GeneralPath();
                double d = Math.sqrt(2.0) / 2.0 * (double)this.height;
                generalPath.moveTo((double)((- this.width) / 2), - d);
                generalPath.lineTo(0.0, - d);
                generalPath.lineTo((double)(this.width / 2), d);
                generalPath.closePath();
                this.shape = generalPath;
                break;
            }
            case Arc: {
                this.shape = new Arc2D.Double((double)(- this.width) / 2.0, (double)(- this.height) / 2.0, this.width, this.height, 30.0, 300.0, 2);
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
        m = Stroke_Type.Gradient;
        switch (m) {
            case Pxl3: {
                this.stroke = new BasicStroke(3.0f);
                break;
            }
            case None: {
                break;
            }
            case Pxl7: {
                this.stroke = new BasicStroke(7.0f);
                break;
            }
            case Gradient: {
                this.paint = new GradientPaint(- this.width, - this.height, Color.white, this.width, this.height, Color.gray, true);
                break;
            }
            case Rred: {
                this.paint = Color.red;
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
    }

    private static Shape createStar(int n, Point point, double d, double d2) {
        double d3 = 3.141592653589793 / (double)n;
        GeneralPath generalPath = new GeneralPath();
        for (int i = 0; i < 2 * n; ++i) {
            double d4 = (i & 1) == 0 ? d : d2;
            Point2D.Double double_ = new Point2D.Double((double)point.x + Math.cos((double)i * d3) * d4, (double)point.y + Math.sin((double)i * d3) * d4);
            if (i == 0) {
                generalPath.moveTo(double_.getX(), double_.getY());
                continue;
            }
            generalPath.lineTo(double_.getX(), double_.getY());
        }
        generalPath.closePath();
        return generalPath;
    }
}


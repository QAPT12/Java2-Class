package ca.nl.cna.quintin.java2.Assignments.Assignment1.Shapes;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;

public class TestClass {

    @Test
    public void circleTest() throws InvalidShapeParameterException {
        Circle circle = new Circle(10.0);
        Assert.assertEquals("314.16",String.format("%.2f",circle.area()));
        Assert.assertEquals("Circle with radius 10.0 has a area of 314.16", circle.getDescription());
        assertThrows(InvalidShapeParameterException.class, () -> {
            Circle wrongCircle = new Circle(-10.0);
        });
    }

    @Test
    public void rectangleTest() throws InvalidShapeParameterException {
        Rectangle rectangle = new Rectangle(5,10);
        Assert.assertEquals(50, rectangle.area(), 0.0);
        Assert.assertEquals("Rectangle with a length 5.0 and width 10.0 has a area of 50.0", rectangle.getDescription());
        assertThrows(InvalidShapeParameterException.class, () -> {
            Rectangle wrongRect = new Rectangle(-10.0, -9.0);
        });
    }
}

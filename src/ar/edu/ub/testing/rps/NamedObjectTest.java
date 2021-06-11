package ar.edu.ub.testing.rps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class NamedObjectTest {

    @Test
    public void testNameObjectWithValidName() {
        NamedObject namedObject = new NamedObject("paper");
        assertEquals("paper", namedObject.name());
        
    }

    @Test
    public void testNameObjectWithNullName() {
        NamedObject namedObject = new NamedObject(null);
        assertEquals("Unnamed", namedObject.name());
        
    }

    @Test
    public void testNameObjectWithEmptyString() {
        NamedObject namedObject = new NamedObject("");
        assertEquals("Unnamed", namedObject.name());
        
    }
    
}

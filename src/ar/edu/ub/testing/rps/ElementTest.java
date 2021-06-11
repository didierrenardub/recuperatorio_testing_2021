package ar.edu.ub.testing.rps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class ElementTest {

    @Test
    public void testMakeStrongAgainstItselfFail() {
        Element element = new Element("scissor");
        assertFalse(element.makeStrongAgainst(element));
    }

    @Test
    public void testMakeStrongAgainstSuccess() {
        Element element = new Element("rock");
        Element element2 = new Element("paper");
        assertTrue(element.makeStrongAgainst(element2));
            
    }

    @Test
    public void testMakeStrongAgainstSameElementTwiceFail() {
        Element element = new Element("rock");
        Element element2 = new Element("paper");
        assertTrue(element.makeStrongAgainst(element2));
        assertFalse(element.makeStrongAgainst(element2));
            
    }

    @Test
    public void testMakeStrongAgainstElementIsWeakAgainstOtherFail() {
        Element element = new Element("rock");
        Element element2 = new Element("paper");
        assertTrue(element2.makeStrongAgainst(element));
        assertFalse(element.makeStrongAgainst(element2));
            
    }

    @Test
    public void testIsStrongAgainst() {
        Element element = new Element("scissors");
        Element element2 = new Element("rock");
        Element element3 = new Element("paper");
        element2.makeStrongAgainst(element);
        assertTrue(element2.isStrongAgainst(element));
        assertFalse(element2.isStrongAgainst(element3));
    }
    
}

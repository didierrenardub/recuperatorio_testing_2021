package ar.edu.ub.testing.rps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class RoundTest {

    @Test
    public void testGetPlayerByName() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Round round = new Round(player1, player2, new ArrayList<Element>());
        assertEquals(player1, round.player("Sebastian"));
        assertEquals(player2, round.player("Luis"));
    }

    @Test
    public void testGetPlayerBynameNotFound() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Round round = new Round(player1, player2, new ArrayList<Element>());
        assertNull(round.player("Marcela"));
        
    }

    @Test
    public void testPlayedWithValidPlayerAndElement() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        assertTrue(round.play("Sebastian", "paper"));
        
    }

    @Test
    public void testPlayedWithInvalidPlayer() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        assertFalse(round.play("Marcela", "paper"));
        
    }

    @Test
    public void testPlayedWithInvalidElement() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        assertFalse(round.play("Sebastian", "cellphone"));
        
    }

    @Test
    public void testPlayFromWithValidPlayer() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        round.play("Luis", "paper");
        assertEquals(paper, round.playFrom("Luis"));
        
    }

    @Test
    public void testPlayFromWithInvalidPlayer() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        round.play("Luis", "paper");
        assertNull(round.playFrom("Marcela"));
        
    }

    @Test
    public void testWinner() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        paper.makeStrongAgainst(rock);
        rock.makeStrongAgainst(scissors);
        scissors.makeStrongAgainst(paper);
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        round.play("Luis", "paper");
        round.play("Sebastian", "rock");
        assertEquals(player2, round.winner());
        
    }

    @Test
    public void testWinnerWithOnePlay() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        paper.makeStrongAgainst(rock);
        rock.makeStrongAgainst(scissors);
        scissors.makeStrongAgainst(paper);
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        round.play("Luis", "paper");
        assertNull(round.winner());
        
    }

    @Test
    public void testLoser() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        paper.makeStrongAgainst(rock);
        rock.makeStrongAgainst(scissors);
        scissors.makeStrongAgainst(paper);
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        round.play("Luis", "paper");
        round.play("Sebastian", "rock");
        assertEquals(player1, round.loser());
        
    }

    @Test
    public void testLoserWithOnePlay() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        paper.makeStrongAgainst(rock);
        rock.makeStrongAgainst(scissors);
        scissors.makeStrongAgainst(paper);
        ArrayList<Element>elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        round.play("Luis", "paper");
        assertNull(round.loser());
        
    }

    @Test
    public void testCreateRoundWithRepeatElement() {
        NamedObject player1 = new NamedObject("Sebastian");
        NamedObject player2 = new NamedObject("Luis");
        Element paper = new Element("paper");
        Element rock = new Element("rock");
        Element scissors = new Element("scissors");
        ArrayList<Element> elements = new ArrayList<Element>();
        elements.add(paper);
        elements.add(paper);
        elements.add(rock);
        elements.add(scissors);
        Round round = new Round(player1, player2, elements);
        ArrayList<Element> expectedElements = new ArrayList<Element>();
        expectedElements.add(paper);
        expectedElements.add(rock);
        expectedElements.add(scissors);
        assertEquals(expectedElements, round.elements());
        
        
    }



    
}

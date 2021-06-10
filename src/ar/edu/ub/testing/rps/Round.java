package ar.edu.ub.testing.rps;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;


/** A class that wraps a round of Rock, Paper, Scissors (or whatever the elements in play are) between two players. */
public class Round
{
    /** Initialize the round with the given players and playable elements.
     *
     * Args:
     *    playerA: A player in the form of a NamedObject (players do not have any other property).
     *    playerB: Another player that will play against the first one. Cannot be the same as playerA.
     *    availableElements: A list of Elements that are available for the players to pick while playing.
     */
    public Round(NamedObject playerA, NamedObject playerB, ArrayList<Element> availableElements)
    {
        this.plays = new HashMap<String, Element>();
        this.elements = new HashMap<String, Element>();

        if (playerA != null && playerB != null && playerB != playerA && !playerA.name().equals(playerB.name()))
        {
            this.playerA = playerA;
            this.playerB = playerB;
        }
        
        if (availableElements != null)
        {
            for (Element element : availableElements)
            {
                if (element != null && !this.elements.containsKey(element.name()) && !this.elements.values().contains(elements))
                {
                    this.elements.put(element.name(), element);
                }
            }
        }
    }

    /** Getter for the players that play against each other on the current Round.
     *  
     * Args:
     *   byName: Name of the player to get its associated object.
     *
     * Returns:
     *    NamedObject: A reference to the player being queried or null if not found.
     */
    public NamedObject player(String byName)
    {
        if (this.playerA.name().equals(byName))
        {
            return this.playerA;
        }
        else if (this.playerB.name().equals(byName))
        {
            return this.playerB;
        }
        return null;
    }

    /** The entire list of players that are part of the current Round.
     *   
     * Returns:
     *    ArrayList<NamedObject>: A list of references to players playing on this Round. If a player is missing,
     *        null will be present in the list.
     */
    public ArrayList<NamedObject> players()
    {
        ArrayList<NamedObject> players = new ArrayList<NamedObject>();
        players.add(this.playerA);
        players.add(this.playerB);
        return players;
    }

    /** Retrieve all Elements that are available for the players to play during this Round.
     *   
     * Returns:
     *    Collection<Element>: The list of Elements the players can choose from to play on this Round.
     */
    public Collection<Element> elements()
    {
        return this.elements.values();
    }

    /** Analog to player(String byName), allows retrieving a specific Element based on its name.
     *   
     * Args:
     *    byName: The name of the Element that should be looked up.
     *
     * Returns:
     *    Element: The Element reference, if found, or null if not.
     */
    public Element element(String byName)
    {
        return this.elements.get(byName);
    }

    /** Allows a player to perform a play.
     *   
     * Args:
     *    who: A string containing the name of the player that is doing the play.
     *    element: The name of the element the player chooses to go with on this Round.
     *
     * Returns:
     *    boolean: true if the play is done, false if it's not because either the player or the
     *        element are not found.
     */
    public boolean play(String who, String element)
    {
        NamedObject player = this.player(who);
        if (player != null)
        {
            Element played = this.element(element);
            if (played != null)
            {
                this.plays.put(player.name(), played);
                return true;
            }
        }
        return false;
    }

    /** Getter for the play a specific player made.
     *   
     * Args:
     *    player: Name of the player to search the play for.
     *
     * Returns:
     *    Element: The Element the player played in this Round, or null if it hasn't played yet.
     */
    public Element playFrom(String player)
    {
        if (player != null)
        {
            return this.plays.get(player);
        }
        return null;
    }

    /** It returns a reference to the player that won the current Round.
     *   
     * Returns:
     *    NamedObject: A reference to the player that won the current Round. If any of the players
     *       hasn't played yet or if the Round is tied, it'll return null.
     */
    public NamedObject winner()
    {
        NamedObject winner = null;
        if (this.plays.size() == 2)
        {
            Element playerAPlay = this.playFrom(this.playerA.name());
            Element playerBPlay = this.playFrom(this.playerB.name());
            if (playerAPlay.isStrongAgainst(playerBPlay))
            {
                winner = this.playerA;
            }
            else if (playerBPlay.isStrongAgainst(playerAPlay))
            {
                winner = this.playerB;
            }
        }
        return winner;
    }

    /** It returns a reference to the player that lost the current Round.
     *   
     * Returns:
     *    NamedObject: A reference to the player that lost the current Round. If any of the players
     *        hasn't played yet or if the Round is tied, it'll return null.
     */
    public NamedObject loser()
    {
        NamedObject winner = this.winner();
        if (this.playerA != winner)
        {
            return this.playerA;
        }
        else if (this.playerB != winner)
        {
            return this.playerB;
        }
        return null;
    }

    private NamedObject playerA = null;
    private NamedObject playerB = null;
    private HashMap<String, Element> elements = null;
    private HashMap<String, Element> plays = null;
}
package ar.edu.ub.testing.rps;

import java.util.ArrayList;


/** A game element that has a name and is strong against other elements.*/
public class Element extends NamedObject
{
    /** Initialize the Element with the given name.
     *
     * Args:
     *    name: A string containing the name the current Element will be referred to as.
     */
    public Element(String name)
    {
        super(name);
        this.strongAgainst = new ArrayList<Element>();
    }

    /** Adds a rule to determine this Element type beats the supplied one in a face to face match up.
     *   
     * Args:
     *    other: Another Element that will be added to the list of Elements the current one is strong against.
     *
     * Returns:
     *    bool: True if the rule is correctly added, False otherwise. I.e., an Element cannot be strong
     *       against itself, nor against an Element the current one is weak against.
     */
    public boolean makeStrongAgainst(Element other)
    {
        if (other != this && !this.strongAgainst.contains(other) && !other.isStrongAgainst(this))
        {
            this.strongAgainst.add(other);
            return true;
        }
        return false;
    }

    /** Query a relationship between Elements, determining if the supplied is weak against the current one.
     *
     * Args:
     *    other: The Element to check for weakness against current.
     *
     * Returns:
     *    bool: True if the current Element beats the supplied one, False otherwise.
     */
    public boolean isStrongAgainst(Element other)
    {
        return this.strongAgainst.contains(other);
    }

    /** Getter for the complete list of this Element's strengths against others.
     *   
     * Returns:
     *    ArrayList<Element>: A list containing the Elements the current Element is strong against.
     */
    public ArrayList<Element> strongAgainst()
    {
        return this.strongAgainst;
    }

    private ArrayList<Element> strongAgainst;
}

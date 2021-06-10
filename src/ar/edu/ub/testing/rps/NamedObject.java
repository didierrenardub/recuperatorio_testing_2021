package ar.edu.ub.testing.rps;

/**
 * Provides a base class for objects that own a name for themselves.
 */
public class NamedObject
{
    /** Initialize the NamedObject with the given name.
     *
     * Args:
     *    name: A string containing the name the current object will be referred to as. Defaults to 'Unnamed' if
     *       `None` or an empty string are supplied.
     */
    public NamedObject(String name)
    {
        if (name != null && name.length() > 0)
        {
            this.name = name;
        }
        else
        {
            this.name = "Unnamed";
        }
    }
    
    /** Getter for the current object's name.
     *
     * Returns:
     *    str: The string that contains the name of the current object.
     */
    public String name()
    {
        return this.name;
    }

    private String name;
}

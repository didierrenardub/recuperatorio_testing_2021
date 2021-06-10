

class NamedObject():
    """Provides a base class for objects that own a name for themselves."""
    def __init__(self, name: str):
        """Initialize the NamedObject with the given name.
        
        Args:
            name: A string containing the name the current object will be referred to as. Defaults to 'Unnamed' if
                `None` or an empty string are supplied.
        """
        self._name = name if name is not None and len(name) else 'Unnamed'
    
    def name(self) -> str:
        """Getter for the current object's name.
        
        Returns:
            str: The string that contains the name of the current object.
        """
        return self._name

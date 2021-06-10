from named_object import NamedObject
from typing import List


class Element(NamedObject):
    """A game element that has a name and is strong against other elements."""
    def __init__(self, name: str):
        """Initialize the Element with the given name.
        
        Args:
            name: A string containing the name the current Element will be referred to as.
        """
        NamedObject.__init__(self, name)
        self._strong_against = []

    def make_strong_against(self, other: 'Element') -> bool:
        """Adds a rule to determine this Element type beats the supplied one in a face to face match up.
        
        Args:
            other: Another Element that will be added to the list of Elements the current one is strong against.

        Returns:
            bool: True if the rule is correctly added, False otherwise. I.e., an Element cannot be strong
                against itself, nor against an Element the current one is weak against.
        """
        if other is not self and other not in self._strong_against and not other.is_strong_against(self):
            self._strong_against.append(other)
            return True
        return False

    def is_strong_against(self, other: 'Element') -> bool:
        """Query a relationship between Elements, determining if the supplied is weak against the current one.

        Args:
            other: The Element to check for weakness against current.

        Returns:
            bool: True if the current Element beats the supplied one, False otherwise.
        """
        return other in self._strong_against

    def strong_against(self) -> List['Element']:
        """Getter for the complete list of this Element's strengths against others.
        
        Returns:
            List[Element]: A list containing the Elements the current Element is strong against.
        """
        return self._strong_against

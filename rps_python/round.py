from named_object import NamedObject
from element import Element
from typing import List, Optional


class Round():
    """A class that wraps a round of Rock, Paper, Scissors (or whatever the elements in play are) between two players."""
    def __init__(self, player_a: NamedObject, player_b: NamedObject, available_elements: List[Element]):
        """Initialize the round with the given players and playable elements.
        
        Args:
            player_a: A player in the form of a NamedObject (players do not have any other property).
            player_b: Another player that will play against the first one. Cannot be the same as player_a.
            available_elements: A list of Elements that are available for the players to pick while playing.
        """
        self._player_a = None
        self._player_b = None
        if player_a is not None and player_b is not None and player_a != player_b and player_a.name() != player_b.name():
            self._player_a = player_a
            self._player_b = player_b
        self._plays = {}
        self._elements = {}
        if available_elements is not None:
            for element in available_elements:
                if element is not None and element.name() not in self._elements.keys() and element not in self._elements.values():
                    self._elements[element.name()] = element

    def player(self, by_name: str) -> Optional[NamedObject]:
        """Getter for the players that play against each other on the current Round.
        
        Args:
            by_name: Name of the player to get its associated object.
        
        Returns:
            Optional[NamedObject]: A reference to the player being queried or None if not found.
        """
        if self._player_a.name() == by_name:
            return self._player_a
        elif self._player_b.name() == by_name:
            return self._player_b
        return None

    def players(self) -> List[NamedObject]:
        """The entire list of players that are part of the current Round.
        
        Returns:
            List[NamedObject]: A list of references to players playing on this Round. If a player is missing,
                None will be present in the list.
        """
        return [self._player_a, self._player_b]

    def elements(self) -> List[Element]:
        """Retrieve all Elements that are available for the players to play during this Round.
        
        Returns:
            List[Element]: The list of Elements the players can choose from to play on this Round.
        """
        return list(self._elements.values())

    def element(self, by_name: str) -> Optional[Element]:
        """Analog to player(by_name), allows retrieving a specific Element based on its name.
        
        Args:
            by_name: The name of the Element that should be looked up.

        Returns:
            Optional[Element]: The Element reference, if found, or None if not.
        """
        return self._elements.get(by_name, None)

    def play(self, who: str, element: str) -> bool:
        """Allows a player to perform a play.
        
        Args:
            who: A string containing the name of the player that is doing the play.
            element: The name of the element the player chooses to go with on this Round.

        Returns:
            bool: True if the play is done, False if it's not because either the player or the
                element are not found.
        """
        player = self.player(who)
        if player is not None:
            played = self.element(element)
            if played is not None:
                self._plays[player.name()] = played
                return True
        return False

    def play_from(self, player: str) -> Optional[Element]:
        """Getter for the play a specific player made.
        
        Args:
            player: Name of the player to search the play for.

        Returns:
            Optional[Element]: The Element the player played in this Round, or None if it hasn't played yet.
        """
        if player is not None:
            return self._plays.get(player, None)
        return None

    def winner(self) -> Optional[NamedObject]:
        """It returns a reference to the player that won the current Round.
        
        Returns:
            Optional[NamedObject]: A reference to the player that won the current Round. If any of the players
                hasn't played yet or if the Round is tied, it'll return None.
        """
        winner = None
        if len(self._plays) == 2:
            player_a_play = self.play_from(self._player_a.name())
            player_b_play = self.play_from(self._player_b.name())
            if player_a_play.is_strong_against(player_b_play):
                winner = self._player_a
            elif player_b_play.is_strong_against(player_a_play):
                winner = self._player_b
        return winner

    def loser(self) -> Optional[NamedObject]:
        """It returns a reference to the player that lost the current Round.
        
        Returns:
            Optional[NamedObject]: A reference to the player that lost the current Round. If any of the players
                hasn't played yet or if the Round is tied, it'll return None.
        """
        winner = self.winner()
        if self._player_a != winner:
            return self._player_a
        elif self._player_b != winner:
            return self._player_b
        return None

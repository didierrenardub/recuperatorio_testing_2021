from typing import List
from random import choice
from named_object import NamedObject
from element import Element
from round import Round


class Simulation():
    def __init__(self, player_a: NamedObject, player_b: NamedObject, available_elements: List[Element], rounds: int = 3):
        self._rounds: List[Round] = []
        for _ in range(rounds):
            self._rounds.append(Round(player_a, player_b, available_elements))

    def print_rules(self):
        print('Available elements:')
        for element in self._rounds[0].elements():
            print(f'\t{element.name()}: beats ', end='')
            for weak in element.strong_against():
                print(weak.name(), end=' ')
            print()
        print()
        print(f'Match: {self._rounds[0].players()[0].name()} vs {self._rounds[0].players()[1].name()}')

    def run(self):
        self.print_rules()
        score = {}
        for index, round in enumerate(self._rounds):
            for player in round.players():
                if player.name() not in score:
                    score[player.name()] = 0
                round.play(player.name(), choice(round.elements()).name())
            winner = round.winner()
            if winner is not None:
                print(f'{index}: {round.winner().name()} ({round.play_from(round.winner().name()).name()} vs {round.play_from(round.loser().name()).name()})')
                score[winner.name()] = score.get(winner.name(), 0) + 1
            else:
                print(f'{index}: Tie ({round.play_from(round.players()[0].name()).name()} vs {round.play_from(round.players()[1].name()).name()})')
        print(f'Match result: {list(score.keys())[0]} {score[list(score.keys())[0]]} - {score[list(score.keys())[1]]} {list(score.keys())[1]}')


if __name__ == '__main__':
    rock = Element('rock')
    paper = Element('paper')
    scissors = Element('scissors')
    rock.make_strong_against(scissors)
    paper.make_strong_against(rock)
    scissors.make_strong_against(paper)
    Simulation(NamedObject('Alumno rindiendo'), NamedObject('Didier'), [rock, paper, scissors]).run()
    print()

    a = Element('A')
    b = Element('B')
    c = Element('C')
    d = Element('D')
    e = Element('E')
    a.make_strong_against(b)
    a.make_strong_against(c)
    b.make_strong_against(c)
    b.make_strong_against(d)
    c.make_strong_against(d)
    c.make_strong_against(e)
    d.make_strong_against(e)
    d.make_strong_against(a)
    e.make_strong_against(a)
    e.make_strong_against(b)
    Simulation(NamedObject('Alumno rindiendo'), NamedObject('Didier'), [a, b, c, d, e], 5).run()

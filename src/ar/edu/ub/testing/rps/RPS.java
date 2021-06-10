package ar.edu.ub.testing.rps;

import java.util.ArrayList;
import java.util.HashMap;


public class RPS
{
    public RPS(NamedObject playerA, NamedObject playerB, ArrayList<Element> availableElements)
    {
        this(playerA, playerB, availableElements, 3);
    }

    public RPS(NamedObject playerA, NamedObject playerB, ArrayList<Element> availableElements, int rounds)
    {
        this.rounds = new ArrayList<Round>();
        for (int i = 0; i < rounds; i++)
        {
            this.rounds.add(new Round(playerA, playerB, availableElements));
        }
    }

    public static void print(String str)
    {
        System.out.print(str);
    }

    private void printRules()
    {
        this.print("Available elements:");
        for (Element element : this.rounds.get(0).elements())
        {
            print("\t" + element.name() + ": beats ");
            for (Element weak : element.strongAgainst())
            {
                print(weak.name() + " ");
            }
            print("\n");
        }
        print("\n");
        print("Match: " + this.rounds.get(0).players().get(0).name() + " vs " + this.rounds.get(0).players().get(1).name());
    }

    public void run()
    {
        this.printRules();
        HashMap<String, Integer> score = new HashMap<>();
        for (int i = 0; i < this.rounds.size(); i++)
        {
            Round round = this.rounds.get(i);
            for (NamedObject player : round.players())
            {
                if (!score.containsKey(player.name()))
                {
                    score.put(player.name(), 0);
                }
                round.play(player.name(), round.elements().toArray(new Element[round.elements().size()])[(int)(Math.random() * round.elements().size())].name());
            }
            NamedObject winner = round.winner();
            if (winner != null)
            {
                print("" + i + ": " + winner.name() + " (" + round.playFrom(winner.name()).name() + " vs " + round.playFrom(round.loser().name()).name() + ")");
                score.put(winner.name(), score.get(winner.name()) + 1);
            }
            else
            {
                print("" + i + ": Tie (" + round.playFrom(round.players().get(0).name()).name() + " vs " + round.playFrom(round.players().get(1).name()).name() + ")");
            }
        }
        print("Match result: " + score.keySet().toArray()[0] + " " + score.values().toArray()[0] + " - " + score.values().toArray()[1] + " " + score.keySet().toArray()[1]);
    }

    private ArrayList<Round> rounds;

    public static void main(String[] args)
    {
        Element rock = new Element("rock");
        Element paper = new Element("paper");
        Element scissors = new Element("scissors");
        rock.makeStrongAgainst(scissors);
        paper.makeStrongAgainst(rock);
        scissors.makeStrongAgainst(paper);
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(rock);
        elements.add(paper);
        elements.add(scissors);
        new RPS(new NamedObject("Alumno rindiendo"), new NamedObject("Didier"), elements).run();
        RPS.print("\n");
    
        Element a = new Element("A");
        Element b = new Element("B");
        Element c = new Element("C");
        Element d = new Element("D");
        Element e = new Element("E");
        a.makeStrongAgainst(b);
        a.makeStrongAgainst(c);
        b.makeStrongAgainst(c);
        b.makeStrongAgainst(d);
        c.makeStrongAgainst(d);
        c.makeStrongAgainst(e);
        d.makeStrongAgainst(e);
        d.makeStrongAgainst(a);
        e.makeStrongAgainst(a);
        e.makeStrongAgainst(b);
        elements = new ArrayList<>();
        elements.add(a);
        elements.add(b);
        elements.add(c);
        elements.add(d);
        elements.add(e);
        new RPS(new NamedObject("Alumno rindiendo"), new NamedObject("Didier"), elements, 5).run();
    }
}

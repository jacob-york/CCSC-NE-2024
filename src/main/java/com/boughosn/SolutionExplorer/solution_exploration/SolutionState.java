package com.boughosn.SolutionExplorer.solution_exploration;

import java.util.ArrayList;
import java.util.List;

public abstract class SolutionState
{
    protected ProblemData data;

    public SolutionState(ProblemData data)
    {
        this.data = data;
    }
    public ProblemData getProblemData() {return data;}
    public abstract boolean isGoalState();
    public abstract boolean isValidState();
    public abstract List<Object> getMoveList();
    public abstract SolutionState generateNewState(Object move);

    public ArrayList<Move> getPossibleMoves()
    {
        ArrayList<Move> moves = new ArrayList<>();
        List<Object> moveList = getMoveList();

        for (int i = 0; i < moveList.size(); i++)
        {
            final int finalI = i;
            moves.add(new Move()
            {
                @Override
                public SolutionState perform(SolutionState state)
                {
                    return generateNewState(moveList.get(finalI));
                }
            });
        }
        return moves;
    };
}

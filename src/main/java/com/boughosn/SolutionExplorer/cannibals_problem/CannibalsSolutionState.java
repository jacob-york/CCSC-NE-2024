package com.boughosn.SolutionExplorer.cannibals_problem;

import com.boughosn.SolutionExplorer.solution_exploration.SolutionState;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CannibalsSolutionState extends SolutionState
{
    //Number of missionaries and cannibals on left side
    private int numMissionaries;
    private int numCannibals;
    private boolean boatOnLeft;

    public CannibalsSolutionState()
    {
        super(null);
        numMissionaries = 3;
        numCannibals = 3;
        boatOnLeft = true;
    }

    @Override
    public boolean equals(Object cannibalsSolutionState)
    {
        CannibalsSolutionState otherState = (CannibalsSolutionState) cannibalsSolutionState;
        return (this.numMissionaries == otherState.numMissionaries &&
                this.numCannibals == otherState.numCannibals &&
                this.boatOnLeft == otherState.boatOnLeft);
    }

    @Override
    public boolean isGoalState()
    {
        return (numMissionaries == 0 && numCannibals == 0 && boatOnLeft == false);
    }

    @Override
    public boolean isValidState()
    {
        return (
                (numCannibals <= numMissionaries || numMissionaries == 0)
                && (3 - numCannibals <= 3 - numMissionaries || numMissionaries == 3)
                && (numMissionaries >= 0 && numCannibals >= 0)
                && (numMissionaries <= 3 && numCannibals <= 3)
               );
    }

    @Override
    public List<Object> getMoveList()
    {
        return Arrays.asList(new Point(1,0), new Point(0,1),
                new Point(2,0), new Point(0,2), new Point(1,1));
    }

    @Override
    public SolutionState generateNewState(Object move)
    {
        CannibalsSolutionState newSolutionState = new CannibalsSolutionState();
        if (boatOnLeft)
        {
            newSolutionState.numMissionaries = numMissionaries - ((Point)move).x;
            newSolutionState.numCannibals = numCannibals - ((Point)move).y;
            newSolutionState.boatOnLeft = false;
        }
        else
        {
            newSolutionState.numMissionaries = numMissionaries + ((Point)move).x;
            newSolutionState.numCannibals = numCannibals + ((Point)move).y;
            newSolutionState.boatOnLeft = true;
        }
        return newSolutionState;
    }

    public String toString()
    {
        return "(" + numMissionaries + "," + numCannibals + "," + boatOnLeft + ")";
    }
}

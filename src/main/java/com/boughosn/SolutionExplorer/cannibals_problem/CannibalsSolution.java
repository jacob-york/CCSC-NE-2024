package com.boughosn.SolutionExplorer.cannibals_problem;

import com.boughosn.SolutionExplorer.solution_exploration.Solution;
import com.boughosn.SolutionExplorer.solution_exploration.SolutionState;

public class CannibalsSolution extends Solution
{
    public int assessQuality() {return -size();}
    public String render()
    {
        String solStr = "";

        for (SolutionState solutionState : this)
        {
            CannibalsSolutionState cannibalsSolutionState = (CannibalsSolutionState)solutionState;
            solStr += (cannibalsSolutionState.toString() + "\n");
        }
        return solStr;
    }
    public Solution createSolution() { return new CannibalsSolution();}
}

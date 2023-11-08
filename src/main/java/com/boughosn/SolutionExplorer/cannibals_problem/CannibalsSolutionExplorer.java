package com.boughosn.SolutionExplorer.cannibals_problem;

import com.boughosn.SolutionExplorer.solution_exploration.ProblemData;
import com.boughosn.SolutionExplorer.solution_exploration.Solution;
import com.boughosn.SolutionExplorer.solution_exploration.SolutionExplorer;
import com.boughosn.SolutionExplorer.solution_exploration.SolutionState;

public class CannibalsSolutionExplorer extends SolutionExplorer
{

    @Override
    public String getProblemDataPath()
    {
        return "";
    }

    @Override
    public ProblemData createProblemData()
    {
        return new CannibalsProblemData();
    }

    @Override
    public Solution createNewSolution()
    {
        return new CannibalsSolution();
    }

    @Override
    public SolutionState getInitialState(ProblemData problemData)
    {
        return new CannibalsSolutionState();
    }

    public static void main(String[] args)
    {
        CannibalsSolutionExplorer cannibalsSolutionExplorer = new CannibalsSolutionExplorer();
        cannibalsSolutionExplorer.explore();
    }
}

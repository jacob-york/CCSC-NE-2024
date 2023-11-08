package com.boughosn.SolutionExplorer.amazeing_problem;

import com.boughosn.SolutionExplorer.solution_exploration.ProblemData;
import com.boughosn.SolutionExplorer.solution_exploration.Solution;
import com.boughosn.SolutionExplorer.solution_exploration.SolutionExplorer;
import com.boughosn.SolutionExplorer.solution_exploration.SolutionState;

import java.awt.*;

public class AmazeingSolutionExplorer extends SolutionExplorer
{

    @Override
    public String getProblemDataPath()
    {
        return "C:\\Users\\Administrator\\Desktop\\data.txt";
    }

    @Override
    public ProblemData createProblemData()
    {
        return new AmazeingProblemData();
    }

    @Override
    public Solution createNewSolution()
    {
        return new AmazeingSolution();
    }

    @Override
    public SolutionState getInitialState(ProblemData problemData)
    {
        SolutionState initialState = new AmazeingSolutionState(problemData);

        char[][] maze = ((AmazeingProblemData)problemData).getMaze();

        maze_search_loop:
        for (int row = 0; row < maze.length; row++)
        {
            for (int col = 0; col < maze[0].length; col++)
                if (maze[row][col] == '>')
                {
                    Point pos = ((AmazeingSolutionState)initialState).getPosition();
                    pos.x = col;
                    pos.y = row;
                    break maze_search_loop;
                }
        }
        return initialState;
    }

    //only for debugging
    /*protected void setCurrentState(SolutionState state)
    {
        super.setCurrentState(state);
        Point pos = ((AmazeingSolutionState)state).getPosition();
        if (pos.x == 3 && pos.y == 6)
            pos.x = pos.x;
    }*/

    public static void main(String[] args)
    {
        AmazeingSolutionExplorer amazeingSolutionExplorer = new AmazeingSolutionExplorer();
        amazeingSolutionExplorer.explore();
    }
}

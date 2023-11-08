package com.boughosn.SolutionExplorer.amazeing_problem;

import com.boughosn.SolutionExplorer.solution_exploration.Solution;
import com.boughosn.SolutionExplorer.solution_exploration.SolutionState;

import java.awt.*;

public class AmazeingSolution extends Solution
{
    //This override is required so that the getShallowCopy method will work for this child class
    @Override
    public Solution createSolution()
    {
        return new AmazeingSolution();
    }

    @Override
    public int assessQuality() {return -size();}

    @Override
    public String render()
    {
        String mazeStr = "";
        char[][] maze = null;

        if (isEmpty())
            return "No Escape";

        maze = ((AmazeingProblemData)get(0).getProblemData()).getMaze();

        mazeStr += ( (size() - 1) + "\n");
        for (SolutionState solutionState : this)
        {
            Point pos = ((AmazeingSolutionState)solutionState).getPosition();
            if (maze[pos.y][pos.x] == '>' || maze[pos.y][pos.x] == '$')
                continue;
            maze[pos.y][pos.x] = '%';
        }

        for (int row = 0; row < maze.length; row++)
        {
            for (int col = 0; col < maze[0].length; col++)
                mazeStr += maze[row][col] + " ";
            mazeStr += "\n";
        }
        return mazeStr;
    }
}

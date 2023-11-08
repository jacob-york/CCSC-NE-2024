package com.boughosn.SolutionExplorer.amazeing_problem;

import com.boughosn.SolutionExplorer.solution_exploration.ProblemData;

import java.util.Scanner;

public class AmazeingProblemData extends ProblemData
{
    private char[][] maze;

    public AmazeingProblemData()
    {
        maze = null;
    }

    public char[][] getMaze()
    {
        return maze;
    }

    @Override
    public boolean loadProblemInstanceData(Scanner dataStream)
    {
        int rows = 0;
        int cols = 0;
        boolean success = false;

        rows = dataStream.nextInt();
        if (rows != -1)
        {
            cols = dataStream.nextInt();
            dataStream.nextLine();
            maze = new char[rows][cols];

            for (int row = 0; row < rows; row++)
            {
                String rowStr = dataStream.nextLine();
                for (int col = 0; col < cols; col++)
                    maze[row][col] = rowStr.charAt(col);
            }
            success = true;
        }
        return success;
    }
}

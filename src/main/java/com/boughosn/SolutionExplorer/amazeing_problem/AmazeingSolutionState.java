package com.boughosn.SolutionExplorer.amazeing_problem;

import com.boughosn.SolutionExplorer.solution_exploration.ProblemData;
import com.boughosn.SolutionExplorer.solution_exploration.SolutionState;

import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class AmazeingSolutionState extends SolutionState
{
    private Point position;
    private char[][] maze;

    public Point getPosition() {return position;}

    public AmazeingSolutionState(ProblemData problemData)
    {
        super(problemData);
        maze = ((AmazeingProblemData)data).getMaze();
        position = new Point();
    }

    @Override
    public boolean equals(Object amazeingSolutionState)
    {
        Point otherPos = ((AmazeingSolutionState) amazeingSolutionState).position;
        return (this.position.x == otherPos.x && this.position.y == otherPos.y);
    }

    @Override
    public boolean isGoalState()
    {
        return maze[position.y][position.x] == '$';
    }

    @Override
    public boolean isValidState()
    {
        int nRows = maze.length;
        int nCols = maze[0].length;

        return  ((position.y >= 0 && position.y < nRows)
                 && (position.x >= 0 && position.x < nCols)
                 && (maze[position.y][position.x] != '#') );
    }

    @Override
    public List<Object> getMoveList()
    {
        return Arrays.asList(new Point(1,0), new Point(0,1),
                new Point(-1,0), new Point(0,-1));
    }

    public SolutionState generateNewState(Object move)
    {
        AmazeingSolutionState newSolutionState = new AmazeingSolutionState(data);
        newSolutionState.position.x = position.x + ((Point)move).x;
        newSolutionState.position.y = position.y + ((Point)move).y;
        return newSolutionState;
    }

}

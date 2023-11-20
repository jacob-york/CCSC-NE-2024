package solution_exploration;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class SolutionExplorer
{
    public enum ExplorationMode {DEPTH_FIRST, BREADTH_FIRST}
    private ExplorationMode explorationMode;
    private Solution solutionPath;
    private static ArrayList<Solution> foundSolutions;
    private static ArrayList<ArrayList<Solution>> allFoundSolutions;
    private static ArrayList<ProblemData> problems;
    private static ArrayList<Solution> finalSolutions;
    private SolutionState currentState;
    private ProblemData currentProblem;

    public abstract String getProblemDataPath();
    public abstract ProblemData createProblemData();
    public abstract Solution createNewSolution();
    public abstract SolutionState getInitialState(ProblemData problemData);

    //public static

    public SolutionExplorer()
    {
        currentProblem = null;
        finalSolutions = new ArrayList<>();
        allFoundSolutions = new ArrayList<>();
        //We use depth first by default
        explorationMode = ExplorationMode.DEPTH_FIRST;
    }
    private void initialize()
    {
        solutionPath = createNewSolution();
        foundSolutions = new ArrayList<>();
        setCurrentState(getInitialState(currentProblem));
    }

    protected void setCurrentState(SolutionState state)
    {
        currentState = state;
        solutionPath.add(currentState);
    }

    public ExplorationMode getExplorationMode()
    {
        return explorationMode;
    }

    public void setExplorationMode(ExplorationMode explorationMode)
    {
        if (explorationMode != null)
            this.explorationMode = explorationMode;
    }

    public static ArrayList<Solution> getAllSolutions(Solution solution)
    {
        int problemIndex = finalSolutions.indexOf(solution);
        return allFoundSolutions.get(problemIndex);
    }

    private void exploreDepthFirst()
    {
        ArrayList<Move> moves = currentState.getPossibleMoves();

        for (Move move : moves)
        {
            SolutionState nextState = move.perform(currentState);
            //discard invalid solutions and avoid cycles
            if (!nextState.isValidState() || solutionPath.contains(nextState))
                continue;
            //if we reached the goal, then store solution to keep track of it
            if (nextState.isGoalState())
            {
                solutionPath.add(nextState);
                foundSolutions.add(solutionPath.getShallowCopy());
            }
            else
            {
                setCurrentState(nextState);
                exploreDepthFirst();
            }
            //We finished processing this move thoroughly, so remove it
            //from the path, to backtrack and explore other moves
            solutionPath.remove(solutionPath.size() - 1);
            //restore current state
            currentState = solutionPath.get(solutionPath.size() - 1);
        }
    }

    private void exploreBreadthFirst()
    {
        return;
    }

    public void explore()
    {
        loadAllProblemData();

        for (ProblemData problem : problems)
        {
            currentProblem = problem;
            initialize();

            switch (explorationMode)
            {
                case DEPTH_FIRST:
                    exploreDepthFirst();
                    break;
                case BREADTH_FIRST:
                    exploreBreadthFirst();
                    break;
            }

            pickBestSolution();
        }
        System.out.println(renderAllSolutions());

    }

    private void pickBestSolution()
    {
        //If no solution was found for this problem, add a null entry
        if (foundSolutions.isEmpty())
            finalSolutions.add(createNewSolution());
        else
        {
            Solution bestSolution = foundSolutions.get(0);
            for (Solution solution : foundSolutions)
                if (solution.assessQuality() > bestSolution.assessQuality())
                    bestSolution = solution;
            finalSolutions.add(bestSolution);
        }
        allFoundSolutions.add(foundSolutions);
    }

    public void loadAllProblemData()
    {
        problems = new ArrayList<>();
        String dataPath = getProblemDataPath();

        if (dataPath.isEmpty())
        {
            problems.add(createProblemData());
            return;
        }

        try
        {
            FileInputStream fileInputStream = new FileInputStream(getProblemDataPath());
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine())
            {
                ProblemData problemData = createProblemData();
                boolean success = problemData.loadProblemInstanceData(scanner);
                if (success)
                    problems.add(problemData);
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to load problem data!!");
        }
    };

    public String renderAllSolutions()
    {
        String allSolutions = "";

        for (Solution solution : finalSolutions)
            allSolutions += solution.render();

        return allSolutions;
    }


}

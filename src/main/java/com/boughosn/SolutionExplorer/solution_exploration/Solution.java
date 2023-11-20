package solution_exploration;

import java.util.ArrayList;

public class Solution extends ArrayList<SolutionState>
{
    public int assessQuality() {return -1;}
    public String render() {return "";}
    //This needs to be overriden in child classes so that the getShallowCopy method works for that class
    public Solution createSolution() { return new Solution();}

    public Solution getShallowCopy()
    {
        Solution copy = createSolution();
        for (SolutionState state : this)
            copy.add(state);
        return copy;
    }

}

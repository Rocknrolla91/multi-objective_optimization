package MultiObjectiveOptimizaton;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;

/**
 * Created by alifar on 5/21/16.
 */
public class Randon extends AbstractProblem {

    /**
     * Constructs the Rendon problem.
     */
    public Randon() {
        super(2, 2);
    }

    @Override
    public void evaluate(Solution solution) {
        double x = ((RealVariable)solution.getVariable(0)).getValue();
        double y = ((RealVariable)solution.getVariable(1)).getValue();
        double f1 = 1.0 / (Math.pow(x, 2.0) + Math.pow(y, 2.0) + 1.0);
        double f2 = Math.pow(x, 2.0) + 3.0*Math.pow(y, 2.0) + 1.0;

        solution.setObjective(0, f1);
        solution.setObjective(1, f2);
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(2, 2);

        solution.setVariable(0, new RealVariable(-3.0, 3.0));
        solution.setVariable(1, new RealVariable(-3.0, 3.0));

        return solution;
    }

}
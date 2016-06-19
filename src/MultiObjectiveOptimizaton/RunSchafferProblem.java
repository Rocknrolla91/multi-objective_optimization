package MultiObjectiveOptimizaton;

import org.moeaframework.Analyzer;
import org.moeaframework.Executor;
import org.moeaframework.analysis.collector.Accumulator;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;

/**
 * Created by alifar on 5/21/16.
 */
public class RunSchafferProblem {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        NondominatedPopulation result = new Executor()
                .withAlgorithm("eMOEA")
                .withProblemClass(SchafferProblem.class)
                .withMaxEvaluations(10000)
//                .withProperty("ElapsedTime", System.currentTimeMillis()-start)
                .run();
//        for(Solution solution: result){
//            System.out.printf("%.5f => %.5f, %.5f\n",
//                    EncodingUtils.getReal(solution.getVariable(0)),
//                    solution.getObjective(0),
//                    solution.getObjective(1));
//        }
        Analyzer analyzer = new Analyzer()
                .withProblemClass(SchafferProblem.class)
                .includeAllMetrics()
                .showStatisticalSignificance();
        Executor executor = new Executor()
                .withProblemClass(SchafferProblem.class)
                .withMaxEvaluations(10000)
                .withAlgorithm("NSGAII");

        analyzer.add("NSGA",
                result);
        Accumulator accumulator = executor.getInstrumenter().getLastAccumulator();

        new Plot()
                .add(accumulator)
//                .add(analyzer.getAnalysis())
                .show();
//        new Plot()
////                .add("MultiO", result)
//                .add(analyzer)
//                .show();
    }
}

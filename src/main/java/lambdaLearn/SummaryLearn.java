package lambdaLearn;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by zx on 2019/10/28.
 */
public class SummaryLearn {
    public static void main(String[] args) {
        List<Double> list= Arrays.asList(100.0,200.0,300.0);
        DoubleSummaryStatistics doubleSummaryStatistics=list.stream().mapToDouble(n -> n).summaryStatistics();
        System.out.println("max:{}"+doubleSummaryStatistics.getMax());
        System.out.println("min:{}"+doubleSummaryStatistics.getMin());
        System.out.println("average:{}"+doubleSummaryStatistics.getAverage());
        System.out.println("count:{}"+doubleSummaryStatistics.getCount());
        System.out.println("sum:{}"+doubleSummaryStatistics.getSum());
    }
}

package backend.academy;

import backend.academy.benchmark.DirectBenchmark;
import backend.academy.benchmark.LambdaMetafactoryBenchmark;
import backend.academy.benchmark.MethodHandleBenchmark;
import backend.academy.benchmark.ReflectionBenchmark;
import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@UtilityClass
public class Main {

    private static final int FORKS_COUNT = 3;
    private static final int WARMUP_FORKS_COUNT = 1;
    private static final int WARMUP_ITERATION_COUNT = 5;
    private static final int WARMUP_TIME = 10;
    private static final int MEASUREMENT_ITERATIONS_COUNT = 5;
    private static final int MEASUREMENT_TIME_COUNT = 10;


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(DirectBenchmark.class.getSimpleName())
            .include(ReflectionBenchmark.class.getSimpleName())
            .include(MethodHandleBenchmark.class.getSimpleName())
            .include(LambdaMetafactoryBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(FORKS_COUNT)
            .warmupForks(WARMUP_FORKS_COUNT)
            .warmupIterations(WARMUP_ITERATION_COUNT)
            .warmupTime(TimeValue.seconds(WARMUP_TIME))
            .measurementIterations(MEASUREMENT_ITERATIONS_COUNT)
            .measurementTime(TimeValue.seconds(MEASUREMENT_TIME_COUNT))
            .build();

        new Runner(options).run();
    }
}

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
            .forks(3)
            .warmupForks(1)
            .warmupIterations(5)
            .warmupTime(TimeValue.seconds(10))
            .measurementIterations(5)
            .measurementTime(TimeValue.seconds(10))
            .build();

        new Runner(options).run();
    }
}

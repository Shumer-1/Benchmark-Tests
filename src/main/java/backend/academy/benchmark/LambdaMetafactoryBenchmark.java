package backend.academy.benchmark;

import backend.academy.testedEntity.Student;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class LambdaMetafactoryBenchmark {

    private Student student;
    private Function<Student, String> lambda;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Vladislav", "Borisov");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));

        var callSite = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            methodHandle.type()
        );

        lambda = (Function<Student, String>) callSite.getTarget().invokeExact();
    }

    @Benchmark
    public void lambdaMetafactoryBenchmark(Blackhole blackhole) {
        String name = lambda.apply(student);
        blackhole.consume(name);
    }
}

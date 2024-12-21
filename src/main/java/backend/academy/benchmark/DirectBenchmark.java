package backend.academy.benchmark;

import backend.academy.testedEntity.Student;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class DirectBenchmark {
    private Student student;

    @Setup
    public void setup() {
        student = new Student("Vladislav", "Borisov");
    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        String name = student.name();
        blackhole.consume(name);
    }
}

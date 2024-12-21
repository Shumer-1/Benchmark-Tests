package backend.academy.benchmark;

import backend.academy.testedEntity.Student;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class MethodHandleBenchmark {
    private Student student;
    private MethodHandle methodHandle;

    @Setup
    public void setup() throws NoSuchMethodException, IllegalAccessException {
        student = new Student("Vladislav", "Borisov");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));
    }

    @Benchmark
    public void methodHandleAccess(Blackhole blackhole) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        blackhole.consume(name);
    }
}

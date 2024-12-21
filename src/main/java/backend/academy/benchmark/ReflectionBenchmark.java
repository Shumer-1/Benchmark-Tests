package backend.academy.benchmark;

import backend.academy.testedEntity.Student;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private Student student;
    private Method method;

    @Setup
    public void setup() throws NoSuchMethodException {
        student = new Student("Vladislav", "Borisov");
        method = student.getClass().getMethod("name");
    }

    @Benchmark
    public void reflectionAccess(Blackhole blackhole) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        blackhole.consume(name);
    }
}

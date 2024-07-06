Here is a review of Kotlin interpolation issue connected with implementing Koltin interface (with inline value classes) by another JVM language 

Preamble:
There is `EntityId` which is inline value class
```kotlin
@JvmInline
value class EntityId(val id: UUID)
```

and Kotlin interface that contains abstract function with `EntityId`
```kotlin
interface KotlinInterface<T> {

    fun findById(id: EntityId): T
}
```

I try to implement this interface by 
Java(since java doesn't support kotlin value classes the compiler expect `UUID` type instead of `EntityId`):
```java
public class JavaImpl implements KotlinInterface<Entity> {

    @Override
    public Entity findById(@NotNull UUID id) {
        return new Entity(id, "Some name");
    }
}
```

IDE doesn't ee any issues and no errors provided.

I try to compile project via maven and get the following:
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  22.602 s
[INFO] Finished at: 2024-07-06T12:48:51+04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (compile) on project kotlin-interpolation: Compilation failure: Compilation failure: 
[ERROR] .../kotlin-interpolation/src/main/kotlin/com/example/JavaImpl.java:[9,8] com.example.JavaImpl is not abstract and does not override abstract method findById-WQTzO-4(java.util.UUID) in org.example.com.example.KotlinInterface
[ERROR] .../kotlin-interpolation/src/main/kotlin/com/example/JavaImpl.java:[11,5] method does not override or implement a method from a supertype
[ERROR] .../kotlin-interpolation/src/main/kotlin/com/example/JavaImpl.java:[13,16] Entity(java.util.UUID,java.lang.String) has private access in org.example.com.example.Entity
[ERROR] -> [Help 1]
```

To reproduce the issue you can run: 

```bash
mvn clean compile
```
or
```bash
./mvnw clean compile
```


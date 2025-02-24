package run.auxiliary;

public class HelloWorldLoad {
    public static void main(String[] args) throws ClassNotFoundException {
        String className = "sample.HelloWorld";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class.forName(className, true, classLoader);
    }
}

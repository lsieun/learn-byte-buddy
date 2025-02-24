package sample.hierarchy;

public class Ancestor implements ITest {
    public void sayFromAncestor() {
        System.out.println("Ancestor");
    }

    @Override
    public String test(String name, int age) {
        return String.format("Ancestor: %s - %d", name, age);
    }
}

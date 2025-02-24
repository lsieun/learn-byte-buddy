package sample.hierarchy;

public class GrandFather extends Ancestor {
    public void sayFromGrandFather() {
        System.out.println("GrandFather");
    }

    @Override
    public String test(String name, int age) {
        return String.format("GrandFather: %s - %d", name, age);
    }
}
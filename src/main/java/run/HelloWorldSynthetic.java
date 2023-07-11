package run;

import lsieun.utils.ASMUtils;
import lsieun.utils.FileUtils;

public class HelloWorldSynthetic {
    public static void main(String[] args) {
        String[] array = {
                FileUtils.getFilePath("sample/HelloWorld.class"),
        };

        System.out.println("Remove Synthetic:");
        for (String filepath : array) {
            ASMUtils.removeSynthetic(filepath);
        }
    }
}

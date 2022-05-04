package run;

import lsieun.utils.FileUtils;
import lsieun.utils.OutputUtils;

public class HelloWorldSynthetic {
    public static void main(String[] args) {
        String[] array = {
                FileUtils.getFilePath("sample/HelloWorld.class"),
        };

        System.out.println("Remove Synthetic:");
        for (String filepath : array) {
            OutputUtils.removeSynthetic(filepath);
        }
    }
}

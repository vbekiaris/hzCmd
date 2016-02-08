package vendor.gem;

import com.gemstone.gemfire.distributed.LocatorLauncher;

public class GemLocator {

    public static void main(String[] args) {
        LocatorLauncher locatorLauncher = new LocatorLauncher.Builder()
                .setPort(13489)
                .build();
        locatorLauncher.start();

    }
}
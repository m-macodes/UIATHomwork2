package custom.properties;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static PropsDriver propsDriver = ConfigFactory.create(PropsDriver.class);
    public static PropsUrl propsURL = ConfigFactory.create(PropsUrl.class);
}

package enums;

public class Enums {

    public enum DeviceType {
        PHONE("Phone"),
        TABLET("Tablet"),
        LAPTOP("Laptop");

        private final String displayName;

        DeviceType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum Brand {
        APPLE("Apple"),
        SAMSUNG("Samsung"),
        XIAOMI("Xiaomi"),
        OTHER("Other"),
        MACBOOK_PRO("Macbook pro"),
        MACBOOK_AIR("Macbook air");

        private final String displayName;

        Brand(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

}

package driver;

public enum Browser {
    CHROME {
        @Override
        public AbstractDriverFactory getDriverFactory() {
            return new ChromeFactory();
        }

    },

    EDGE {
        @Override
        public AbstractDriverFactory getDriverFactory() {
            return new EdgeFactory();
        }
    };

    public abstract AbstractDriverFactory getDriverFactory();


}

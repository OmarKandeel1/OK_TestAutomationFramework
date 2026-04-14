package driver;

public enum Browser {
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new Chrome();
        }

    },

    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return new Edge();
        }
    };

    public abstract AbstractDriver getDriverFactory();


}

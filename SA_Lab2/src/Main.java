public class Main {


    public static class Car {
        private final String engineType;
        private final String transmission;
        private final String interiorFeatures;
        private final String exteriorOptions;
        private final String safetyFeatures;


        private Car(CarBuilder builder) {
            this.engineType = builder.engineType;
            this.transmission = builder.transmission;
            this.interiorFeatures = builder.interiorFeatures;
            this.exteriorOptions = builder.exteriorOptions;
            this.safetyFeatures = builder.safetyFeatures;
        }


        public String getEngineType() {
            return engineType;
        }

        public String getTransmission() {
            return transmission;
        }

        public String getInteriorFeatures() {
            return interiorFeatures;
        }

        public String getExteriorOptions() {
            return exteriorOptions;
        }

        public String getSafetyFeatures() {
            return safetyFeatures;
        }


        public static class CarBuilder {
            private String engineType;
            private String transmission;
            private String interiorFeatures;
            private String exteriorOptions;
            private String safetyFeatures;

            public CarBuilder setEngineType(String engineType) {
                this.engineType = engineType;
                return this;
            }

            public CarBuilder setTransmission(String transmission) {
                this.transmission = transmission;
                return this;
            }

            public CarBuilder setInteriorFeatures(String interiorFeatures) {
                this.interiorFeatures = interiorFeatures;
                return this;
            }

            public CarBuilder setExteriorOptions(String exteriorOptions) {
                this.exteriorOptions = exteriorOptions;
                return this;
            }

            public void setSafetyFeatures(String safetyFeatures) {
                this.safetyFeatures = safetyFeatures;
            }

            public Car build() {

                if (engineType == null || transmission == null) {
                    throw new IllegalArgumentException("Engine type and transmission are mandatory!");
                }
                return new Car(this);
            }
        }
    }


    public static class ConfigurationManager {
        private static volatile ConfigurationManager instance;

        private ConfigurationManager() {}

        public static ConfigurationManager getInstance() {
            if (instance == null) {
                synchronized (ConfigurationManager.class) {
                    if (instance == null) {
                        instance = new ConfigurationManager();
                    }
                }
            }
            return instance;
        }

        public Car configureCar() {
            Car.CarBuilder builder = new Car.CarBuilder();

            builder.setEngineType("V8")
                    .setTransmission("Automatic")
                    .setInteriorFeatures("Leather seats, GPS")
                    .setExteriorOptions("Red color, Alloy rims")
                    .setSafetyFeatures("ABS, Airbags, Rear camera");

            return builder.build();
        }
    }

    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();

        Car myCar = configManager.configureCar();

        System.out.println("Your Car Details:");
        System.out.println("Engine: " + myCar.getEngineType());
        System.out.println("Transmission: " + myCar.getTransmission());
        System.out.println("Interior: " + myCar.getInteriorFeatures());
        System.out.println("Exterior: " + myCar.getExteriorOptions());
        System.out.println("Safety: " + myCar.getSafetyFeatures());
    }
}

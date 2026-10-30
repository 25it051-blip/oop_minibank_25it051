public class Thermostat {
    private String location;
    private int temperature;

    private static final int MAX = 30;
    private static final int MIN = 16;
    private static int ActiveCount = 0;

    public Thermostat() {
        location = null;
        temperature = 0;
    }

    public Thermostat(String location, int startTemp) {
        this.location = location;

        if (startTemp >= MIN && startTemp <= MAX)
            temperature = startTemp;
        else
            temperature = 22;

        ActiveCount++;
    }

    public int raise() {
        if (temperature < MAX) {
            temperature++;
            return 0;
        } else {
            System.out.println("Thermostat already raised");
            return 1;
        }
    }

    public int lower() {
        if (temperature > MIN) {
            temperature--;
            return 0;
        } else {
            System.out.println("Thermostat already lowered");
            return 1;
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public static void showactivecount() {
        System.out.println(ActiveCount);
    }

    public Thermostat(String location) {
        this(location, 22);
    }

    public static void main(String[] args) {
        Thermostat thermostat = new Thermostat("temperature", 22);

        for (int i = 0; i < 10; i++) {
            if (thermostat.raise() == 0)
                System.out.println("temperature = " + thermostat.getTemperature());
            else
                break;
        }

        Thermostat thermostat2 = new Thermostat("temperature", 25);

        for (int i = 0; i < 20; i++) {
            if (thermostat2.lower() == 0)
                System.out.println("temperature = " + thermostat2.getTemperature());
            else
                break;
        }

        showactivecount();
    }
}
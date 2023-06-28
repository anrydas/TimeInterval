package das.tools;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DasTimeInterval class used for measuring time intervals in code
 */
public class DasTimeInterval {
    private static final String STORED_VALUE = "%d %s";
    private static final String OUTPUT_MESSAGE = "[%03d] %s: %s";
    protected static final String WARNING_LABEL = "[!WARN] %s";
    protected static final String ERROR_LABEL = "[!ERROR] %s";
    public static final int ERROR_CODE_LABEL_NOT_FOUND = -100;
    public static final int ERROR_CODE_LABEL_STOPPED = -200;
    public static final int ERROR_CODE_UNIT_NOT_FOUND = -300;

    private final Map<String, String> finalData;
    private final Map<String, Instant> initialData;

    public DasTimeInterval() {
        this.initialData = new HashMap<>();
        this.finalData = new LinkedHashMap<>();
    }

    public void startInterval(String label) {
        initialData.put(label, Instant.now());
    }

    public void stopInterval(String label, IntervalUnit unit) {
        if (!initialData.containsKey(label)) {
            putResult(String.format(ERROR_LABEL, label), String.format(STORED_VALUE, ERROR_CODE_LABEL_NOT_FOUND,
                    getFormattedErrorMessage("Label", label, "was not found")));
            return;
        }
        Instant i1 = initialData.get(label);
        Instant i2 = Instant.now();
        Duration d = Duration.between(i1, i2);
        if (finalData.containsKey(label)) {
            putResult(String.format(WARNING_LABEL, label), String.format(STORED_VALUE, ERROR_CODE_LABEL_STOPPED,
                    getFormattedErrorMessage("Label", label, "have been already stopped")));
            return;
        }
        switch (unit) {
            case MILLI:
                putResult(label, String.format(STORED_VALUE, d.toMillis(), IntervalUnit.valueOf(IntervalUnit.MILLI)));
                break;
            case SECONDS:
                putResult(label, String.format(STORED_VALUE, d.getSeconds(), IntervalUnit.valueOf(IntervalUnit.SECONDS)));
                break;
            case MICRO:
                putResult(label, String.format(STORED_VALUE, (int)(d.toNanos() / 1000), IntervalUnit.valueOf(IntervalUnit.MICRO)));
                break;
            case MINUTES:
                putResult(label, String.format(STORED_VALUE, d.toMinutes(), IntervalUnit.valueOf(IntervalUnit.MINUTES)));
                break;
            case NANO:
                putResult(label, String.format(STORED_VALUE, d.toNanos(), IntervalUnit.valueOf(IntervalUnit.NANO)));
                break;
            default:
                putResult(label, String.format(STORED_VALUE, ERROR_CODE_UNIT_NOT_FOUND, getFormattedErrorMessage("Unit", unit.name(), "was not found")));
        }
    }

    private String getFormattedErrorMessage(String ... str) {
        return String.format("The %s '%s' %s", str[0], str[1], str[2]);
    }

    private void putResult(String label, String value) {
        finalData.put(label, value);
    }

    public static IntervalUnit getDurationMinutes() {
        return IntervalUnit.MINUTES;
    }
    public static IntervalUnit getDurationSeconds() {
        return IntervalUnit.SECONDS;
    }
    public static IntervalUnit getDurationMilliSeconds() {
        return IntervalUnit.MILLI;
    }
    public static IntervalUnit getDurationNanoSeconds() {
        return IntervalUnit.NANO;
    }
    public static IntervalUnit getDurationMicroSeconds() {
        return IntervalUnit.MICRO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, String> entry : finalData.entrySet()) {
            sb.append(String.format(OUTPUT_MESSAGE, i++, entry.getKey(), entry.getValue()))
                            .append("\n");
        }
        return sb.toString();
    }
}

enum IntervalUnit {
    MINUTES, SECONDS, MILLI, NANO, MICRO;

    public static boolean contains(IntervalUnit test) {
        for (IntervalUnit c : IntervalUnit.values()) {
            if (c.equals(test)) {
                return true;
            }
        }
        return false;
    }
    public static boolean contains(String test) {
        for (IntervalUnit c : IntervalUnit.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
    public static IntervalUnit fromName(String text) {
        for (IntervalUnit u : IntervalUnit.values()) {
            if (u.name().equalsIgnoreCase(text)) {
                return u;
            }
        }
        return null;
    }
    public static IntervalUnit fromString(String text) {
        for (IntervalUnit u : IntervalUnit.values()) {
            if (valueOf(u).equalsIgnoreCase(text)) {
                return u;
            }
        }
        return null;
    }
    public static String valueOf(IntervalUnit u) {
        return unitToStringMap.getOrDefault(u, "ms");
    }

    private static final Map<IntervalUnit, String> unitToStringMap = new HashMap<>();
    static {
        unitToStringMap.put(MILLI, "ms");
        unitToStringMap.put(SECONDS, "s");
        unitToStringMap.put(MICRO, "μs");
        unitToStringMap.put(MINUTES, "min");
        unitToStringMap.put(NANO, "ns");
    }
}


package broccolai.corn.core;

import java.util.function.Function;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class Numbers {

    private Numbers() {
    }

    /**
     * Get the value of a {@link String} or null if it could not be read
     *
     * @param input    {@link String} to get the number from
     * @param function {@link Function} to be used to find value
     * @param <T>      Type of number
     * @return Parsed number or null if it could not be parsed
     */
    public static <T extends Number> @Nullable T valueOrNull(
            final @Nullable String input,
            final @NonNull Function<String, T> function
    ) {
        if (input == null) {
            return null;
        }

        try {
            return function.apply(input);
        } catch (Exception e) {
            return null;
        }
    }

}

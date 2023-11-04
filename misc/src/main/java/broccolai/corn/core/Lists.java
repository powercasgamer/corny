/*
 * This file is part of corn, licensed under the GNU Lesser General Public License (LGPL) version 3 license.
 *
 * Copyright (C)  2020-2023 broccolai
 * Copyright (C)  2020-2023 Contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package broccolai.corn.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class Lists {

    private Lists() {
    }

    /**
     * Group a list by a given function
     *
     * @param <R>      Type to be grouped by
     * @param <T>      Type of the lists elements
     * @param input    List to be grouped
     * @param function Function to group the result by
     * @return Map of R as the keys and T as the values
     */
    public static <@Nullable T, @Nullable R> @NonNull Map<R, @NonNull List<T>> group(
            final @NonNull Iterable<T> input,
            final @NonNull Function<T, R> function
    ) {
        final Map<R, List<T>> output = new HashMap<>();

        for (final T value : input) {
            final R type = function.apply(value);
            output.putIfAbsent(type, new ArrayList<>());

            final List<T> current = output.get(type);
            current.add(value);

            output.put(type, current);
        }

        return output;
    }

    /**
     * Map a list using a given function.
     *
     * @param input    the list to be mapped
     * @param function the function to be applied to all elements
     * @param <R>      new type of the lists elements
     * @param <T>      current type of the lists elements
     * @return a list with the mapped elements
     */
    public static <@Nullable R, @Nullable T> @NonNull List<R> map(
            final @NonNull Iterable<T> input,
            final @NonNull Function<T, R> function
    ) {
        final List<R> output = new ArrayList<>();

        for (final T value : input) {
            output.add(function.apply(value));
        }

        return output;
    }

    /**
     * Find the last element in a List, matching a predicate.
     *
     * @param input the list to search
     * @param predicate the predicate to match against
     * @param <T> type of list elements
     * @return the found value
     */
    public static <@Nullable T> @NonNull T last(
            final @NonNull List<T> input,
            final @NonNull Predicate<T> predicate
    ) {
        for (int i = input.size() - 1; i >= 0; i--) {
            final T value = input.get(i);

            if (predicate.test(value)) {
                return value;
            }
        }

        throw new IllegalArgumentException();
    }

}

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
        } catch (Exception ignored) {
            return null;
        }
    }

}

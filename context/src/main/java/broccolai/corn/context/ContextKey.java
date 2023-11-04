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
package broccolai.corn.context;

import io.leangen.geantyref.TypeToken;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ContextKey<@NonNull T> {

    /**
     * Get the namespace name.
     *
     * @return Namespace of key
     */
    @NonNull String namespace();

    /**
     * Get the name of the key.
     *
     * @return Name of key
     */
    @NonNull String name();

    /**
     * Get the type via a TypeToken of the expected value.
     *
     * @return TypeToken of the expected type of value
     */
    @NonNull TypeToken<@NonNull T> token();

    /**
     * Create a ContextKey through TypeToken
     *
     * @param namespace Namespace of key
     * @param name      Name of key
     * @param token     TypeToken of the keys expected value
     * @param <T>       Type associated with the key
     * @return Built ContextKey with T generic
     */
    static <T> ContextKey<T> of(final @NonNull String namespace, final @NonNull String name, final @NonNull TypeToken<T> token) {
        return new ContextKeyImpl<>(namespace, name, token);
    }

    /**
     * Create a ContextKey through Class
     *
     * @param namespace Namespace of key
     * @param name      Name of key
     * @param clazz     Clazz of the keys expected value
     * @param <T>       Type associated with the key
     * @return Built ContextKey with T generic
     */
    static <T> ContextKey<T> of(final @NonNull String namespace, final @NonNull String name, final @NonNull Class<T> clazz) {
        return new ContextKeyImpl<>(namespace, name, TypeToken.get(clazz));
    }

}

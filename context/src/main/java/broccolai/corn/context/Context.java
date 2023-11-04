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

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface Context {

    /**
     * Get a value out of the context through a key.
     *
     * @param key Context key to query against
     * @param <T> Type associated with the key
     * @return Optional of the stored context key assigned ot the T type
     */
    <T> Optional<T> get(@NonNull ContextKey<T> key);

    /**
     * Store a value into the context, defined by a key.
     *
     * @param key   Context key to assign with
     * @param value Value to store against the key
     * @param <T>   Type associated with the key and value
     */
    <T> void put(@NonNull ContextKey<T> key, @NonNull T value);

    /**
     * Performs given consumer on all entries stored.
     *
     * @param consumer Consumer to be applied
     */
    void forEach(@NonNull BiConsumer<ContextKey<?>, Object> consumer);

}

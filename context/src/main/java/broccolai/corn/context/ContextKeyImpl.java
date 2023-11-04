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
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

final class ContextKeyImpl<T> implements ContextKey<T> {

    private final String namespace;
    private final String name;
    private final TypeToken<T> token;

    ContextKeyImpl(final @NonNull String namespace, final @NonNull String name, final @NonNull TypeToken<T> token) {
        this.namespace = namespace;
        this.name = name;
        this.token = token;
    }

    @Override
    public @NonNull String namespace() {
        return this.namespace;
    }

    @Override
    public @NonNull String name() {
        return this.name;
    }

    @Override
    public @NonNull TypeToken<@NonNull T> token() {
        return this.token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.namespace, this.name);
    }

    @Override
    public boolean equals(final @Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextKey<?> that)) {
            return false;
        }

        return Objects.equals(this.namespace(), that.namespace()) && Objects.equals(this.name(), that.name());
    }

}

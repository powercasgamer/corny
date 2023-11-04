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
package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

final class ObjectProperty implements Property {

    private final @NonNull String name;
    private final @Nullable Integer objectHash;

    ObjectProperty(final @NonNull String name, final @Nullable Object object) {
        this.name = name;
        this.objectHash = object != null ? object.hashCode() : null;
    }

    @Override
    public @NonNull String name() {
        return this.name;
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectProperty objectProperty)) {
            return false;
        }

        return Objects.equals(this.name(), objectProperty.name()) && Objects.equals(this.objectHash, objectProperty.objectHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, objectHash);
    }

}

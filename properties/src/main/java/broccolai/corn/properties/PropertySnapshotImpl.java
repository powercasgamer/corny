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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

final class PropertySnapshotImpl implements PropertySnapshot {

    private final Map<String, Property> flattenedProperties = new HashMap<>();

    PropertySnapshotImpl(final @NonNull Collection<@NonNull Property> properties) {
        for (final Property property : properties) {
            if (property instanceof FlattenableProperty flattenableProperty) {
                flattenableProperty.flatten().forEach((key, value) -> {
                    this.flattenedProperties.put(property.name() + ":" + key, value);
                });
                continue;
            }

            this.flattenedProperties.put(property.name(), property);
        }
    }

    @Override
    public @NonNull Iterator<Property> iterator() {
        return this.flattenedProperties.values().iterator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(flattenedProperties);
    }

    @Override
    public boolean equals(final Object that) {
        if (!(that instanceof PropertySnapshotImpl propertySnapshot)) {
            return false;
        }

        if (this == that) {
            return true;
        }

        Map<String, Property> targetProperties = propertySnapshot.flattenedProperties;

        if (this.flattenedProperties.size() != targetProperties.size()) {
            return false;
        }

        for (Map.Entry<String, Property> entry : this.flattenedProperties.entrySet()) {
            String name = entry.getKey();
            Property property = entry.getValue();

            if (!targetProperties.containsKey(name)) {
                return false;
            }

            if (!targetProperties.get(name).equals(property)) {
                return false;
            }
        }

        return true;
    }

}

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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

record NestedProperty(@NonNull String name, @Nullable PropertyHolder propertyHolder) implements FlattenableProperty {

    @Override
    public @NonNull Map<String, Property> flatten() {
        if (propertyHolder == null) {
            return Collections.emptyMap();
        }

        Map<String, Property> results = new HashMap<>();

        for (final Property property : this.propertyHolder.properties()) {
            if (property instanceof FlattenableProperty flattenableProperty) {
                flattenableProperty.flatten().forEach((key, value) -> {
                    results.put(property.name() + ":" + key, value);
                });
                continue;
            }

            results.put(property.name(), property);
        }

        return results;
    }

}

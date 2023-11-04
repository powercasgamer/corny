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

import java.util.HashMap;
import java.util.Map;

record MapProperty(
        @NonNull String name,
        @NonNull Map<String, PropertyHolder> entries
) implements FlattenableProperty {

    @Override
    public @NonNull Map<String, Property> flatten() {
        Map<String, Property> results = new HashMap<>();

        this.entries.forEach((key, propertyHolder) -> {
            String keyedName = this.name + ":" + key;
            for (final Property property : propertyHolder.properties()) {
                results.put(keyedName + ":" + property.name(), property);
            }
        });

        return results;
    }

}

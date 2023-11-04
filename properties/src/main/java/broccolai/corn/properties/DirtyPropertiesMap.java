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
import java.util.Map;

public interface DirtyPropertiesMap<K, V extends PropertyHolder> extends Map<K, V> {

    /**
     * Create a DirtyPropertiesMap from an existing Map instance
     *
     * @param base Map instance to generate with
     * @param <K>  the type of keys maintained by this map
     * @param <V>  the type of mapped values
     * @return Map created with base
     */
    static <K, V extends PropertyHolder> DirtyPropertiesMap<K, V> from(final @NonNull Map<K, V> base) {
        return new DirtyPropertiesMapImpl<>(base);
    }

    /**
     * Create a DirtyPropertiesMap with a HashMap
     *
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     * @return Map created with a HashMap
     */
    static <K, V extends PropertyHolder> DirtyPropertiesMap<K, V> hashmap() {
        return new DirtyPropertiesMapImpl<>(new HashMap<>());
    }

    /**
     * Reset all dirty trackers to the current map state
     */
    void clean();

    /**
     * Retrieve all values from the map that are currently dirty
     *
     * @return Collection containing dirty values
     */
    @NonNull Collection<@NonNull V> dirty();

    /**
     * Check if a key currently has a dirty value
     *
     * @param key Key to lookup with
     * @return true if the key has a dirty value
     */
    boolean isDirty(@NonNull K key);

    /**
     * Set a keys value to be dirty
     *
     * @param key Key to set with
     */
    void setDirty(@NonNull K key);

}

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

public interface Property {

    /**
     * Get the name of the property
     *
     * @return Name of the property
     */
    @NonNull String name();

    /**
     * Construct a Property with an Object as it's value
     *
     * @param name   Name of the property
     * @param object Value of the property
     * @return Constructed Property
     */
    static @NonNull Property of(final @NonNull String name, final @NonNull Object object) {
        return new ObjectProperty(name, object);
    }

    /**
     * Construct a Property with a {@link PropertyHolder} as it's value
     *
     * @param name           Name of the property
     * @param propertyHolder Value of the property
     * @return Constructed Property
     */
    static @NonNull Property of(final @NonNull String name, final @NonNull PropertyHolder propertyHolder) {
        return new NestedProperty(name, propertyHolder);
    }

    /**
     * Construct a Property with a {@link Collection<PropertyHolder>} as it's value
     *
     * @param name               Name of the property
     * @param propertyCollection Value of the property
     * @return Constructed Property
     */
    static @NonNull Property of(final @NonNull String name, final @NonNull Collection<PropertyHolder> propertyCollection) {
        return new CollectionProperty(name, propertyCollection);
    }

}

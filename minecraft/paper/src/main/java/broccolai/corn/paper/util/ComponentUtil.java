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
package broccolai.corn.paper.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DefaultQualifier(NonNull.class)
public final class ComponentUtil {

    private ComponentUtil() {
    }

    /**
     * Disables italics on a component if it is not already set.
     * @param component the component
     * @return the component with italics disabled
     */
    public static Component disableItalics(final Component component) {
        if (component.decoration(TextDecoration.ITALIC) == TextDecoration.State.NOT_SET) {
            return component.decoration(TextDecoration.ITALIC, false);
        }
        return component;
    }

    /**
     * Disables italics on a component if it is not already set.
     * @param components the components
     * @return the components with italics disabled
     */
    public static List<Component> disableItalicsList(final Collection<? extends Component> components) {
        final List<Component> newComponents = new ArrayList<>(components.size());
        for (final Component component : components) {
            newComponents.add(disableItalics(component));
        }

        return newComponents;
    }
}

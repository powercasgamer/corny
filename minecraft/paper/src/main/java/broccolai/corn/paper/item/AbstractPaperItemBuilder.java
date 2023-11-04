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
package broccolai.corn.paper.item;

import broccolai.corn.paper.util.ComponentUtil;
import broccolai.corn.spigot.item.AbstractItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Modifies {@link ItemStack}s using Paper-specific methods.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractPaperItemBuilder<B extends AbstractPaperItemBuilder<B, M>, M extends ItemMeta>
        extends AbstractItemBuilder<B, M> {

    protected AbstractPaperItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public @Nullable Component name() {
        return this.itemMeta.displayName();
    }

    /**
     * Sets the display name. Pass {@code null} to reset.
     * <p>
     * The component passed in is appended to an empty component decorated with
     * italicization set to false. This effectively bypasses the default,
     * italicized text formatting, resulting in the text being only the component
     * that is passed in.
     *
     * @param name the display name
     * @return the builder
     */
    public @NonNull B name(final @Nullable Component name) {
        if (name == null) {
            this.itemMeta.displayName(null);
            return (B) this;
        }

        // sidestep default formatting by creating a dummy component and appending the component to that
        this.itemMeta.displayName(ComponentUtil.disableItalics(name));

        return (B) this;
    }

    /**
     * Gets the lore.
     *
     * @return the lore
     */
    public @Nullable List<Component> lore() {
        return this.itemMeta.lore();
    }

    /**
     * Sets the lore. Pass {@code null} to reset.
     * <p>
     * Each component passed in is appended to an empty component decorated with
     * italicization set to false. This effectively bypasses the default,
     * italicized text formatting, resulting in the text being only the component
     * that is passed in.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @Nullable List<Component> lines) {
        if (lines == null) {
            this.itemMeta.lore(null);
            return (B) this;
        }

        // sidestep default formatting by creating a dummy component and appending the component to that
        this.itemMeta.lore(ComponentUtil.disableItalicsList(lines));
        return (B) this;
    }

    /**
     * A utility method that converts the provided {@code lines} into a
     * {@link List} using {@link List#of(Object[])}, and calls
     * {@link #lore(List)} using the new {@link List} as the argument.
     *
     * @param line the line of the lore
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Component line) {
        return this.lore(List.of(line));
    }

    /**
     * A utility method that converts the provided {@code lines} into a
     * {@link List} using {@link List#of(Object[])}, and calls
     * {@link #lore(List)} using the new {@link List} as the argument.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Component... lines) {
        return this.lore(List.of(lines));
    }

    /**
     * A utility method that converts the provided {@code lines} into a
     * {@link List} using {@link List#of(Object[])}, and calls
     * {@link #lore(List)} using the new {@link List} as the argument.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B loreList(final @NonNull Component... lines) {
        return this.lore(List.of(lines));
    }

    /**
     * Directly modifies the lore with a {@link Consumer}.
     * If the item has no lore, an empty {@link List} will
     * be supplied to the {@link Consumer} instead.
     *
     * @param consumer the {@link Consumer} to modify the lore with
     * @return the builder
     */
    public @NonNull B loreModifier(final @NonNull Consumer<@NonNull List<Component>> consumer) {
        final @NonNull List<Component> lore = Optional
                .ofNullable(this.itemMeta.lore())
                .orElse(new ArrayList<>());

        consumer.accept(lore);

        this.itemMeta.lore(ComponentUtil.disableItalicsList(lore));
        return (B) this;
    }

}

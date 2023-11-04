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
package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link BundleMeta}.
 */
@SuppressWarnings("unused")
public final class BundleBuilder extends AbstractPaperItemBuilder<BundleBuilder, BundleMeta> {

    private BundleBuilder(final @NonNull ItemStack itemStack, final @NonNull BundleMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link BundleBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link BundleBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BundleBuilder(itemStack, castMeta(itemStack.getItemMeta(), BundleMeta.class));
    }

    /**
     * Creates an {@link BundleBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link BundleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BundleBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link BundleBuilder} of type {@link Material#BUNDLE}. A convenience method.
     *
     * @return instance of {@link BundleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder ofBundle() throws IllegalArgumentException {
        return ofType(Material.BUNDLE);
    }

    /**
     * Gets the items.
     *
     * @return the items
     */
    public @NonNull List<@NonNull ItemStack> items() {
        return this.itemMeta.getItems();
    }

    /**
     * Sets the items. Pass {@code null} to reset.
     *
     * @param items the items
     * @return the builder
     */
    public @NonNull BundleBuilder items(final @Nullable List<@NonNull ItemStack> items) {
        this.itemMeta.setItems(items);
        return this;
    }

    /**
     * Adds an item.
     *
     * @param item the item to add
     * @return the builder
     */
    public @NonNull BundleBuilder addItem(final @NonNull ItemStack... item) {
        for (final ItemStack i : item) {
            this.itemMeta.addItem(i);
        }
        return this;
    }

}

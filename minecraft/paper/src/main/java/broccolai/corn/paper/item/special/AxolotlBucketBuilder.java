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
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link AxolotlBucketMeta}.
 */
@SuppressWarnings("unused")
public final class AxolotlBucketBuilder extends AbstractPaperItemBuilder<AxolotlBucketBuilder, AxolotlBucketMeta> {

    private AxolotlBucketBuilder(final @NonNull ItemStack itemStack, final @NonNull AxolotlBucketMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link AxolotlBucketBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull AxolotlBucketBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new AxolotlBucketBuilder(itemStack, castMeta(itemStack.getItemMeta(), AxolotlBucketMeta.class));
    }

    /**
     * Creates an {@link AxolotlBucketBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull AxolotlBucketBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return AxolotlBucketBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link AxolotlBucketBuilder} of type {@link Material#AXOLOTL_BUCKET}. A convenience method.
     *
     * @return instance of {@link AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull AxolotlBucketBuilder ofAxolotlBucket() throws IllegalArgumentException {
        return ofType(Material.AXOLOTL_BUCKET);
    }

    /**
     * Gets the variant.
     *
     * @return the variant
     */
    public Axolotl.@NonNull Variant variant() {
        return this.itemMeta.getVariant();
    }

    /**
     * Sets the variant.
     *
     * @param variant the variant
     * @return the builder
     */
    public @NonNull AxolotlBucketBuilder variant(final Axolotl.@NonNull Variant variant) {
        this.itemMeta.setVariant(variant);
        return this;
    }

    /**
     * Gets whether a variant tag exists.
     * If true, a specific axolotl will be spawned.
     *
     * @return whether a variant tag exists
     */
    public boolean hasVariant() {
        return this.itemMeta.hasVariant();
    }

}

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
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link BlockDataMeta}.
 */
@SuppressWarnings("unused")
public final class BlockDataBuilder extends AbstractPaperItemBuilder<BlockDataBuilder, BlockDataMeta> {

    private BlockDataBuilder(final @NonNull ItemStack itemStack, final @NonNull BlockDataMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link BlockDataBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BlockDataBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BlockDataBuilder(itemStack, castMeta(itemStack.getItemMeta(), BlockDataMeta.class));
    }

    /**
     * Creates a {@link BlockDataBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BlockDataBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BlockDataBuilder.of(getItem(material));
    }

    /**
     * Gets a copy of the {@link BlockData}. Creates a new one if it doesn't currently exist.
     *
     * @param material the material the data should be retrieved in the context of
     * @return the {@link BlockData}
     */
    public @NonNull BlockData blockData(final @NonNull Material material) {
        return this.itemMeta.getBlockData(material);
    }

    /**
     * Sets the {@link BlockData}.
     *
     * @param blockData the {@link BlockData}
     * @return the builder
     */
    public @NonNull BlockDataBuilder blockData(final @NonNull BlockData blockData) {
        this.itemMeta.setBlockData(blockData);
        return this;
    }

    /**
     * Gets whether a {@link BlockData} is currently attached.
     *
     * @return whether a {@link BlockData} is currently attached
     */
    public boolean hasBlockData() {
        return this.itemMeta.hasBlockData();
    }

}

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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link Repairable}.
 */
@SuppressWarnings("unused")
public final class RepairableBuilder extends AbstractPaperItemBuilder<RepairableBuilder, Repairable> {

    private RepairableBuilder(final @NonNull ItemStack itemStack, final @NonNull Repairable itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link RepairableBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link RepairableBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull RepairableBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new RepairableBuilder(itemStack, castMeta(itemStack.getItemMeta(), Repairable.class));
    }

    /**
     * Creates an {@link RepairableBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link RepairableBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull RepairableBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return RepairableBuilder.of(getItem(material));
    }

    /**
     * Gets the repair cost.
     *
     * @return the repair cost
     */
    public @Nullable Integer repairCost() {
        if (this.itemMeta.hasRepairCost()) {
            return null;
        }
        return this.itemMeta.getRepairCost();
    }

    /**
     * Sets the repair cost.
     *
     * @param repairCost the repair cost
     * @return the builder
     */
    public @NonNull RepairableBuilder repairCost(final @NonNull Integer repairCost) {
        this.itemMeta.setRepairCost(repairCost);
        return this;
    }

}

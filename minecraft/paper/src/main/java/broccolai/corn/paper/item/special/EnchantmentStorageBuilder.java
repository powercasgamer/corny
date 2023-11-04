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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link EnchantmentStorageMeta}.
 */
@SuppressWarnings("unused")
public final class EnchantmentStorageBuilder extends AbstractPaperItemBuilder<EnchantmentStorageBuilder, EnchantmentStorageMeta> {

    private EnchantmentStorageBuilder(final @NonNull ItemStack itemStack, final @NonNull EnchantmentStorageMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link EnchantmentStorageBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link EnchantmentStorageBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull EnchantmentStorageBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new EnchantmentStorageBuilder(itemStack, castMeta(itemStack.getItemMeta(), EnchantmentStorageMeta.class));
    }

    /**
     * Creates an {@link EnchantmentStorageBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link EnchantmentStorageBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull EnchantmentStorageBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return EnchantmentStorageBuilder.of(getItem(material));
    }

    /**
     * Gets the stored enchants.
     *
     * @return the stored enchants
     */
    public @NonNull Map<@NonNull Enchantment, @NonNull Integer> storedEnchants() {
        return this.itemMeta.getStoredEnchants();
    }

    /**
     * Sets the stored enchants.
     *
     * @param storedEnchants the stored enchants
     * @return the builder
     */
    public @NonNull EnchantmentStorageBuilder storedEnchants(final @NonNull Map<@NonNull Enchantment, @NonNull Integer> storedEnchants) {
        for (final @NonNull Enchantment item : this.itemMeta.getStoredEnchants().keySet()) {
            this.itemMeta.removeStoredEnchant(item);
        }
        for (final Map.@NonNull Entry<@NonNull Enchantment, @NonNull Integer> entry : storedEnchants.entrySet()) {
            this.addStoredEnchant(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * Adds a stored enchant.
     *
     * @param enchant the {@link Enchantment} to add
     * @param level   the level of the {@link Enchantment}
     * @return the builder
     */
    public @NonNull EnchantmentStorageBuilder addStoredEnchant(final @NonNull Enchantment enchant, final int level) {
        this.itemMeta.addStoredEnchant(enchant, level, true);
        return this;
    }

    /**
     * Removes a stored enchant.
     *
     * @param enchant the {@link Enchantment} to remove
     * @return the builder
     */
    public @NonNull EnchantmentStorageBuilder removeStoredEnchant(final @NonNull Enchantment... enchant) {
        for (final @NonNull Enchantment item : enchant) {
            this.itemMeta.removeStoredEnchant(item);
        }
        return this;
    }

}

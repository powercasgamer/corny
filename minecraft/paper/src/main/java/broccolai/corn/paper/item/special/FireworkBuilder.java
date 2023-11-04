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
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.common.value.qual.IntRange;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link FireworkMeta}.
 */
@SuppressWarnings("unused")
public final class FireworkBuilder extends AbstractPaperItemBuilder<FireworkBuilder, FireworkMeta> {

    private FireworkBuilder(final @NonNull ItemStack itemStack, final @NonNull FireworkMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link FireworkBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link FireworkBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull FireworkBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new FireworkBuilder(itemStack, castMeta(itemStack.getItemMeta(), FireworkMeta.class));
    }

    /**
     * Creates a {@link FireworkBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link FireworkBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull FireworkBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return FireworkBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link FireworkBuilder} of type {@link Material#FIREWORK_ROCKET}. A convenience method.
     *
     * @return instance of {@link FireworkBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull FireworkBuilder ofFireworkRocket() throws IllegalArgumentException {
        return ofType(Material.FIREWORK_ROCKET);
    }

    /**
     * Gets the power.
     *
     * @return the power
     */
    public @IntRange(from = 0, to = 127) int power() {
        return this.itemMeta.getPower();
    }

    /**
     * Sets the power.
     *
     * @param power the power
     * @return the builder
     */
    public @NonNull FireworkBuilder power(final @IntRange(from = 0, to = 127) int power) {
        this.itemMeta.setPower(power);
        return this;
    }

    /**
     * Gets the {@link FireworkEffect}s.
     *
     * @return the {@link FireworkEffect}s
     */
    public @NonNull List<@NonNull FireworkEffect> fireworkEffects() {
        return this.itemMeta.getEffects();
    }

    /**
     * Sets the {@link FireworkEffect}s.
     *
     * @param fireworkEffects the {@link FireworkEffect}s
     * @return the builder
     */
    public @NonNull FireworkBuilder fireworkEffects(final @NonNull List<@NonNull FireworkEffect> fireworkEffects) {
        this.itemMeta.clearEffects();
        this.itemMeta.addEffects(fireworkEffects);
        return this;
    }

    /**
     * Adds a {@link FireworkEffect}.
     *
     * @param fireworkEffect the {@link FireworkEffect} to add
     * @return the builder
     */
    public @NonNull FireworkBuilder addFireworkEffect(final @NonNull FireworkEffect... fireworkEffect) {
        this.itemMeta.addEffects(fireworkEffect);
        return this;
    }

}

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
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.common.value.qual.IntRange;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link BannerMeta}.
 */
@SuppressWarnings("unused")
public final class BannerBuilder extends AbstractPaperItemBuilder<BannerBuilder, BannerMeta> {

    private BannerBuilder(final @NonNull ItemStack itemStack, final @NonNull BannerMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link BannerBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link BannerBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BannerBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BannerBuilder(itemStack, castMeta(itemStack.getItemMeta(), BannerMeta.class));
    }

    /**
     * Creates a {@link SkullBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link SkullBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BannerBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BannerBuilder.of(getItem(material));
    }

    /**
     * Gets the patterns.
     *
     * @return the patterns
     */
    public @NonNull List<Pattern> patterns() {
        return this.itemMeta.getPatterns();
    }

    /**
     * Sets the patterns.
     *
     * @param patterns the patterns
     * @return the builder
     */
    public @NonNull BannerBuilder patterns(final @NonNull List<Pattern> patterns) {
        this.itemMeta.setPatterns(patterns);
        return this;
    }

    /**
     * Gets a pattern.
     *
     * @param index the index (0-indexed)
     * @return the pattern
     */
    public @NonNull Pattern getPattern(final @IntRange(from = 0) int index) {
        return this.itemMeta.getPattern(index);
    }

    /**
     * Sets a pattern.
     *
     * @param index   the index
     * @param pattern the pattern (0-indexed)
     * @return the builder
     */
    public @NonNull BannerBuilder setPattern(final @IntRange(from = 0) int index, final @NonNull Pattern pattern) {
        this.itemMeta.setPattern(index, pattern);
        return this;
    }

    /**
     * Adds a pattern.
     *
     * @param pattern the pattern
     * @return the builder
     */
    public @NonNull BannerBuilder addPattern(final @NonNull Pattern... pattern) {
        for (final Pattern item : pattern) {
            this.itemMeta.addPattern(item);
        }
        return this;
    }

    /**
     * Removes a pattern.
     *
     * @param index the index (0-indexed)
     * @return the builder
     */
    public @NonNull BannerBuilder removePattern(final @IntRange(from = 0) int... index) {
        for (final int item : index) {
            this.itemMeta.removePattern(item);
        }
        return this;
    }

}

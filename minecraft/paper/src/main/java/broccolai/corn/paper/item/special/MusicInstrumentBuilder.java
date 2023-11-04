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
import org.bukkit.MusicInstrument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MusicInstrumentMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link MusicInstrumentMeta}.
 */
@SuppressWarnings("unused")
public class MusicInstrumentBuilder extends AbstractPaperItemBuilder<MusicInstrumentBuilder, MusicInstrumentMeta> {

    protected MusicInstrumentBuilder(final @NonNull ItemStack itemStack, final @NonNull MusicInstrumentMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link MusicInstrumentBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link MusicInstrumentBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull MusicInstrumentBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new MusicInstrumentBuilder(itemStack, castMeta(itemStack.getItemMeta(), MusicInstrumentMeta.class));
    }

    /**
     * Creates an {@link MusicInstrumentBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link MusicInstrumentBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull MusicInstrumentBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return MusicInstrumentBuilder.of(getItem(material));
    }

    /**
     * Set the instrument
     *
     * @param instrument to set
     * @return the builder
     */
    public @NonNull MusicInstrumentBuilder instrument(final @Nullable MusicInstrument instrument) {
        this.itemMeta.setInstrument(instrument);
        return this;
    }

    /**
     * get the music instrument
     *
     * @return the MusicInstrument
     */
    public @Nullable MusicInstrument instrument() {
        return this.itemMeta.getInstrument();
    }

}

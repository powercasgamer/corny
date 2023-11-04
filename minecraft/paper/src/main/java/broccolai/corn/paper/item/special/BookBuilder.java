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
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntRange;

import java.util.List;


/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link BookMeta}.
 */
@SuppressWarnings("unused")
public final class BookBuilder extends AbstractPaperItemBuilder<BookBuilder, BookMeta> {

    private BookBuilder(final @NonNull ItemStack itemStack, final @NonNull BookMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link BookBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link BookBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BookBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BookBuilder(itemStack, castMeta(itemStack.getItemMeta(), BookMeta.class));
    }

    /**
     * Creates a {@link BookBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link BookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BookBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BookBuilder.of(getItem(material));
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public @Nullable Component title() {
        return this.itemMeta.title();
    }

    /**
     * Sets the title. Pass {@code null} to reset.
     *
     * @param title the title
     * @return the builder
     */
    public @NonNull BookBuilder title(final @Nullable Component title) {
        this.itemMeta.title(title);
        return this;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public @Nullable Component author() {
        return this.itemMeta.author();
    }

    /**
     * Sets the author. Pass {@code null} to reset.
     *
     * @param author the author
     * @return the builder
     */
    public @NonNull BookBuilder author(final @Nullable Component author) {
        this.itemMeta.author(author);
        return this;
    }

    /**
     * Gets the {@link BookMeta.Generation}.
     *
     * @return the generation
     */
    public BookMeta.@Nullable Generation generation() {
        return this.itemMeta.getGeneration();
    }

    /**
     * Sets the {@link BookMeta.Generation}. Pass {@code null} to reset.
     *
     * @param generation the generation
     * @return the builder
     */
    public @NonNull BookBuilder generation(final BookMeta.@Nullable Generation generation) {
        this.itemMeta.setGeneration(generation);
        return this;
    }

    /**
     * Gets the pages.
     *
     * @return the pages
     */
    public @NonNull List<@NonNull Component> pages() {
        return this.itemMeta.pages();
    }

    /**
     * Sets the pages. Pass {@code null} to reset.
     *
     * @param pages the pages
     * @return the builder
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public @NonNull BookBuilder pages(final @Nullable List<@NonNull Component> pages) {
        if (pages == null) {
            this.itemMeta.pages(List.of());
            return this;
        }

        // in Paper's implementation, this will actually mutate the internal BookMeta instance
        // you can see the discussion I had about this on the Paper discord
        // https://canary.discord.com/channels/289587909051416579/555462289851940864/872168673283043328
        // https://canary.discord.com/channels/289587909051416579/555462289851940864/872172549075783690
        // once the Javadocs are fixed on Paper's end, these comments may be removed
        this.itemMeta.pages(pages);
        return this;
    }

    /**
     * Gets the page at that index.
     *
     * @param index the index (1-indexed)
     * @return the page
     */
    public @NonNull Component getPage(final @IntRange(from = 1) int index) {
        return this.itemMeta.page(index);
    }

    /**
     * Sets the page at that index.
     *
     * @param index the index (1-indexed)
     * @param page  the page
     * @return the builder
     */
    public @NonNull BookBuilder setPage(final @IntRange(from = 1) int index, final @NonNull Component page) {
        this.itemMeta.page(index, page);
        return this;
    }

    /**
     * Adds a page.
     *
     * @param page the page to add
     * @return the builder
     */
    public @NonNull BookBuilder addPage(final @NonNull Component... page) {
        this.itemMeta.addPages(page);
        return this;
    }

    /**
     * Removes a page.
     *
     * @param index the index of the page to remove (1-indexed)
     * @return the builder
     */
    public @NonNull BookBuilder removePage(final @IntRange(from = 1) int... index) {
        for (final int i : index) {
            this.itemMeta.page(i, Component.empty());
        }
        return this;
    }

    /**
     * Gets the page count.
     *
     * @return the page count
     */
    public int pageCount() {
        return this.itemMeta.getPageCount();
    }

}

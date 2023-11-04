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
package broccolai.corn.spigot.item;

import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Modifies {@link ItemStack}s using methods that both Paper and Spigot share.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractItemBuilder<B extends AbstractItemBuilder<B, M>, M extends ItemMeta> {

    /**
     * The {@link ItemStack} to modify during building. This will be cloned and
     * returned upon {@link #build()}.
     */
    protected final @NonNull ItemStack itemStack;
    /**
     * The {@link ItemMeta} to modify during building. This will be applied to
     * the {@link #itemStack} upon {@link #build()}.
     */
    protected final @NonNull M itemMeta;

    /**
     * @param itemStack the {@link ItemStack}
     * @param itemMeta  the {@link ItemMeta}
     */
    protected AbstractItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta;
    }

    /**
     * Attempts to cast {@code meta} to {@code expectedType},
     * and returns the result if successful.
     *
     * @param meta         the meta
     * @param expectedType the class of the expected type
     * @param <T>          the expected type
     * @return {@code meta} casted to {@code expectedType}
     * @throws IllegalArgumentException if {@code} meta is not the type of {@code expectedType}
     */
    protected static <T extends ItemMeta> T castMeta(final ItemMeta meta, final Class<T> expectedType)
            throws IllegalArgumentException {
        try {
            return expectedType.cast(meta);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException("The ItemMeta must be of type "
                    + expectedType.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Returns an {@link ItemStack} of {@code material} if it is an item,
     * else throws an exception.
     *
     * @param material the material
     * @return an {@link ItemStack} of type {@code material}
     * @throws IllegalArgumentException if {@code material} is not an item
     */
    protected static @NonNull ItemStack getItem(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }
        return new ItemStack(material);
    }

    /**
     * Gets the {@link Material}.
     *
     * @return the {@link Material}
     */
    public @NonNull Material material() {
        return this.itemStack.getType();
    }

    /**
     * Sets the {@link Material}.
     *
     * @param material the {@link Material}
     * @return the builder
     */
    public @NonNull B material(final @NonNull Material material) {
        this.itemStack.setType(material);
        return (B) this;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int amount() {
        return this.itemStack.getAmount();
    }

    /**
     * Sets the quantity.
     *
     * @param amount the quantity
     * @return the builder
     */
    public @NonNull B amount(final int amount) {
        this.itemStack.setAmount(amount);
        return (B) this;
    }

    /**
     * Gets data from the {@link ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key  the {@link NamespacedKey} to use
     * @param type the {@link PersistentDataType to use}
     * @param <T>  the primary object type of the data
     * @param <Z>  the retrieve object type of the data
     * @return the data
     */
    public <T, Z> @Nullable Z getData(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T, Z> type
    ) {
        return this.itemMeta.getPersistentDataContainer().get(key, type);
    }

    /**
     * Adds data to the {@link ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key    the {@link NamespacedKey} to use
     * @param type   the {@link PersistentDataType} to use
     * @param object the data to set
     * @param <T>    the primary object type of the data
     * @param <Z>    the retrieve object type of the data
     * @return the builder
     */
    public <T, Z> @NonNull B setData(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T, Z> type,
            final @NonNull Z object
    ) {
        this.itemMeta.getPersistentDataContainer().set(key, type, object);
        return (B) this;
    }

    /**
     * Removes data from the {@link ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key the {@link NamespacedKey} to use
     * @return the builder
     */
    public @NonNull B removeData(
            final @NonNull NamespacedKey key
    ) {
        this.itemMeta.getPersistentDataContainer().remove(key);
        return (B) this;
    }

    /**
     * Gets the {@link ItemFlag}s.
     *
     * @return the {@link ItemFlag}s
     */
    public @NonNull Set<ItemFlag> flags() {
        return this.itemMeta.getItemFlags();
    }

    /**
     * Sets the {@link ItemFlag}s. Pass {@code null} to reset.
     *
     * @param flags the {@link ItemFlag}s
     * @return the builder
     */
    public @NonNull B flags(final @Nullable List<ItemFlag> flags) {
        this.clearFlags();
        if (flags != null) {
            this.itemMeta.addItemFlags(flags.toArray(new ItemFlag[0]));
        }
        return (B) this;
    }

    private void clearFlags() {
        this.itemMeta.removeItemFlags(this.itemMeta.getItemFlags().toArray(new ItemFlag[0]));
    }

    /**
     * Add an {@link ItemFlag}.
     *
     * @param flag the {@link ItemFlag} to add
     * @return the builder
     */
    public @NonNull B addFlag(final @NonNull ItemFlag... flag) {
        this.itemMeta.addItemFlags(flag);
        return (B) this;
    }

    /**
     * Remove an {@link ItemFlag}.
     *
     * @param flag the {@link ItemFlag} to remove
     * @return the builder
     */
    public @NonNull B removeFlag(final @NonNull ItemFlag... flag) {
        this.itemMeta.removeItemFlags(flag);
        return (B) this;
    }

    /**
     * Gets the {@link Enchantment}s.
     *
     * @return the {@link Enchantment}s
     */
    public @NonNull Map<Enchantment, Integer> enchants() {
        return new HashMap<>(this.itemStack.getEnchantments());
    }

    /**
     * Sets the {@link Enchantment}s. Pass {@code null} to reset.
     *
     * @param enchants the {@link Enchantment}s
     * @return the builder
     */
    public @NonNull B enchants(final @Nullable Map<Enchantment, Integer> enchants) {
        this.clearEnchants();
        if (enchants != null) {
            this.itemStack.addEnchantments(enchants);
        }
        return (B) this;
    }

    private void clearEnchants() {
        for (final Enchantment enchantment : this.itemMeta.getEnchants().keySet()) {
            this.itemMeta.removeEnchant(enchantment);
        }
    }

    /**
     * Adds an {@link Enchantment}.
     *
     * @param enchantment the {@link Enchantment} to add
     * @param level       the level of the {@link Enchantment}
     * @return the builder
     */
    public @NonNull B addEnchant(final @NonNull Enchantment enchantment, final int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return (B) this;
    }

    /**
     * Removes an {@link Enchantment}.
     *
     * @param enchantment the {@link Enchantment} to remove
     * @return the builder
     */
    public @NonNull B removeEnchant(final @NonNull Enchantment... enchantment) {
        for (final Enchantment item : enchantment) {
            this.itemMeta.removeEnchant(item);
        }
        return (B) this;
    }

    /**
     * Sets whether the {@link ItemStack} is unbreakable.
     *
     * @param unbreakable whether the {@link ItemStack} is unbreakable
     * @return the builder
     */
    public @NonNull B unbreakable(final boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return (B) this;
    }

    /**
     * Get the max stack size.
     *
     * @return the max stack size
     */
    public int maxStackSize() {
        return this.itemStack.getMaxStackSize();
    }

    /**
     * Gets the custom model data.
     *
     * @return the custom model data
     */
    public @Nullable Integer customModelData() {
        // we use the wrapper with null signifying absent for api consistency
        if (!this.itemMeta.hasCustomModelData()) {
            return null;
        }
        return this.itemMeta.getCustomModelData();
    }

    /**
     * Sets the custom model data. Pass {@code null} to reset.
     *
     * @param customModelData the custom model data
     * @return the builder
     */
    public @NonNull B customModelData(final @Nullable Integer customModelData) {
        this.itemMeta.setCustomModelData(customModelData);
        return (B) this;
    }

    /**
     * Gets the localized name.
     *
     * @return the localized name
     */
    public @NonNull String localizedName() {
        return this.itemMeta.getLocalizedName();
    }

    /**
     * Sets the localized name. Pass {@code null} to reset.
     *
     * @param localizedName the localized name
     * @return the builder
     */
    public @NonNull B localizedName(final @Nullable String localizedName) {
        this.itemMeta.setLocalizedName(localizedName);
        return (B) this;
    }

    /**
     * Gets the {@link AttributeModifier}s.
     *
     * @return the {@link AttributeModifier}s
     */
    public @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers() {
        return this.itemMeta.getAttributeModifiers();
    }

    /**
     * Sets the {@link AttributeModifier}s. Pass {@code null} to reset.
     *
     * @param attributeModifiers the {@link AttributeModifier}s
     * @return the builder
     */
    public @NonNull B attributeModifiers(final @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
        this.itemMeta.setAttributeModifiers(attributeModifiers);
        return (B) this;
    }

    /**
     * Adds an {@link AttributeModifier}.
     *
     * @param attribute         the attribute to modify
     * @param attributeModifier the {@link AttributeModifier} to add
     * @return the builder
     */
    public @NonNull B addAttributeModifier(
            final @NonNull Attribute attribute,
            final @NonNull AttributeModifier attributeModifier
    ) {
        this.itemMeta.addAttributeModifier(attribute, attributeModifier);
        return (B) this;
    }

    /**
     * Removes an {@link AttributeModifier}.
     *
     * @param attribute         the attribute to modify
     * @param attributeModifier the {@link AttributeModifier} to remove
     * @return the builder
     */
    public @NonNull B removeAttributeModifier(
            final @NonNull Attribute attribute,
            final @NonNull AttributeModifier attributeModifier
    ) {
        this.itemMeta.removeAttributeModifier(attribute, attributeModifier);
        return (B) this;
    }

    /**
     * Removes all {@link AttributeModifier}s for the given {@code attribute}.
     *
     * @param attribute the {@link Attribute}
     * @return the builder
     */
    public @NonNull B removeAttributeModifier(final @NonNull Attribute... attribute) {
        for (final @NonNull Attribute item : attribute) {
            this.itemMeta.removeAttributeModifier(item);
        }
        return (B) this;
    }

    /**
     * Builds the {@link ItemStack} from the set properties.
     *
     * @return the built {@link ItemStack}
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack.clone();
    }

}

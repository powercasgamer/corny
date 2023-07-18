package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link Damageable}.
 */
@SuppressWarnings("unused")
public final class DamageableBuilder extends AbstractPaperItemBuilder<DamageableBuilder, Damageable> {

    private DamageableBuilder(final @NonNull ItemStack itemStack, final @NonNull Damageable itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link DamageableBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link DamageableBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull DamageableBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new DamageableBuilder(itemStack, castMeta(itemStack.getItemMeta(), Damageable.class));
    }

    /**
     * Creates an {@link DamageableBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link DamageableBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull DamageableBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return DamageableBuilder.of(getItem(material));
    }

    /**
     * Gets the damage.
     *
     * @return the damage
     */
    public @Nullable Integer damage() {
        if (!this.itemMeta.hasDamage()) {
            return null;
        }
        return this.itemMeta.getDamage();
    }

    /**
     * Sets the damage.
     *
     * @param damage the damage
     * @return the builder
     */
    public @NonNull DamageableBuilder damage(final @NonNull Integer damage) {
        this.itemMeta.setDamage(damage);
        return this;
    }

}

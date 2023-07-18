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

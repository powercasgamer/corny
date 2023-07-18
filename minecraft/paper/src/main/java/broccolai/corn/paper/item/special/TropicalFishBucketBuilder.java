package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link TropicalFishBucketMeta}.
 */
@SuppressWarnings("unused")
public final class TropicalFishBucketBuilder extends AbstractPaperItemBuilder<TropicalFishBucketBuilder, TropicalFishBucketMeta> {

    private TropicalFishBucketBuilder(final @NonNull ItemStack itemStack, final @NonNull TropicalFishBucketMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link TropicalFishBucketBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull TropicalFishBucketBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new TropicalFishBucketBuilder(itemStack, castMeta(itemStack.getItemMeta(), TropicalFishBucketMeta.class));
    }

    /**
     * Creates a {@link TropicalFishBucketBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link org.bukkit.inventory.meta.ItemMeta} is not the correct type
     */
    public static @NonNull TropicalFishBucketBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return TropicalFishBucketBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link TropicalFishBucketBuilder} of type {@link Material#TROPICAL_FISH_BUCKET}. A convenience method.
     *
     * @return instance of {@link TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull TropicalFishBucketBuilder ofTropicalFishBucket() throws IllegalArgumentException {
        return ofType(Material.TROPICAL_FISH_BUCKET);
    }

    /**
     * Gets the {@link TropicalFish.Pattern}.
     *
     * @return the pattern
     */
    public TropicalFish.@NonNull Pattern pattern() {
        return this.itemMeta.getPattern();
    }

    /**
     * Sets the {@link TropicalFish.Pattern}.
     *
     * @param pattern the pattern
     * @return the builder
     */
    public @NonNull TropicalFishBucketBuilder pattern(final TropicalFish.@NonNull Pattern pattern) {
        this.itemMeta.setPattern(pattern);
        return this;
    }

    /**
     * Gets the pattern color.
     *
     * @return the pattern color
     */
    public @NonNull DyeColor patternColor() {
        return this.itemMeta.getPatternColor();
    }

    /**
     * Sets the pattern color.
     *
     * @param patternColor the pattern color
     * @return the builder
     */
    public @NonNull TropicalFishBucketBuilder patternColor(final @NonNull DyeColor patternColor) {
        this.itemMeta.setPatternColor(patternColor);
        return this;
    }

    /**
     * Gets the body color.
     *
     * @return the body color
     */
    public @NonNull DyeColor bodyColor() {
        return this.itemMeta.getBodyColor();
    }

    /**
     * Sets the body color.
     *
     * @param bodyColor the body color
     * @return the builder
     */
    public @NonNull TropicalFishBucketBuilder bodyColor(final @NonNull DyeColor bodyColor) {
        this.itemMeta.setBodyColor(bodyColor);
        return this;
    }

    /**
     * Gets whether a variant tag exists.
     * If true, a specific fish will be spawned.
     *
     * @return whether a variant tag exists
     */
    public boolean variant() {
        return this.itemMeta.hasVariant();
    }

}

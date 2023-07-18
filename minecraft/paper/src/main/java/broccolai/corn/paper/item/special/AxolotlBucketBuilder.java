package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link AxolotlBucketMeta}.
 */
@SuppressWarnings("unused")
public final class AxolotlBucketBuilder extends AbstractPaperItemBuilder<AxolotlBucketBuilder, AxolotlBucketMeta> {

    private AxolotlBucketBuilder(final @NonNull ItemStack itemStack, final @NonNull AxolotlBucketMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link AxolotlBucketBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull AxolotlBucketBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new AxolotlBucketBuilder(itemStack, castMeta(itemStack.getItemMeta(), AxolotlBucketMeta.class));
    }

    /**
     * Creates an {@link AxolotlBucketBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull AxolotlBucketBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return AxolotlBucketBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link AxolotlBucketBuilder} of type {@link Material#AXOLOTL_BUCKET}. A convenience method.
     *
     * @return instance of {@link AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull AxolotlBucketBuilder ofAxolotlBucket() throws IllegalArgumentException {
        return ofType(Material.AXOLOTL_BUCKET);
    }

    /**
     * Gets the variant.
     *
     * @return the variant
     */
    public Axolotl.@NonNull Variant variant() {
        return this.itemMeta.getVariant();
    }

    /**
     * Sets the variant.
     *
     * @param variant the variant
     * @return the builder
     */
    public @NonNull AxolotlBucketBuilder variant(final Axolotl.@NonNull Variant variant) {
        this.itemMeta.setVariant(variant);
        return this;
    }

    /**
     * Gets whether a variant tag exists.
     * If true, a specific axolotl will be spawned.
     *
     * @return whether a variant tag exists
     */
    public boolean hasVariant() {
        return this.itemMeta.hasVariant();
    }

}

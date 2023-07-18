package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link BlockDataMeta}.
 */
@SuppressWarnings("unused")
public final class BlockDataBuilder extends AbstractPaperItemBuilder<BlockDataBuilder, BlockDataMeta> {

    private BlockDataBuilder(final @NonNull ItemStack itemStack, final @NonNull BlockDataMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link BlockDataBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BlockDataBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BlockDataBuilder(itemStack, castMeta(itemStack.getItemMeta(), BlockDataMeta.class));
    }

    /**
     * Creates a {@link BlockDataBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BlockDataBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BlockDataBuilder.of(getItem(material));
    }

    /**
     * Gets a copy of the {@link BlockData}. Creates a new one if it doesn't currently exist.
     *
     * @param material the material the data should be retrieved in the context of
     * @return the {@link BlockData}
     */
    public @NonNull BlockData blockData(final @NonNull Material material) {
        return this.itemMeta.getBlockData(material);
    }

    /**
     * Sets the {@link BlockData}.
     *
     * @param blockData the {@link BlockData}
     * @return the builder
     */
    public @NonNull BlockDataBuilder blockData(final @NonNull BlockData blockData) {
        this.itemMeta.setBlockData(blockData);
        return this;
    }

    /**
     * Gets whether a {@link BlockData} is currently attached.
     *
     * @return whether a {@link BlockData} is currently attached
     */
    public boolean hasBlockData() {
        return this.itemMeta.hasBlockData();
    }

}

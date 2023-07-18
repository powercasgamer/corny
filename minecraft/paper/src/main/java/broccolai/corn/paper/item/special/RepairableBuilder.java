package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link Repairable}.
 */
@SuppressWarnings("unused")
public final class RepairableBuilder extends AbstractPaperItemBuilder<RepairableBuilder, Repairable> {

    private RepairableBuilder(final @NonNull ItemStack itemStack, final @NonNull Repairable itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link RepairableBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link RepairableBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull RepairableBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new RepairableBuilder(itemStack, castMeta(itemStack.getItemMeta(), Repairable.class));
    }

    /**
     * Creates an {@link RepairableBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link RepairableBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull RepairableBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return RepairableBuilder.of(getItem(material));
    }

    /**
     * Gets the repair cost.
     *
     * @return the repair cost
     */
    public @Nullable Integer repairCost() {
        if (this.itemMeta.hasRepairCost()) {
            return null;
        }
        return this.itemMeta.getRepairCost();
    }

    /**
     * Sets the repair cost.
     *
     * @param repairCost the repair cost
     * @return the builder
     */
    public @NonNull RepairableBuilder repairCost(final @NonNull Integer repairCost) {
        this.itemMeta.setRepairCost(repairCost);
        return this;
    }

}

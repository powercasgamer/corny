package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link ArmorStandMeta}.
 */
@SuppressWarnings("unused")
public final class ArmorStandBuilder extends AbstractPaperItemBuilder<ArmorStandBuilder, ArmorStandMeta> {

    private ArmorStandBuilder(final @NonNull ItemStack itemStack, final @NonNull ArmorStandMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link ArmorStandBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new ArmorStandBuilder(itemStack, castMeta(itemStack.getItemMeta(), ArmorStandMeta.class));
    }

    /**
     * Creates an {@link ArmorStandBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return ArmorStandBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link ArmorStandBuilder} of type {@link Material#ARMOR_STAND}. A convenience method.
     *
     * @return instance of {@link ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder ofArmorStand() throws IllegalArgumentException {
        return ofType(Material.ARMOR_STAND);
    }

    /**
     * Gets whether the armor stand is invisible.
     *
     * @return whether the armor stand is invisible
     */
    public boolean invisible() {
        return this.itemMeta.isInvisible();
    }

    /**
     * Sets whether the armor stand is invisible.
     *
     * @param invisible whether the armor stand is invisible
     * @return the builder
     */
    public @NonNull ArmorStandBuilder invisible(final boolean invisible) {
        this.itemMeta.setInvisible(invisible);
        return this;
    }

    /**
     * Gets whether the armor stand is small.
     *
     * @return whether the armor stand is small
     */
    public boolean small() {
        return this.itemMeta.isSmall();
    }

    /**
     * Sets whether the armor stand is small.
     *
     * @param small whether the armor stand is small
     * @return the builder
     */
    public @NonNull ArmorStandBuilder small(final boolean small) {
        this.itemMeta.setSmall(small);
        return this;
    }

    /**
     * Gets whether the armor stand shows its arms.
     *
     * @return whether the armor stand shows arms
     */
    public boolean showArms() {
        return this.itemMeta.shouldShowArms();
    }

    /**
     * Sets whether the armor stand shows its arms.
     *
     * @param showArms whether the armor stand shows arms
     * @return the builder
     */
    public @NonNull ArmorStandBuilder showArms(final boolean showArms) {
        this.itemMeta.setShowArms(showArms);
        return this;
    }

    /**
     * Gets whether the armor stand has no base plate.
     *
     * @return whether the armor stand has no base plate
     */
    public boolean noBasePlate() {
        return this.itemMeta.hasNoBasePlate();
    }

    /**
     * Sets whether the armor stand has no base plate.
     *
     * @param noBasePlate whether the armor stand has no base plate
     * @return the builder
     */
    public @NonNull ArmorStandBuilder noBasePlate(final boolean noBasePlate) {
        this.itemMeta.setNoBasePlate(noBasePlate);
        return this;
    }

    /**
     * Gets whether the armor stand is a marker.
     *
     * @return whether the armor stand is a marker
     */
    public boolean marker() {
        return this.itemMeta.isMarker();
    }

    /**
     * Sets whether the armor stand is a marker.
     *
     * @param marker whether the armor stand is a marker
     * @return the builder
     */
    public @NonNull ArmorStandBuilder marker(final boolean marker) {
        this.itemMeta.setMarker(marker);
        return this;
    }

}

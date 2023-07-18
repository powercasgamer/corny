package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link MapMeta}.
 */
@SuppressWarnings("unused")
public final class MapBuilder extends AbstractPaperItemBuilder<MapBuilder, MapMeta> {

    private MapBuilder(final @NonNull ItemStack itemStack, final @NonNull MapMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link MapBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link MapBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull MapBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new MapBuilder(itemStack, castMeta(itemStack.getItemMeta(), MapMeta.class));
    }

    /**
     * Creates a {@link MapBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link MapBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull MapBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return MapBuilder.of(getItem(material));
    }

    /**
     * Gets the {@link Color}.
     *
     * @return the {@link Color}
     */
    public @Nullable Color color() {
        return this.itemMeta.getColor();
    }

    /**
     * Sets the {@link Color}. Pass {@code null} to reset.
     *
     * @param color the {@link Color}
     * @return the builder
     */
    public @NonNull MapBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

    /**
     * Gets the location name.
     *
     * @return the location name
     */
    public @Nullable String locationName() {
        return this.itemMeta.getLocationName();
    }

    /**
     * Sets the location name. Pass {@code null} to reset.
     *
     * @param locationName the location name
     * @return the builder
     */
    public @NonNull MapBuilder locationName(final @Nullable String locationName) {
        this.itemMeta.setLocationName(locationName);
        return this;
    }

    /**
     * Gets the {@link MapView}.
     *
     * @return the {@link MapView}
     */
    public @Nullable MapView mapView() {
        return this.itemMeta.getMapView();
    }

    /**
     * Sets the {@link MapView}. Pass {@code null} to reset.
     *
     * @param mapView the {@link MapView}
     * @return the builder
     */
    public @NonNull MapBuilder mapView(final @Nullable MapView mapView) {
        this.itemMeta.setMapView(mapView);
        return this;
    }

    /**
     * Gets whether the map is scaling.
     *
     * @return whether the map is scaling
     */
    public boolean scaling() {
        return this.itemMeta.isScaling();
    }

    /**
     * Sets whether the map is scaling.
     *
     * @param scaling whether the map is scaling
     * @return the builder
     */
    public @NonNull MapBuilder scaling(final boolean scaling) {
        this.itemMeta.setScaling(scaling);
        return this;
    }

}

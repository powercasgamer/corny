package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.MusicInstrument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MusicInstrumentMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link MusicInstrumentMeta}.
 */
@SuppressWarnings("unused")
public class MusicInstrumentBuilder extends AbstractPaperItemBuilder<MusicInstrumentBuilder, MusicInstrumentMeta> {

    protected MusicInstrumentBuilder(final @NonNull ItemStack itemStack, final @NonNull MusicInstrumentMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@link MusicInstrumentBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link MusicInstrumentBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull MusicInstrumentBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new MusicInstrumentBuilder(itemStack, castMeta(itemStack.getItemMeta(), MusicInstrumentMeta.class));
    }

    /**
     * Creates an {@link MusicInstrumentBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link MusicInstrumentBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull MusicInstrumentBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return MusicInstrumentBuilder.of(getItem(material));
    }

    /**
     * Set the instrument
     *
     * @param instrument to set
     * @return the builder
     */
    public @NonNull MusicInstrumentBuilder instrument(final @Nullable MusicInstrument instrument) {
        this.itemMeta.setInstrument(instrument);
        return this;
    }

    /**
     * get the music instrument
     *
     * @return the MusicInstrument
     */
    public @Nullable MusicInstrument instrument() {
        return this.itemMeta.getInstrument();
    }

}

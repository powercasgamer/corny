package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link FireworkEffectMeta}.
 */
@SuppressWarnings("unused")
public final class FireworkEffectBuilder extends AbstractPaperItemBuilder<FireworkEffectBuilder, FireworkEffectMeta> {

    private FireworkEffectBuilder(final @NonNull ItemStack itemStack, final @NonNull FireworkEffectMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link FireworkEffectBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link FireworkEffectBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull FireworkEffectBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new FireworkEffectBuilder(itemStack, castMeta(itemStack.getItemMeta(), FireworkEffectMeta.class));
    }

    /**
     * Creates a {@link FireworkEffectBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link FireworkEffectBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull FireworkEffectBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return FireworkEffectBuilder.of(getItem(material));
    }

    /**
     * Gets the {@link FireworkEffect}.
     *
     * @return the {@link FireworkEffect}
     */
    public @Nullable FireworkEffect fireworkEffect() {
        return this.itemMeta.getEffect();
    }

    /**
     * Sets the {@link FireworkEffect}. Pass {@code null} to reset.
     *
     * @param fireworkEffect the {@link FireworkEffect}
     * @return the builder
     */
    public @NonNull FireworkEffectBuilder fireworkEffect(final @Nullable FireworkEffect fireworkEffect) {
        this.itemMeta.setEffect(fireworkEffect);
        return this;
    }

}

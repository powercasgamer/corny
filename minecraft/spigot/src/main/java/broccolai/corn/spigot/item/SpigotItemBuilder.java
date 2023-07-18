package broccolai.corn.spigot.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link ItemMeta}.
 */
@SuppressWarnings({"unused"})
public final class SpigotItemBuilder extends AbstractSpigotItemBuilder<SpigotItemBuilder, ItemMeta> {

    private SpigotItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                ));
    }

    /**
     * Creates a {@link SpigotItemBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link SpigotItemBuilder}
     */
    public static @NonNull SpigotItemBuilder of(final @NonNull ItemStack itemStack) {
        return new SpigotItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Creates a {@link SpigotItemBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link SpigotItemBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static @NonNull SpigotItemBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return SpigotItemBuilder.of(getItem(material));
    }

}

package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link KnowledgeBookMeta}.
 */
@SuppressWarnings("unused")
public final class KnowledgeBookBuilder extends AbstractPaperItemBuilder<KnowledgeBookBuilder, KnowledgeBookMeta> {

    private KnowledgeBookBuilder(final @NonNull ItemStack itemStack, final @NonNull KnowledgeBookMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link KnowledgeBookBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull KnowledgeBookBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new KnowledgeBookBuilder(itemStack, castMeta(itemStack.getItemMeta(), KnowledgeBookMeta.class));
    }

    /**
     * Creates a {@link KnowledgeBookBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull KnowledgeBookBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return KnowledgeBookBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link KnowledgeBookBuilder} of type {@link Material#KNOWLEDGE_BOOK}. A convenience method.
     *
     * @return instance of {@link KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull KnowledgeBookBuilder ofKnowledgeBook() throws IllegalArgumentException {
        return ofType(Material.KNOWLEDGE_BOOK);
    }

    /**
     * Gets the recipes.
     *
     * @return the recipes
     */
    public @NonNull List<NamespacedKey> recipes() {
        return this.itemMeta.getRecipes();
    }

    /**
     * Sets the recipes.
     *
     * @param recipes the recipes
     * @return the builder
     */
    public @NonNull KnowledgeBookBuilder recipes(final @NonNull List<@NonNull NamespacedKey> recipes) {
        this.itemMeta.setRecipes(recipes);
        return this;
    }

    /**
     * Adds a recipe.
     *
     * @param recipe the recipe to add
     * @return the builder
     */
    public @NonNull KnowledgeBookBuilder addRecipe(final @NonNull NamespacedKey... recipe) {
        this.itemMeta.addRecipe(recipe);
        return this;
    }

}

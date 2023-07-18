package broccolai.corn.paper.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DefaultQualifier(NonNull.class)
public final class ComponentUtil {

    private ComponentUtil() {
    }

    /**
     * Disables italics on a component if it is not already set.
     * @param component the component
     * @return the component with italics disabled
     */
    public static Component disableItalics(final Component component) {
        if (component.decoration(TextDecoration.ITALIC) == TextDecoration.State.NOT_SET) {
            return component.decoration(TextDecoration.ITALIC, false);
        }
        return component;
    }

    /**
     * Disables italics on a component if it is not already set.
     * @param components the components
     * @return the components with italics disabled
     */
    public static List<Component> disableItalicsList(final Collection<? extends Component> components) {
        final List<Component> newComponents = new ArrayList<>(components.size());
        for (final Component component : components) {
            if (component.decoration(TextDecoration.ITALIC) == TextDecoration.State.NOT_SET) {
                newComponents.add(component.decoration(TextDecoration.ITALIC, false));
            }
        }

        return newComponents;
    }
}

/*
 * This file is part of corn, licensed under the GNU Lesser General Public License (LGPL) version 3 license.
 *
 * Copyright (C)  2020-2023 broccolai
 * Copyright (C)  2020-2023 Contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class DirtyPropertiesMapTest {

    private final DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();

    private final SomePropertyHolder entryOne = new SomePropertyHolder(20, "word");
    private final SomeComplexPropertyHolder entryTwo = new SomeComplexPropertyHolder(
            20,
            new SomePropertyHolder(10, "test")
    );

    @BeforeAll
    void create() {
        this.dirtyMap.put(1, entryOne);
        this.dirtyMap.put(5, entryTwo);
    }

    @BeforeEach
    void cleanup() {
        this.dirtyMap.clean();
    }

    @Test
    void isEmpty() {
        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void simpleChange() {
        entryOne.number(25);
        assertThat(dirtyMap.dirty()).containsExactly(entryOne);
    }

    @Test
    void simpleChangeAndClean() {
        entryOne.number(25);
        dirtyMap.clean();
        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void changeBackToNormal() {
        entryTwo.number(25);
        dirtyMap.clean();

        entryTwo.number(20);
        entryTwo.number(25);
        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void changeNestedField() {
        entryTwo.someHolder.number(14);
        assertThat(dirtyMap.dirty()).containsExactly(entryTwo);
    }

    static final class SomePropertyHolder implements PropertyHolder {

        private int number;
        private String word;

        SomePropertyHolder(final int number, final String word) {
            this.number = number;
            this.word = word;
        }

        public void number(final int number) {
            this.number = number;
        }

        public void word(final String word) {
            this.word = word;
        }

        @Override
        public @NonNull PropertySnapshot properties() {
            return PropertySnapshot.of(
                    Property.of("number", this.number),
                    SafeProperty.of("word", this.word)
            );
        }

    }

    static final class SomeComplexPropertyHolder implements PropertyHolder {

        private int number;
        private SomePropertyHolder someHolder;

        SomeComplexPropertyHolder(final int number, final SomePropertyHolder someHolder) {
            this.number = number;
            this.someHolder = someHolder;
        }

        public void number(final int number) {
            this.number = number;
        }

        public void someHolder(final SomePropertyHolder someHolder) {
            this.someHolder = someHolder;
        }

        @Override
        public @NonNull PropertySnapshot properties() {
            return PropertySnapshot.of(
                    Property.of("number", this.number),
                    Property.of("someHolder", this.someHolder)
            );
        }

    }


}

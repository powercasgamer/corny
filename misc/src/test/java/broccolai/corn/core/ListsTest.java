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
package broccolai.corn.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

final class ListsTest {

    @Test
    void testGroup() {
        TestPair p1 = new TestPair(1, 'a');
        TestPair p2 = new TestPair(3, 'b');
        TestPair p3 = new TestPair(1, 'c');

        List<TestPair> input = Arrays.asList(p1, p2, p3);
        var output = Lists.group(input, TestPair::key);

        assertThat(output.get(1)).containsExactly(p1, p3);
        assertThat(output.get(3)).containsExactly(p2);
    }

    @Test
    void testMap() {
        List<String> unparsed = Arrays.asList("1", "5", "22", "41");
        List<Integer> parsed = Lists.map(unparsed, Integer::parseInt);

        assertThat(parsed).containsExactly(1, 5, 22, 41);
    }

    record TestPair(int key, char value) {

    }

}

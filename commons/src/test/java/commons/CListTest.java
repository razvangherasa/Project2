/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CListTest {

    @Test
    public void checkConstructor() {
        Card card1 = new Card("Card1");
        List<Card> list = new ArrayList<>();
        list.add(card1);
        CList cList= new CList("TO-DO");
        cList.list.add(card1);
        assertEquals(list, cList.list);
        assertEquals("TO-DO", cList.title);
    }

    @Test
    public void equalsHashCode() {
        CList cList = new CList("TO-DO");
        CList cList2 = new CList("TO-DO");
        assertEquals(cList, cList2);
        assertEquals(cList.hashCode(), cList2.hashCode());
    }

    @Test
    public void notEqualsHashCode() {
        CList cList = new CList("TO-DO");
        CList cList2 = new CList("DONE");
        assertNotEquals(cList, cList2);
        assertNotEquals(cList.hashCode(), cList2.hashCode());
    }

    @Test
    public void hasToString() {
        CList cList = new CList("TO-DO");
        String actual = cList.toString();
        assertTrue(actual.contains(CList.class.getSimpleName()));
        assertTrue(actual.contains("\n"));
        assertTrue(actual.contains("title"));
    }
}
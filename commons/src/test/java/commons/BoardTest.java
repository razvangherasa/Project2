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

public class BoardTest {

    @Test
    public void checkConstructor() {
        CList cList= new CList( "TO-DO");
        List<CList> b = new ArrayList<>();
        b.add(cList);
        Board board= new Board();
        board.list.add(cList);
        assertEquals(b, board.list);
    }

    @Test
    public void equalsHashCode() {
        CList cList= new CList("TO-DO");
        Board b1 = new Board();
        Board b2 = new Board();
        b1.list.add(cList);
        b2.list.add(cList);
        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    public void notEqualsHashCode() {
        CList cList = new CList("TO-DO");
        CList cList2 = new CList("DONE");
        Board b1 = new Board();
        Board b2 = new Board();
        b1.list.add(cList);
        b2.list.add(cList2);
        assertNotEquals(b1, b2);
        assertNotEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    public void hasToString() {
        CList cList= new CList("TO-DO");
        Board board= new Board();
        board.list.add(cList);
        String actual = board.toString();
        assertTrue(actual.contains(Board.class.getSimpleName()));
        assertTrue(actual.contains("\n"));
        assertTrue(actual.contains("title"));
    }
}
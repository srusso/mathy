package net.sr89.mathy.comp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommutativeMatricesTest {
    @Test
    void commutativeMatrices2x2() {
        assertEquals(674609, new CommutativeMatrices().countCommutativeMatrices(2,-5, 5));
    }

    @Test
    void commutativeMatrices3x3() {
        assertEquals(375417, new CommutativeMatrices().countCommutativeMatrices(3,-1, 1));
    }
}
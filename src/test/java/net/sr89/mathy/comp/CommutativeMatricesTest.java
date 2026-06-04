package net.sr89.mathy.comp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommutativeMatricesTest {
    @Test
    void commutativeMatrices2x2() {
        assertEquals(674609, new CommutativeMatrices().countCommutativeMatrices());
    }
}
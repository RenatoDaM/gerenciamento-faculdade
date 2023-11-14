package com.gerenciamentofaculdade.gerenciamentofaculdade.unittests;

import com.gerenciamentofaculdade.gerenciamentofaculdade.util.RaGenerator;
import org.junit.jupiter.api.Test;

public class RaGeneratorTest {
    @Test
    public void testRa() {
        String result = RaGenerator.gerarRA();
        System.out.println(result);
    }
}

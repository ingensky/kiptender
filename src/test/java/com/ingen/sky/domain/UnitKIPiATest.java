package com.ingen.sky.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ingen.sky.web.rest.TestUtil;

public class UnitKIPiATest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitKIPiA.class);
        UnitKIPiA unitKIPiA1 = new UnitKIPiA();
        unitKIPiA1.setId(1L);
        UnitKIPiA unitKIPiA2 = new UnitKIPiA();
        unitKIPiA2.setId(unitKIPiA1.getId());
        assertThat(unitKIPiA1).isEqualTo(unitKIPiA2);
        unitKIPiA2.setId(2L);
        assertThat(unitKIPiA1).isNotEqualTo(unitKIPiA2);
        unitKIPiA1.setId(null);
        assertThat(unitKIPiA1).isNotEqualTo(unitKIPiA2);
    }
}

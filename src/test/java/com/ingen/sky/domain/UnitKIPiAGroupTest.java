package com.ingen.sky.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ingen.sky.web.rest.TestUtil;

public class UnitKIPiAGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitKIPiAGroup.class);
        UnitKIPiAGroup unitKIPiAGroup1 = new UnitKIPiAGroup();
        unitKIPiAGroup1.setId(1L);
        UnitKIPiAGroup unitKIPiAGroup2 = new UnitKIPiAGroup();
        unitKIPiAGroup2.setId(unitKIPiAGroup1.getId());
        assertThat(unitKIPiAGroup1).isEqualTo(unitKIPiAGroup2);
        unitKIPiAGroup2.setId(2L);
        assertThat(unitKIPiAGroup1).isNotEqualTo(unitKIPiAGroup2);
        unitKIPiAGroup1.setId(null);
        assertThat(unitKIPiAGroup1).isNotEqualTo(unitKIPiAGroup2);
    }
}

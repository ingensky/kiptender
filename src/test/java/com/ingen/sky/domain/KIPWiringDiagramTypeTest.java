package com.ingen.sky.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ingen.sky.web.rest.TestUtil;

public class KIPWiringDiagramTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KIPWiringDiagramType.class);
        KIPWiringDiagramType kIPWiringDiagramType1 = new KIPWiringDiagramType();
        kIPWiringDiagramType1.setId(1L);
        KIPWiringDiagramType kIPWiringDiagramType2 = new KIPWiringDiagramType();
        kIPWiringDiagramType2.setId(kIPWiringDiagramType1.getId());
        assertThat(kIPWiringDiagramType1).isEqualTo(kIPWiringDiagramType2);
        kIPWiringDiagramType2.setId(2L);
        assertThat(kIPWiringDiagramType1).isNotEqualTo(kIPWiringDiagramType2);
        kIPWiringDiagramType1.setId(null);
        assertThat(kIPWiringDiagramType1).isNotEqualTo(kIPWiringDiagramType2);
    }
}

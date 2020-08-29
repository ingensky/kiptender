package com.ingen.sky.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ingen.sky.web.rest.TestUtil;

public class InstallationKIPiATest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstallationKIPiA.class);
        InstallationKIPiA installationKIPiA1 = new InstallationKIPiA();
        installationKIPiA1.setId(1L);
        InstallationKIPiA installationKIPiA2 = new InstallationKIPiA();
        installationKIPiA2.setId(installationKIPiA1.getId());
        assertThat(installationKIPiA1).isEqualTo(installationKIPiA2);
        installationKIPiA2.setId(2L);
        assertThat(installationKIPiA1).isNotEqualTo(installationKIPiA2);
        installationKIPiA1.setId(null);
        assertThat(installationKIPiA1).isNotEqualTo(installationKIPiA2);
    }
}

package com.ingen.sky.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ingen.sky.web.rest.TestUtil;

public class InstallationKIPiAGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstallationKIPiAGroup.class);
        InstallationKIPiAGroup installationKIPiAGroup1 = new InstallationKIPiAGroup();
        installationKIPiAGroup1.setId(1L);
        InstallationKIPiAGroup installationKIPiAGroup2 = new InstallationKIPiAGroup();
        installationKIPiAGroup2.setId(installationKIPiAGroup1.getId());
        assertThat(installationKIPiAGroup1).isEqualTo(installationKIPiAGroup2);
        installationKIPiAGroup2.setId(2L);
        assertThat(installationKIPiAGroup1).isNotEqualTo(installationKIPiAGroup2);
        installationKIPiAGroup1.setId(null);
        assertThat(installationKIPiAGroup1).isNotEqualTo(installationKIPiAGroup2);
    }
}

package com.ingen.sky.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ingen.sky.web.rest.TestUtil;

public class ProjectMarkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectMark.class);
        ProjectMark projectMark1 = new ProjectMark();
        projectMark1.setId(1L);
        ProjectMark projectMark2 = new ProjectMark();
        projectMark2.setId(projectMark1.getId());
        assertThat(projectMark1).isEqualTo(projectMark2);
        projectMark2.setId(2L);
        assertThat(projectMark1).isNotEqualTo(projectMark2);
        projectMark1.setId(null);
        assertThat(projectMark1).isNotEqualTo(projectMark2);
    }
}

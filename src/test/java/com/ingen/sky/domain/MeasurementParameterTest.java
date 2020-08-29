package com.ingen.sky.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ingen.sky.web.rest.TestUtil;

public class MeasurementParameterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeasurementParameter.class);
        MeasurementParameter measurementParameter1 = new MeasurementParameter();
        measurementParameter1.setId(1L);
        MeasurementParameter measurementParameter2 = new MeasurementParameter();
        measurementParameter2.setId(measurementParameter1.getId());
        assertThat(measurementParameter1).isEqualTo(measurementParameter2);
        measurementParameter2.setId(2L);
        assertThat(measurementParameter1).isNotEqualTo(measurementParameter2);
        measurementParameter1.setId(null);
        assertThat(measurementParameter1).isNotEqualTo(measurementParameter2);
    }
}
